package edu.miu.cs.cs544.examples.exercise03_1;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

public class App {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Car.class, Owner.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Car car1 = new Car();
			Car car2 = new Car();

			Owner owner1 = new Owner();
			owner1.setName("Jorge");
			owner1.setAddress("102W Grimes avenue");

			Owner owner2 = new Owner();
			owner2.setName("Maria");
			owner2.setAddress("Diag 28 55 88");

			car1.setOwner(owner1);
			car2.setOwner(owner2);

			session.persist(car1);
			session.persist(car2);

			car1.setMaker("Ferrari");
			car1.setModel("Spider");
			car1.setYear((short) 1994);

			car2.setMaker("Lamborgini");
			car2.setModel("Aventador");
			car2.setYear((short) 2002);

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
