/**
 * 
 */
package com.wiseboys.mmhackathon.schedulers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



/**
 * Start quartz 
 * @author Irudaya Raj
 * Nov 12, 2016 2016
 */
public class QuartzListener implements ServletContextListener {

	
	Scheduler scheduler = null;

	/**
	 * Start notification cron job 
	 */
    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
            System.out.println("Context Initialized");
            
            try {
                    // Setup the Job class and the Job group
                    JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity(
                                    "CronQuartzJob", "Group").build();

                    // Create a Trigger that fires every 5 minutes.
                    Trigger trigger =TriggerBuilder.newTrigger()
                    .withIdentity("TriggerName", "Group")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/60 * * * * ?"))
                    .build();

                    // Setup the Job and Trigger with Scheduler & schedule jobs
                    scheduler = new StdSchedulerFactory().getScheduler();
                    scheduler.start();
                    scheduler.scheduleJob(job, trigger);
            }
            catch (SchedulerException e) {
                    e.printStackTrace();
            }
    }

    /**
	 * Stop notification cron job
	 */
    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
            System.out.println("Context Destroyed");
            try 
            {
                    scheduler.shutdown();
            } 
            catch (SchedulerException e) 
            {
                    e.printStackTrace();
            }
    }
	
}
