package com.manorama.techspectations.model;

import java.math.BigInteger;

/**
 * Model object to hold manoramo article details
 * @author rajkl
 *
 */

public class ManoramaArticleDetails {

    private BigInteger id;
    private String articleID = "";
    private String title = "";
    private String articleURL = "";
    private String thumbnail = "";
    private ManoramaAuthor authorDetails;
    private String lastModified = "";
    private String content = "";
    private Boolean video = false;
    private String imgWeb = "";
    private String imgMob = "";
    private String imageDescription = "";
    private String shareURL = "";
    private String otherImages = "";
    private String avgRating = "";
    private String[] tags;
    private String[] relatedArticles;
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
     * @return the authorDetails
     */
    public ManoramaAuthor getAuthorDetails() {
        return authorDetails;
    }
    /**
     * @param authorDetails the authorDetails to set
     */
    public void setAuthorDetails(ManoramaAuthor authorDetails) {
        this.authorDetails = authorDetails;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the imageDescription
     */
    public String getImageDescription() {
        return imageDescription;
    }
    /**
     * @param imageDescription the imageDescription to set
     */
    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
    /**
     * @return the shareURL
     */
    public String getShareURL() {
        return shareURL;
    }
    /**
     * @param shareURL the shareURL to set
     */
    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }
    /**
     * @return the otherImages
     */
    public String getOtherImages() {
        return otherImages;
    }
    /**
     * @param otherImages the otherImages to set
     */
    public void setOtherImages(String otherImages) {
        this.otherImages = otherImages;
    }
    /**
     * @return the avgRating
     */
    public String getAvgRating() {
        return avgRating;
    }
    /**
     * @param avgRating the avgRating to set
     */
    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }
    /**
     * @return the tags
     */
    public String[] getTags() {
        return tags;
    }
    /**
     * @param tags the tags to set
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    /**
     * @return the relatedArticles
     */
    public String[] getRelatedArticles() {
        return relatedArticles;
    }
    /**
     * @param relatedArticles the relatedArticles to set
     */
    public void setRelatedArticles(String[] relatedArticles) {
        this.relatedArticles = relatedArticles;
    }

}
