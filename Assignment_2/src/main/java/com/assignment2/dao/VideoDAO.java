package com.assignment2.dao;

import java.util.List;

import com.assignment2.entity.Video;
import com.assignment2.util.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VideoDAO {
	static EntityManager entityManager = JpaUtil.getEntityManager();
	//1. lay ds
	public static List<Video> findAll() {
		String jpql = "select v from Video v order by v.id desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		List<Video> videos = query.getResultList();
		
		return videos;
	}
	//2 lay theo id
	
	public static Video findById(Integer id) {
		return entityManager.find(Video.class, id);
	}
	
	//3 them
	public static int insert(Video video) {
		//B3
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(video);
			entityManager.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	//4 sưa
	public static int update(Video video) {
		//B3
		entityManager.getTransaction().begin();
		try {
			entityManager.merge(video);
			entityManager.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	//5 xoa
	public static int delete(Integer id) {
		//B3
		entityManager.getTransaction().begin();
		try {
			Video video = entityManager.find(Video.class, id);
			entityManager.remove(video);
			entityManager.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return 0;
		}
	}
	
	//6 tim videos yeu thich theo user id
	public static List<Video> findVideoByUserId(Integer userId) {
		String jpql = "select li.video from Like li where li.user.id = :userId and li.video.active = true";
		TypedQuery<Video> query =  entityManager.createQuery(jpql, Video.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
	public static List<Video> findVideoByView(){
		String jpql = "select v from Video v where v.active = true order by v.views desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		query.setMaxResults(7);	
		return query.getResultList();
	}
	public static List<Video> findVideoByNew(){
		String jpql = "select v from Video v where v.active = true order by v desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);		
		return query.getResultList();
	}
	public static List<Video> findVideoByLike(){
		String jpql = "SELECT li.video FROM Like  li where li.video.active = true GROUP BY li.video ORDER BY COUNT(li) DESC";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);		
		return query.getResultList();
	}
	
	public static List<Video> pagination(int page, int size, String search){
		String jpql = "select v from Video v where v.title like :search and v.active = true order by v.views desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult((page - 1)*size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	public static List<Video> findVideoByTitile(String title) {
		String jpql = "select v from Video v where v.title like :title and v.active = true";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}
	//7. lay ds video hoat dong
	public static List<Video> findActive(Boolean acctive) {
		String jpql = "select v from Video v where v.active = :active order by v.id desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		query.setParameter("active", acctive);
		return query.getResultList();
	}
	
	// 8. lay ds video theo so luong
	public static List<Video> findViewHasNumber(Integer number) {
		String jpql = "select v from Video v where v.active = true order by v.views desc";
		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
		query.setMaxResults(number);
		return query.getResultList();
	}
	//9. pagination
	public static List<Video> findVideosByPage(int page, int size, String title) {
	    String jpql = "select v from Video v where v.title like :title order by v.id desc";
	    TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);	 
	    query.setParameter("title", "%" + title + "%");
	    query.setFirstResult((page - 1) * size);
	    query.setMaxResults(size);
	    return query.getResultList();
	}
	public static List<Video> findVideosByTitle(String title) {
	    String jpql = "select v from Video v where v.title like :title and v.active = true order by v.id desc";
	    TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);	 
	    query.setParameter("title", "%" + title + "%");
	    return query.getResultList();
	}

}
