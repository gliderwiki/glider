/**
 * @FileName  : ImageThumbnail.java
 * @Project   : glider
 * @Date      : 2013. 3. 20. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.mock.framework;

/**
 * @author yion
 *
 */


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageThumbnail {

	public ImageThumbnail(){
	}
	
	public static void createImage(String loadFile, String saveFile, int zoom) throws IOException{
		File save = new File(saveFile);
		FileInputStream fis = new FileInputStream(loadFile);
		BufferedImage im = ImageIO.read(fis);
		
		if (zoom<=0) zoom = 1;
		
		int width = im.getWidth() / zoom;
		int height = im.getHeight() / zoom;
		
		BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = thumb.createGraphics();
		
		g2.drawImage(im, 0, 0, width, height, null);
		ImageIO.write(thumb, "jpg", save);
		
	}
	
	public static void main(String args[]){
		String loadFile = "c://temp/3.jpg";
		String saveFile = "c://temp/r3.jpg";
		int zoom = 2;
		
		try {
			ImageThumbnail.createImage(loadFile, saveFile, zoom);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}