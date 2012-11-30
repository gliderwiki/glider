package org.gliderwiki.framework.util;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Reply from FTP Server after its been sent a command.
 *
 * FTP replies are either single or multiline. Single line replies
 * consist of a three digit reply code, one space, and the text of the reply.
 * The first line of a multiline reply has the three digit reply code, one dash,
 * and the text of the reply.  The last line of a multiline reply must have the
 * same reply code and text as the first but with a space separating them
 * instead of a dash.
 * Two examples:
 * <pre>
 * 123 Reply Text
 * &nbsp;
 * 123-First line (optional)
 * Second line (allowed if there is a first line)
 *  234 A line beginning with numbers must be indented (allowed if first)
 * 123 The last line (required)
 * </pre>
 * The first digit of the reply code specifies the reply type and is all
 * that is needed for most operations, however there are two replies whose
 * reply text is specially parsed for additional information.
 * <pre>
 * 227 Entering Passive Mode (a1,a2,a3,a4,p1,p2)
 * 257 "file or directory name" blah blah blah
 * </pre>
 *
 * @author Glen Knowles, Copyright &#169; 2000
 * @version 1.0, 00/10/06
 */
public class FtpReply implements Cloneable {

  ///////////////////////////////////////////////
  // PRIVATE
  ///////////////////////////////////////////////
  private int     d_code         = 000;
  private String  d_text         = "000 Unable to contact server";
  private boolean d_isFromServer = false;

  // used for reply 227 blah blah (a1,a2,a3,a4,p1,p2) blah
  private String  d_dataHost = null;
  private int     d_dataPort = 0;

  // used for reply 257 "/home/user" blah blah blah
  private String  d_dataPath = null;

  ///////////////////////////////////////////////
  // PUBLIC
  ///////////////////////////////////////////////
  /**
   * The requested action is being initiated; expect another reply before
   * proceeding with a new command.
   */
  final public static int RC_PRELIMINARY = 1;
  /**
   * The requested action has been successfully completed.
   */
  final public static int RC_COMPLETE = 2;
  /**
   * The command has been accepted, but the requested action is being held in
   * abeyance, pending receipt of further information.
   */
  final public static int RC_INTERMEDIATE = 3;
  /**
   * The command was not accepted and the requested action did not take place,
   * but the error condition is temporary and the action may be requested
   * again.
   */
  final public static int RC_ERROR_TRANSIENT = 4;
  /**
   * The command was not accepted and the requested action did not take place.
   */
  final public static int RC_ERROR = 5;

  ///////////////////////////////////////////////
  // MODIFY
  ///////////////////////////////////////////////
  /**
   * Assign specific reply data to the FtpReply object. This function also
   * parses it for the reply code and possibly host, port, or path data
   * depending on the reply code.
   *
   * @parm isFromServer boolean; reply received from server and not the
   * result of a communication or protocol error?
   * @parm text Text of the reply, should start with the three digit code plus
   * a space followed by the rest of the text.
   * @throws IllegalArgumentException
   */
  public void setReply(boolean isFromServer, String text) {
    // set reply data
    d_isFromServer = isFromServer;
    d_code = 0;
    d_text = text;

    d_dataHost = null;
    d_dataPort = 0;
    d_dataPath = null;

    // get code
    if (d_text.length() < 4) {
      // too short to contain a reply code
      throw new IllegalArgumentException("Bad format");
    }
    try {
      d_code = Integer.parseInt(d_text.substring(0,3));

      // validate reply code
      if (d_code < 100 ||
          d_code > 599) throw new NumberFormatException();
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Bad reply code");
    }

    // parse special data from reply?
    switch (getCode()) {
    case 227: parseReply227(); break;
    case 257: parseReply257(); break;
    }
  }

  ///////////////////////////////////////////////
  // ACCESS
  ///////////////////////////////////////////////
  /**
   * Reply type, from first digit of the reply code.  This is one of
   * PRELIMINARY, COMPLETE, INTERMEDIATE, ERROR_TRANSIENT, or ERROR.
   *
   * @return reply type
   */
  public int getType() { return d_code / 100; }

  /**
   * Complete three digit reply code.
   *
   * @return reply code
   */
  public int getCode() { return d_code; }

  /**
   * Complete text of reply from FTP Server including the three digit
   * reply code.
   *
   * @return text of the reply
   */
  public String getText() { return d_text; }

  /**
   * Reply was received from the server and is not the result of a
   * communication or protocol error.
   *
   * @return reply is from server?
   */
  public boolean isFromServer() { return d_isFromServer; }

  /**
   * Host IP address specified in '227 Entering Passive Mode' reply.
   * Undefined if the reply code is not 227.
   *
   * @return Host IP address
   */
  public String getHost()   { return d_dataHost; }

  /**
   * Port specified in '227 Entering Passive Mode' reply. Undefined if the
   * reply code is not 227.
   *
   * @return Host port
   */
  public int getPort() { return d_dataPort; }

  /**
   * Path specified in '257 "path" blah blah blah' reply.  Undefined if the
   * reply code is not 257. Paths containing doublequotes are properly
   * decoded. The encoding method used by the server is to put two quotation
   * marks for every one that is contained in the path.
   *
   * @return file/directory path
   */
  public String getPath() { return d_dataPath; }

  //#############################################
  // PRIVATE
  //#############################################
  /**
   * Parse a code 227 reply into its host and port components.  <b>d_reply</b>
   * is updated with new host and port data.  FtpException thrown if the reply
   * is not a code 227 reply or is unparsable. A standard 227 reply is in the
   * form:
   * <pre>227 Entering Passive Mode (a1,a2,a3,a4,p1,p2)</pre>
   * This function parses it by looking for all numeric characters that are
   * adjacent to commas.
   *
   * @throws IllegalArgumentException
   */
  private void parseReply227() {

    // break into six comma separated parts
    StringTokenizer st = new StringTokenizer(d_text, ",");
    String[] parts = new String[6];
    for (int i1 = 0; st.hasMoreElements(); i1++) {
      try {
        // more then six parts?
        if (i1 == 6) throw new NoSuchElementException();
        parts[i1] = st.nextToken();
      }
      // fewer then six parts?
      catch (NoSuchElementException nope) {
        throw new IllegalArgumentException("Missing commas");
      }
    } // end getting parts of host, port

    // extract trailing number from first part
    int off;
    for (off = parts[0].length() - 1;
         off >= 0 && off >= parts[0].length() - 4;
         off--) {
      if (!Character.isDigit(parts[0].charAt(off))) break;
    }
    parts[0] = parts[0].substring(off + 1);

    // clean trailing info from last part
    for (off = 0; off < 3 && off < parts[5].length(); off++) {
      if (!Character.isDigit(parts[5].charAt(off))) break;
    }
    parts[5] = parts[5].substring(0, off);

    // Get dotted quad IP number first
    d_dataHost = parts[0] + "." + parts[1] + "." + parts[2] + "." + parts[3];

    // Determine port
    try { // Get first part of port, shift by 8 bits.
      d_dataPort =
        256 * Integer.parseInt(parts[4]) + Integer.parseInt(parts[5]);
    }
    catch (NumberFormatException e) {
      throw new IllegalArgumentException("Bad port number");
    }
  }

  /**
   * Parse a code 257 reply for its pathname component.  <b>d_reply</b>
   * is updated with new path data.  FtpException thrown if the reply
   * is not a code 257 reply or is unparsable. A standard 257 reply is in the
   * form:
   * <pre>257 "/dir with a "" in it" is current directory</pre>
   * Where the path data is the quoted text immediately following the reply
   * code.  Embedded quotation marks are encoded as two quotation marks.
   */
  private void parseReply257() {
    StringBuffer buf = new StringBuffer();

    // get quoted string, start after code and leading double quote.
    for (int i1 = 5; i1 < d_text.length(); i1++) {
      if (d_text.charAt(i1) == '"') {
        i1++;
        if (i1 == d_text.length() || d_text.charAt(i1) != '"') {
          break;
        }
      }
      buf.append(d_text.charAt(i1));
    }
    d_dataPath = buf.toString();
  }


/////////////////////////////////////////////////
} // class FtpReply
