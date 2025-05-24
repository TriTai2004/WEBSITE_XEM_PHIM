package com.assignment2.dao;

import java.util.List;

import com.assignment2.entity.Like;
import com.assignment2.entity.User;
import com.assignment2.entity.Video;
import com.assignment2.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class LikeDAO {
	static EntityManager entityManager = JpaUtil.getEntityManager();
	
	//them like
	public static int insert(Like like) {
		//B3
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(like);
			entityManager.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	public static int delete(Like like) {
		//B3
		entityManager.getTransaction().begin();
		try {
			entityManager.remove(like);
			entityManager.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	public static Like findByLike(User userId, Video videoId) {
	    String jpql = "SELECT li FROM Like li WHERE li.user.id = :user_id AND li.video.id = :video_id";
	    TypedQuery<Like> query = entityManager.createQuery(jpql, Like.class);
	    query.setParameter("user_id", userId.getId());
	    query.setParameter("video_id", videoId.getId());
	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // không tìm thấy
	    }
	}
	public static List<Like> findByUserId(User userId) {
	    String jpql = "SELECT li FROM Like li WHERE li.user.id = :user_id";
	    TypedQuery<Like> query = entityManager.createQuery(jpql, Like.class);
	    query.setParameter("user_id", userId.getId());
	    return query.getResultList();
	}
	public static List<Like> findByUserAndTitle(User userId, String title) {
	    String jpql = "SELECT li FROM Like li WHERE li.user.id = :user_id and li.video.title like :title";
	    TypedQuery<Like> query = entityManager.createQuery(jpql, Like.class);
	    query.setParameter("title", "%"+title+"%");
	    query.setParameter("user_id", userId.getId());
	    return query.getResultList();
	}
	
	public static List<Like> pagination(int size, int page, int userId, String title){
		String jpql = "select li from Like li where li.user.id = :id and li.video.title like :title";
		TypedQuery<Like> query = entityManager.createQuery(jpql, Like.class);
		query.setParameter("title", "%" + title + "%");
		query.setParameter("id", userId);
		query.setFirstResult((page - 1)*size);
		query.setMaxResults(size);
		return query.getResultList();	
	}

}
