package com.assignment2.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment_2");
	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("Assignment_2");
		}
		return factory.createEntityManager();
	}
}
