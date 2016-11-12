/**
 * 
 */
package com.wiseboys.mmhackathon.beans;

/**
 * General class for sending the response back
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public class StatusResponse {
	private Boolean status;

	/**
	 * Parameter constructor
	 */
	public StatusResponse(Boolean status) {
		this.status = status;
	}
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}	
}
