package org.gliderwiki.framework;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MessageUtil 에 코드를 던져 값이 출력되는지 확인한다. 
 * 로케일에 따라 출력되는 메시지를 확인한다.
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
public class MessageUtilTest {

    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    ApplicationContext appCtx;
    ReloadableResourceBundleMessageSource messageSource;
   
    @Before
    public void setUp() throws Exception {
        logger.info("************** appCtx Loading...");
        appCtx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-resource.xml");
        messageSource = (ReloadableResourceBundleMessageSource) appCtx.getBean("messageSource");
        
    }

    @After
    public void tearDown() throws Exception {
        logger.info("************** Destroying MessageUtilTest");
    }


    /**
     * 프로퍼티 파일에 정의된 메시지를 가져 온다,
     */
    @Test
    public void testGetMessageString() {
        //assertEquals(messageSource.getMessage("osp.result.success", null, null), "성공");
    }

    /**
     * 로케일에 따른 메시지 출력을 비교한다. 
     *
     */
    @Test
    public void testGetMessageStringLocale() {
        String language = "en";	// ko
        String country = "US";	// KR
        
        Locale loc = new Locale(language, country);
        logger.info("***** loc  : "  + loc);
        
        //assertEquals(messageSource.getMessage("osp.result.error.unknown",loc), "unknown error");
    }

    @Test
    public void testGetCurrentLocale() {
        String currentLocale = "ko_KR";
        Locale contextLocale = LocaleContextHolder.getLocale();
        logger.info("**** contextLocale.getCountry() " + contextLocale.getCountry());
        logger.info("**** contextLocale.getLanguage() " + contextLocale.getLanguage());
        logger.info("**** LocaleContextHolder.getLocale() : " + LocaleContextHolder.getLocale());
        //assertEquals(MessageUtil.getCurrentLocale().toString() , currentLocale);
    }

}
