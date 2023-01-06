package com.springboot.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Customer;
import com.springboot.sorting.SortOrder;

import jakarta.persistence.EntityManager;

@Repository
@Primary
public class JpaCustomerDao implements CustomerDao {

	private EntityManager entityManager;
	
	@Autowired
	public JpaCustomerDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		return entityManager.createNativeQuery("SELECT * FROM customer", Customer.class).getResultList();
	}

	@Override
	public List<Customer> getAll(SortOrder sortOrder) {
		// sortOrder#sortParams{new SortParam(first_name, true), new SortParam(last_name, false)}
		Session session = entityManager.unwrap(Session.class);

		return session.createNativeQuery("SELECT * FROM customer" + sortOrder.getSqlOrder(),Customer.class)
				.getResultList();
	}

//	@Override
//	public List<Customer> getAll(SortOrder sortOrder, int offset, int recordsPerPage) {
//		final String sql = "SELECT * FROM customer " + sortOrder.getSqlOrder() + " LIMIT :offset, :rowcount";
//		return sessionFactory.getCurrentSession()
//					.createNativeQuery(sql, Customer.class)
//					.setParameter("offset", offset, IntegerType.INSTANCE)
//					.setParameter("rowcount", recordsPerPage, IntegerType.INSTANCE)
//					.getResultList();
//	}
//
//	@Override
//	public int countTotalRecords() {
//		Session session = sessionFactory.getCurrentSession();
//		// INT >>> BigInteger
//		// Number >>> BigDecimal
//		return (int)session.createNativeQuery("SELECT COUNT(*) counter FROM customer")
//					.addScalar("counter", IntegerType.INSTANCE)
//					.uniqueResult();
//	}

	@Override
	public Customer get(int id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public void save(Customer customer) {
		System.out.println("helo>>>>>>>>>>>"+customer);
		Customer newone = entityManager.merge(customer);
		System.out.println("newone "+newone);
		customer.setId(newone.getId());
	}

	@Override
	public void delete(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.createQuery("DELETE FROM Customer WHERE id = :id")
				      .setParameter("id", id)
				      .executeUpdate();
	}

	@Override
	public List<Customer> search(String keyword) {
		// search by firstName || lastName with case insensitive
		Session session = entityManager.unwrap(Session.class);

		// additional: mark insensitive
		String sql = "SELECT * FROM customer \n"
				+ "WHERE first_name LIKE :keyword\n"
				+ "OR last_name LIKE :keyword";

		return session.createNativeQuery(sql, Customer.class)
			   .setParameter("keyword", "%" + keyword + "%")
			   .getResultList();
	}

}