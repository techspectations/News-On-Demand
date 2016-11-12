/**
 * 
 */
package com.manorama.techspectations.model;

import java.math.BigInteger;


/**
 * Bean to hold article summary details
 * @author rajkl
 *
 */

public class ManoramaArticle {

	private BigInteger id; // Auto generated value in database
	private String articleID = "";
	private String title = "";
	private String articleURL = "";
	private String thumbnail = "";
	private String imgWeb = "";
	private String imgMob = "";
	private String lastModified = "";
	private int otherImages =0;
	private Boolean video = false;
	private String articleSection = "";
	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**
	 * @return the articleID
	 */
	public String getArticleID() {
		return articleID;
	}
	/**
	 * @param articleID the articleID to set
	 */
	public void setArticleID(String articleID) {
		this.articleID = articleID;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the articleURL
	 */
	public String getArticleURL() {
		return articleURL;
	}
	/**
	 * @param articleURL the articleURL to set
	 */
	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}
	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * @return the imgWeb
	 */
	public String getImgWeb() {
		return imgWeb;
	}
	/**
	 * @param imgWeb the imgWeb to set
	 */
	public void setImgWeb(String imgWeb) {
		this.imgWeb = imgWeb;
	}
	/**
	 * @return the imgMob
	 */
	public String getImgMob() {
		return imgMob;
	}
	/**
	 * @param imgMob the imgMob to set
	 */
	public void setImgMob(String imgMob) {
		this.imgMob = imgMob;
	}
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	/**
	 * @return the otherImages
	 */
	public int getOtherImages() {
		return otherImages;
	}
	/**
	 * @param otherImages the otherImages to set
	 */
	public void setOtherImages(int otherImages) {
		this.otherImages = otherImages;
	}
	/**
	 * @return the video
	 */
	public Boolean getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(Boolean video) {
		this.video = video;
	}
	/**
	 * @return the articleSection
	 */
	public String getArticleSection() {
		return articleSection;
	}
	/**
	 * @param articleSection the articleSection to set
	 */
	public void setArticleSection(String articleSection) {
		this.articleSection = articleSection;
	}	
	
	
}
