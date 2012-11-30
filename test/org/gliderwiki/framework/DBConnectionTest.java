package org.gliderwiki.framework;
import static org.junit.Assert.*;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * MySQL 연결 여부를 테스트 한다. 
 * 터미널 연결이 안된 상태에서는 Maven install Error 가 발생하므로 일단 이그노어 한다. 
 * 
 * @author yion
 * @version   1.0
 * @see 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext-*.xml",
"classpath:/spring/config/servlet-*.xml" })
@DirtiesContext
public class DBConnectionTest {

    @Autowired 
    private BasicDataSource defaultDataSource;
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Ignore
    public void testMySQLConnect() {
        try {
            //System.out.println("***********" + defaultDataSource.getConnection());
            assertNotNull(defaultDataSource.getConnection());
        }
        catch ( SQLException e ) {
            e.printStackTrace();
            fail();
        }
    }

}
