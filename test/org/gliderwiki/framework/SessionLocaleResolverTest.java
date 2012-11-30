package org.gliderwiki.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * I18N 국제화 로케일 설정 테스트 
 * 
 * @author yion
 * @version   1.0
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext-*.xml",
"classpath:/spring/config/servlet-*.xml" })
@DirtiesContext
@Ignore
public class SessionLocaleResolverTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 
     * Locale 설정 후 값 비교 
     *
     */
    @Test
    public void testResolveLocale() {
        // Request Mock Object 생성 
        MockHttpServletRequest request = new MockHttpServletRequest();
               
        
        // Locale 설정 - US
        request.getSession().setAttribute(
                SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,  // org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE
                Locale.US);                  // ko_KR
        
        
        // Request 의 Locale 
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        
        
        Locale setLocaleValue = resolver.resolveLocale(request);
        logger.info("*** setLocaleValue.getDefault() : "  +  setLocaleValue.getDefault());
        
        logger.info("*** setLocaleValue : " + setLocaleValue);
        
        assertEquals(Locale.US, setLocaleValue);
    }

    @Test
    public void testResolveLocaleSession() {
        // Mock Object 생성 
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        logger.info("**** MockHttpServletRequest : " + request.getLocale());
        
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setLocale(request,  response, Locale.KOREA);
        assertEquals(Locale.KOREA, resolver.resolveLocale(request));
        
        logger.info("**** resolver.resolveLocale(request) : " + resolver.resolveLocale(request));

        HttpSession session = request.getSession();
        
        request = new MockHttpServletRequest();
        request.setSession(session);
        resolver = new SessionLocaleResolver();
        logger.info("resolver.resolveLocale(request) : " + resolver.resolveLocale(request));
        assertEquals(Locale.KOREA, resolver.resolveLocale(request));
        
    }
    
    @Test
    public void testResolveLocaleWithoutSession() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        
        logger.info("** MockHttpServletRequest.getLocale() : " + request.getLocale());
        
        
        request.addPreferredLocale(Locale.KOREA);
        
        logger.info("** request.getLocale() : " + request.getLocale());
        
        
        SessionLocaleResolver resolver = new SessionLocaleResolver();

        assertEquals(request.getLocale(), resolver.resolveLocale(request));
    }
    
    /**
     * 
     * 디폴트 로케일 정보를 읽어온다 
     *
     */
    @Test
    public void testResolveLocaleDefault(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        logger.info("** MockHttpServletRequest.getLocale() : " + request.getLocale());
        
        request.addPreferredLocale(Locale.KOREA);
               
        // 세션 리졸버에 default locale 설정  
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        
        
        assertEquals(Locale.US, resolver.resolveLocale(request));
        assertNotSame(request.getLocale(), resolver.resolveLocale(request));
        
    }
    
    @Test
    public void testSetNullLocale(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addPreferredLocale(Locale.KOREA);
        
        // Session 로케일 설정
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setLocale(request, response, null);
        
        Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        
        assertNull(locale);        
                
        HttpSession session = request.getSession();
        
        request = new MockHttpServletRequest();
        request.addPreferredLocale(Locale.KOREA);
        request.setSession(session);
        resolver = new SessionLocaleResolver();
        assertEquals(Locale.KOREA, resolver.resolveLocale(request));
        
    }
    
    @Test
    public void testAcceptHeaderLocaleResolver() {
        LocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        
        MockServletContext context = new MockServletContext();
        
        MockHttpServletRequest request = new MockHttpServletRequest(context);
        
        request.addPreferredLocale(Locale.KOREA);
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        Locale locale = localeResolver.resolveLocale(request);
        
        assertEquals(locale, Locale.KOREA);
        
        
        try {
            localeResolver.setLocale(request, response, Locale.US);
            
            locale = localeResolver.resolveLocale(request);
            assertEquals(locale, Locale.US);
        } catch(UnsupportedOperationException ex) { 
            assertTrue(ex instanceof UnsupportedOperationException);
        }
        
    }
    
}
