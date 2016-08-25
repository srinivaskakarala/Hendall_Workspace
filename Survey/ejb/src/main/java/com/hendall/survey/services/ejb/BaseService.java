package com.hendall.survey.services.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseService {

	@PersistenceContext(unitName = "mysql")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
