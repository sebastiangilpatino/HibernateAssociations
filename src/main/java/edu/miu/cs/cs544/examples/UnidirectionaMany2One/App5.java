package edu.miu.cs.cs544.examples.UnidirectionaMany2One;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

public class App5 {

	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Customer.class, Reservation.class, Book.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Customer customer1 = new Customer();
			customer1.setName("Jorge");

			Reservation reservation1 = new Reservation();
			reservation1.setDate(LocalDate.of(2020, 1, 25));

			Reservation reservation2 = new Reservation();
			reservation2.setDate(LocalDate.of(2021, 2, 2));

			Reservation reservation3 = new Reservation();
			reservation3.setDate(LocalDate.now());

			Book book1 = new Book(1234, "Harry Potter 1", "JK Rowling");
			Book book2 = new Book(5678, "Harry Potter 2", "JK Rowling");
			Book book3 = new Book(9012, "Harry Potter 3", "JK Rowling");

			// many reservations one book
			reservation1.setBook(book1);
			reservation2.setBook(book2);
			reservation3.setBook(book3);

			customer1.addReservation(reservation1);
			customer1.addReservation(reservation2);
			customer1.addReservation(reservation3);

			session.persist(customer1);

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
			List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
			for (Customer e : customerList) {
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
