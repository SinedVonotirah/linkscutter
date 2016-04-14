package by.vonotirah.linkscutter.dataaccess.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.TagDao;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.datamodel.Tag_;

@Repository
public class TagDaoImpl extends AbstractDaoImpl<Long, Tag> implements TagDao {

	protected TagDaoImpl() {
		super(Tag.class);
	}

	@Override
	public boolean tagExist(final String name) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Tag> criteria = cBuilder.createQuery(Tag.class);
		Root<Tag> root = criteria.from(Tag.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Tag_.name), name));
		TypedQuery<Tag> query = getEntityManager().createQuery(criteria);
		return query.getResultList().size() == 1;

	}

	@Override
	public Tag gatTagByName(final String name) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Tag> criteria = cBuilder.createQuery(Tag.class);
		Root<Tag> root = criteria.from(Tag.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Tag_.name), name));
		TypedQuery<Tag> query = getEntityManager().createQuery(criteria);
		return query.getSingleResult();
	}
}
