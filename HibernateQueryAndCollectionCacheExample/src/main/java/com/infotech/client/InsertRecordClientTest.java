package com.infotech.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.entities.Department;
import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class InsertRecordClientTest {

	public static void main(String[] args) {
		Transaction tx =  null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Sean Murphy");
			employee1.setUsername("seanm");
			employee1.setPassword("pass#123");
			employee1.setAccessLevel(1);
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Barry Johnson");
			employee2.setUsername("barryj");
			employee2.setPassword("barry@123");
			employee2.setAccessLevel(1);
			
			Department department1 = new Department();
			department1.setDeptName("IT");
			department1.getEmployees().add(employee1);
			department1.getEmployees().add(employee2);
			
			employee1.setDepartment(department1);
			employee2.setDepartment(department1);
			
			session.persist(department1);
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
}