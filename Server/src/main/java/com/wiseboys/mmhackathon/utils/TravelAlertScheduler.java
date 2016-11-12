/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * Run a scheduler class to check if any user has planned for a travel.
 * We need to send out travel alerts asking to save articles in offline or not.
 * @author Irudaya Raj Nov 11, 2016 2016
 */
@Component
public class TravelAlertScheduler {

	public TravelAlertScheduler() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("Hello World!  MyJob is executing.");
    }
    
}
