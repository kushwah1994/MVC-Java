package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateInstructorDemo 
{
	public static void main(String[] args) 
	{
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		// create session 
		Session session = factory.getCurrentSession();
		
		try{
					
			// start a transaction
			session.beginTransaction();
			
			// get the ibstructor from db
			int theId=1;
			Instructor teInstructor=session.get(Instructor.class, theId);
			
			// create soe courses
			Course tempCourse1=new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse2=new Course("The Pinball Msterclass");
			
			// add courses to instructor
			teInstructor.add(tempCourse1);
			teInstructor.add(tempCourse2);
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// comit tranaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		}
		finally
		{
			//add clean up code
			session.close();
			
			factory.close();
		}
	}
}
