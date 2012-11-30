package org.gliderwiki.framework;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * ResourceBundle 메시지 테스트 
 * 
 * @author yion
 * @version   1.0
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext-*.xml",
"classpath:/spring/config/servlet-*.xml" })
@DirtiesContext  // applicationContext 의 구성이나 상태가 변경한다는 것을 알려준다.
@Ignore
public class ConfigResourceTest implements ApplicationContextAware{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private ApplicationContext applicationContext;
        
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }


    /**
     * Bean 설정 값 확인 
     *
     */
    @Test
    public void testCount() {
        System.out.println("**** " + this.applicationContext.getBeanDefinitionCount());
    }

    @Test
    public void testRootMessageSource() {
        
        ReloadableResourceBundleMessageSource messageSource = (ReloadableResourceBundleMessageSource) applicationContext.getBean("messageSource");
        
        // 메시지 소스를 읽어온다.      
        // org.springframework.context.support.ReloadableResourceBundleMessageSource: basenames=[classpath:i18n/message]
        
        // 디폴트 설정 전 로케일 
        logger.info("Locale : " + Locale.getDefault());
        // 디폴트 설정
        messageSource.setUseCodeAsDefaultMessage(true);
               
        
        // 디폴트 설정이 KOREA 인지 확인 
        assertEquals(Locale.getDefault(), Locale.KOREA);
        
        // 설정 코드 값으로 메시지 확인 
        assertEquals("성공", applicationContext.getMessage("osp.result.success", null, Locale.getDefault()));
    }
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
