/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rajkl
 * Entity class for holding manorama edition details
 */
@Entity
@Table(name="tbl_manorama_edition")
public class ManoramaEdition {
	
	@Id
	@GeneratedValue
	private BigInteger id; // Auto generated value in database
	
	private String code = "";
	private String name = "";
	private String webUrl = "";
	private String apiUrl = "";
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the webUrl
	 */
	public String getWebUrl() {
		return webUrl;
	}
	/**
	 * @param webUrl the webUrl to set
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	/**
	 * @return the apiUrl
	 */
	public String getApiUrl() {
		return apiUrl;
	}
	/**
	 * @param apiUrl the apiUrl to set
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

}
