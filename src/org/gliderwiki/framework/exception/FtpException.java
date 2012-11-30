package org.gliderwiki.framework.exception;

import org.gliderwiki.framework.util.FtpReply;

/**
 * Exception thrown when unexpected or negative reply is received from the
 * server.
 * 
 * 
 * The getReply() method can be used to examine the reply that caused the
 * exception.
 * 
 * @author Glen Knowles, Copyright &#169; 2000
 * @version 1.0, 00/10/06
 */
public class FtpException extends Exception {
	private FtpReply d_reply;

	/**
	 * Constructs new FtpException with short explaination of the circumstance.
	 * The explaination is prepended to the text of the actual reply to form the
	 * exception message.
	 * 
	 * @parm reply reply that caused the exception
	 * @parm msg cirsumstance of exception
	 */
	public FtpException(FtpReply reply, String msg) {
		super(msg + ": " + reply.getText());
		d_reply = reply;
	}

	/**
	 * Convience constructor; Creates new FtpException using "Unexpected" as the
	 * explaination.
	 * 
	 * @parm reply reply that caused the exception
	 */
	public FtpException(FtpReply reply) {
		this(reply, "Unexpected");
	}

	/**
	 * Returns reply that caused this exception.
	 * 
	 * @return FtpReply object that caused exception
	 */
	public FtpReply getReply() {
		return d_reply;
	}

	/**
	 * Convience method; gets FTP reply type of underlying reply object.
	 * Equivalent to calling getReply().getType().
	 * 
	 * @return type of FtpReply object that caused exception
	 * @see FtpReply#getType
	 */
	public int getType() {
		return d_reply.getType();
	}

	/**
	 * Convience method; gets FTP reply code of underlying reply object.
	 * Equivalent to calling getReply().getCode().
	 * 
	 * @return FTP reply code of FtpReply object that caused exception
	 * @see FtpReply#getCode
	 */
	public int getCode() {
		return d_reply.getCode();
	}
}