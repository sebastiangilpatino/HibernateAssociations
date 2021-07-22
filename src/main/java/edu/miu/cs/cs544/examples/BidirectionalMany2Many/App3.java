package edu.miu.cs.cs544.examples.BidirectionalMany2Many;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

public class App3 {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Course.class, Student.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Course course1 = new Course();
			course1.setName("Mpp");
			session.persist(course1);

			Course course2 = new Course();
			course2.setName("EA");
			session.persist(course2);

			Course course3 = new Course();
			course3.setName("WAA");
			session.persist(course3);

			Student student1 = new Student();
			student1.setFirstName("Jorge");
			student1.setLastName("Gil");
			session.persist(student1);

			Student student2 = new Student();
			student2.setFirstName("Maria");
			student2.setLastName("Estrada");
			session.persist(student2);

			List<Student> mppList = Arrays.asList(student1, student2);
			List<Student> eaList = Arrays.asList(student1);
			List<Student> waaList = Arrays.asList(student2);

			course1.setStudents(mppList);
			course2.setStudents(eaList);
			course3.setStudents(waaList);

			List<Course> student1Courses = Arrays.asList(course1, course2);
			List<Course> student2Courses = Arrays.asList(course1, course3);

			student1.setCourses(student1Courses);
			student2.setCourses(student2Courses);

			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all persons
			List<Course> courseList = session.createQuery("from Course", Course.class).list();
			for (Course e : courseList) {
				System.out.println(e);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		// Close the SessionFactory (not mandatory)
		sessionFactory.close();

	}

}
