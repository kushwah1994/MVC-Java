package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCoursesDemo 
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
			// create the objects
			Instructor tempInstructor=
					new Instructor("Susan", "Public", "susan.public@luv2code.com");
			
			InstructorDetail tempInstructorDetail=
					new InstructorDetail(
							"http://www.youtube.com",
							"Video Games");
			
			// associte the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			//note: this will Also save the details object
			// because of CascadeType.All
			
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
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
