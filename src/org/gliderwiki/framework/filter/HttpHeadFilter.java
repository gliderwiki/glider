/**
 * @FileName  : HttpHeadFilter.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 18.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author bluepoet
 *
 */
public class HttpHeadFilter implements Filter {
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (isHttpHead(httpServletRequest)) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            NoBodyResponseWrapper noBodyResponseWrapper = new NoBodyResponseWrapper(httpServletResponse);

            chain.doFilter(new ForceGetRequestWrapper(httpServletRequest), noBodyResponseWrapper);
            noBodyResponseWrapper.setContentLength();
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //Do nothing
    }

    /**
     * Checks whether the HTTP method of this request is HEAD.
     *
     * @param request The request to check.
     * @return {@code true} if it is HEAD, {@code false} if it isn't.
     */
    private boolean isHttpHead(HttpServletRequest request) {
        return "HEAD".equals(request.getMethod());
    }

    /**
     * Request wrapper that lies about the Http method and always returns GET.
     */
    private class ForceGetRequestWrapper extends HttpServletRequestWrapper {
        /**
         * Initializes the wrapper with this request.
         *
         * @param request The request to initialize the wrapper with.
         */
        public ForceGetRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * Lies about the HTTP method. Always returns GET.
         *
         * @return Always returns GET.
         */
        @Override
        public String getMethod() {
            return "GET";
        }
    }

    /**
     * Response wrapper that swallows the response body, leaving only the headers.
     */
    private class NoBodyResponseWrapper extends HttpServletResponseWrapper {
        /**
         * Outputstream that discards the data written to it.
         */
        private final NoBodyOutputStream noBodyOutputStream = new NoBodyOutputStream();

        private PrintWriter writer;

        /**
         * Constructs a response adaptor wrapping the given response.
         *
         * @param response The response to wrap.
         */
        public NoBodyResponseWrapper(HttpServletResponse response) {
            super(response);
        }

//        @Override
//        public ServletOutputStream getOutputStream() throws IOException {
//            return x;
//        }

        @Override
        public PrintWriter getWriter() throws UnsupportedEncodingException {
            if (writer == null) {
                writer = new PrintWriter(new OutputStreamWriter(noBodyOutputStream, getCharacterEncoding()));
            }

            return writer;
        }

        /**
         * Sets the content length, based on what has been written to the outputstream so far.
         */
        void setContentLength() {
            super.setContentLength(noBodyOutputStream.getContentLength());
        }
    }

    /**
     * Outputstream that only counts the length of what is being written to it while discarding the actual data.
     */
    private class NoBodyOutputStream extends ServletOutputStream {
        /**
         * The number of bytes written to this stream so far.
         */
        private int contentLength = 0;

        /**
         * @return The number of bytes written to this stream so far.
         */
        int getContentLength() {
            return contentLength;
        }

        @Override
        public void write(int b) {
            contentLength++;
        }

        @Override
        public void write(byte buf[], int offset, int len) throws IOException {
            contentLength += len;
        }
    }
}
