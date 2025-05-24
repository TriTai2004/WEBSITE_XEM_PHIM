package com.assignment2.dao;

import java.util.List;

import com.assignment2.entity.User;
import com.assignment2.entity.Video;
import com.assignment2.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDAO {
	static EntityManager entityManager = JpaUtil.getEntityManager();
	
	//kiem tra user co it nhat 1 luot like
	public static List<User> findUserHasLike() {
		String jpqlString = "select u from User u where u.likes is not empty";
		TypedQuery<User> query = entityManager.createQuery(jpqlString, User.class);
		return query.getResultList();
	}
	public static int update(User user) {
	    EntityManager em = JpaUtil.getEntityManager(); // tạo EntityManager mới
	    EntityTransaction tx = em.getTransaction();
	    try {
	        tx.begin();
	        em.merge(user);
	        tx.commit();
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (tx.isActive()) tx.rollback();
	        return 0;
	    } finally {
	        em.close();
	    }
	}

	//find all
	public static List<User> findAll() {
		
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
				
	} 
	
	//find by id
	public static User findById(Integer id) {
		return entityManager.find(User.class, id);
	}
	
	//find by email
	public static User findByEmail(String email) {
		String jpql = "select u from User u where u.email = :email ";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("email", email);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	//finde login
	public static List<User> findByUser(String email, String password) {
	    String jpql = "select u from User u where u.email = :email and u.password = :password";
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    query.setParameter("email", email);
	    query.setParameter("password", password);
	    return query.getResultList();
	}

	//find by id or email
	public static User findByIdOrEmail(String idOrEmail) {
		String jpql = "select u from User u where u.email = :email or u.id = :id";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("email", idOrEmail);
		try {
			Integer id = Integer.parseInt(idOrEmail);
			System.out.println(id);
			query.setParameter("id", id);
		} catch (Exception e) {
			// TODO: handle exception
			query.setParameter("id", 0);
			
		}
		return query.getSingleResult();
	}
	
	//insert
	public static int insert(User user) {
		//B3
		EntityManager e = JpaUtil.getEntityManager();
		e.getTransaction().begin();
		try {
			e.persist(user);
			e.getTransaction().commit();
			return 1;
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			e.getTransaction().rollback();
			return 0;
		}finally {
			e.close();
		}
	}
	
	public static List<User> pagination(int page, int size, String search){
		String jpql = "select u from User u where u.email like :search  or u.fullName like :search order by u.id desc";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult((page - 1)*size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	public static List<User> searchUser(String search){
		String jpql = "select u from User u where u.email like :search  or u.fullName like :search order by u.id desc";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("search", "%" + search + "%");
		return query.getResultList();
	}
	
}
