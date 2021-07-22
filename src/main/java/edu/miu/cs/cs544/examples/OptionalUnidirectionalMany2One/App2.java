package edu.miu.cs.cs544.examples.OptionalUnidirectionalMany2One;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

public class App2 {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class, Publisher.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Publisher publisher1 = new Publisher();
			publisher1.setName("Bloomsbury");
			session.persist(publisher1);

			Book book1 = new Book(12345, "Harry Potter 1", "JK Rowling", publisher1);
			session.persist(book1);
			Book book2 = new Book(67890, "Harry Potter 2", "JK Rowling", publisher1);
			session.persist(book2);
			Book book3 = new Book(78945, "Harry Potter 3", "JK Rowling", publisher1);
			session.persist(book3);

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
			List<Book> bookList = session.createQuery("from Book", Book.class).list();
			for (Book e : bookList) {
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
