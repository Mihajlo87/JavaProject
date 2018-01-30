package ikt.learn.VideoKlub.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManager manager;

	static {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("video_klub");
		manager = factory.createEntityManager();
	}

	public static EntityManager getManager() {
		return manager;
	}
}
