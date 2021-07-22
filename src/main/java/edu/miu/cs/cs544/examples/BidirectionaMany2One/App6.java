package edu.miu.cs.cs544.examples.BidirectionaMany2One;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

public class App6 {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils
				.getSessionFactory(Arrays.asList(Department.class, Employee.class, Office.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Department department1 = new Department();
			department1.setName("sales");

			Employee employee1 = new Employee("Jorge", department1);
			Employee employee2 = new Employee("Sebastian", department1);
			Employee employee3 = new Employee("Naem", department1);

			department1.addEmployee(employee1);
			department1.addEmployee(employee2);
			department1.addEmployee(employee3);

			Office office1 = new Office(12, 140);
			office1.addEmployee(employee1);
			office1.addEmployee(employee2);

			employee1.setOffice(office1);
			employee2.setOffice(office1);

			Office office2 = new Office(13, 142);
			office2.addEmployee(employee3);

			employee3.setOffice(office2);

			session.persist(department1);

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
			List<Department> deptList = session.createQuery("from Department", Department.class).list();
			for (Department e : deptList) {
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
