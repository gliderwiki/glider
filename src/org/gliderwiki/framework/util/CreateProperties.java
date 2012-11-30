/**
 * @FileName  : CreateProperties.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 14. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

/**
 * @author yion
 *
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
 
public class CreateProperties 
{
    public static void main( String[] args )
    { 
    	Properties prop = new Properties();
 
    	try {
    		//set the properties value
    		prop.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
    		prop.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/gliderwiki");
    		prop.setProperty("jdbc.username", "user1234");
    		prop.setProperty("jdbc.password", "pass1234");
 
    		//save properties to project root folder
    		//prop.store(new FileOutputStream("test.properties"), null);
    		   		
    		// 실제 서블릿의 WEB-INF 경로를 구해오는 경우 
    		// getServletConfig().getServletContext().getRealPath("WEB-INF")
    		// 우리 어플리케이션에서 jdbc.properties의 경로 WEB-INF/spring/properties/jdbc/jdbc.properties 
    		ClassLoader loader = Thread.currentThread().getContextClassLoader();
    		File file = new File(loader.getResource("").getFile());
    		System.out.println("file : " + file.toString());
    		
    		String path = file.getParent() + File.separator + "classes" + File.separator;
    		
    		System.out.println("##################################");
    		System.out.println(path);
    		System.out.println("##################################");
    		
    		prop.store(new FileOutputStream(path + "test.properties"), null);
    		
    		Set<Object> keySet = prop.keySet();
    	    
    	    Iterator<Object> iterator = keySet.iterator();
    	    while (iterator.hasNext()) {
    	        String key = (String) iterator.next();
    	        System.out.println(key + "=" + prop.getProperty((String) key));
    	    }
    	    
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    }
}