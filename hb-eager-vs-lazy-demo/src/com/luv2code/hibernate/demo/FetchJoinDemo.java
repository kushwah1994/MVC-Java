package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import com.mysql.cj.Query;

public class FetchJoinDemo 
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
			
			// get the instructor from db
			int theId=1;
			
		org.hibernate.query.Query<Instructor> query=
					session.createQuery("select i from Instructor i "+
			"JOIN FETCH i.courses "
			+"where i.id=:theInstructorId"
			, Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: "+tempInstructor);
			
						
			// comit tranaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			System.out.println("\nluv2code: The session is now closed!\n");
			
			// since courses are lazy loaded .. this fail
			
			//get course for the instructor
			System.out.println("luv2code: Courses: "+tempInstructor.getCourses());

			
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
