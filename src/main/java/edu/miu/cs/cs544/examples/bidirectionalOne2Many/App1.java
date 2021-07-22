package edu.miu.cs.cs544.examples.bidirectionalOne2Many;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.miu.cs.cs544.examples.exercise03_1.Car;
import edu.miu.cs.cs544.examples.exercise03_1.Owner;
import utils.HibernateUtils;

public class App1 {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Department.class, Employee.class));
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
			List<Employee> employeeList = session.createQuery("from Employee", Employee.class).list();
			for (Employee e : employeeList) {
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
