/**
 * 
 */
package com.wiseboys.mmhackathon.schedulers;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quarz job for watching travel notifications
 * 
 * @author Irudaya Raj Nov 12, 2016 2016
 */
public class QuartzJob implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Java web application + Quartz 2.2.1");
	}
}
