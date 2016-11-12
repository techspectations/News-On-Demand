/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wiseboys.mmhackathon.beans.Student;

/**
 * @author rajkl
 *
 */
public class DemoFirst {

	
	  public static void main(String[] args) {
		  
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();
	         
	        Student student = new Student();
	        student.setFirstName("Bob");
	        student.setAge(26);
	         
	        session.save(student);
	        session.getTransaction().commit();
	         
	        session.close();
	 
	    }
	
}
