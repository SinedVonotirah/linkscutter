package by.vonotirah.linkscutter.dataaccess.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.linkscutter.dataaccess.AbstractDao;

public class AbstractDaoImpl<ID, Entity> implements AbstractDao<ID, Entity> {

	private EntityManager entityManager;
	private final Class<Entity> entityClass;
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoImpl.class);

	protected AbstractDaoImpl(final Class<Entity> entityClass) {
		Validate.notNull(entityClass, "entityClass could not be a null");
		this.entityClass = entityClass;
	}
	
	@PersistenceContext
	protected void setEntityManager(final EntityManager entityManager){
		LOGGER.info("Set EntityManager {} to class {}", entityManager.hashCode(), getClass().getName());
		this.entityManager = entityManager;		
	}
	
	public EntityManager getEntityManager(){
		return entityManager;		
	}
	
	public Class<Entity> getEntityClass(){
		return entityClass;		
	}	
	
	@Override
	public Entity getEntityById(ID id){
		return entityManager.find(getEntityClass(), id);		
	}
	
	@Override
	public Entity insertEntity(final Entity entity){
		entityManager.persist(entity);
		return entity;		
	}
	
	@Override
	public Entity updateEntity(Entity entity){
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;		
	}
	
	@Override
	public void deleteEntityById(ID id){
		entityManager.remove(entityManager.find(getEntityClass(), id));		
	}
	
	@Override
	public void deleteAllEntity(){
		final Query query = entityManager.createQuery("delete from " + getEntityClass().getSimpleName());
		query.executeUpdate();
		entityManager.flush();
	}
	
	public List<Entity> getAllEntity(){
		final CriteriaQuery<Entity> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
		query.from(getEntityClass());
		final List<Entity> list = entityManager.createQuery(query).getResultList();
		return list;		
	}
	
}
