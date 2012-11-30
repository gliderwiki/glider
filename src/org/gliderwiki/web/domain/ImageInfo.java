/**
 * @FileName  : ImageInfo.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 13.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

/**
 * @author bluepoet
 *
 */
public class ImageInfo {

	String tempImgRoot;

	String realImgRoot;

	int widthSize;

	int heightSize;

	public ImageInfo() {

	}

	public ImageInfo(String tempImgRoot, String realImgRoot) {
		this.tempImgRoot = tempImgRoot;
		this.realImgRoot = realImgRoot;
	}

	public ImageInfo(int widthSize, int heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	/**
	 * @return the tempImgRoot
	 */
	public String getTempImgRoot() {
		return tempImgRoot;
	}

	/**
	 * @param tempImgRoot the tempImgRoot to set
	 */
	public void setTempImgRoot(String tempImgRoot) {
		this.tempImgRoot = tempImgRoot;
	}

	/**
	 * @return the realImgRoot
	 */
	public String getRealImgRoot() {
		return realImgRoot;
	}

	/**
	 * @param realImgRoot the realImgRoot to set
	 */
	public void setRealImgRoot(String realImgRoot) {
		this.realImgRoot = realImgRoot;
	}

	/**
	 * @return the widthSize
	 */
	public int getWidthSize() {
		return widthSize;
	}

	/**
	 * @param widthSize the widthSize to set
	 */
	public void setWidthSize(int widthSize) {
		this.widthSize = widthSize;
	}

	/**
	 * @return the heightSize
	 */
	public int getHeightSize() {
		return heightSize;
	}

	/**
	 * @param heightSize the heightSize to set
	 */
	public void setHeightSize(int heightSize) {
		this.heightSize = heightSize;
	}


}
