package by.vonotirah.linkscutter.dataaccess.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.LinkDetailsDao;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.datamodel.LinkDetails_;

@Repository
public class LinkDetailsDaoImpl extends AbstractDaoImpl<Long, LinkDetails>implements LinkDetailsDao {

	protected LinkDetailsDaoImpl() {
		super(LinkDetails.class);
	}

	public List<LinkDetails> getLinkDetailsByTag(String tag) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<LinkDetails> criteria = cBuilder.createQuery(LinkDetails.class);
		Root<LinkDetails> root = criteria.from(LinkDetails.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(LinkDetails_.tags), tag));
		TypedQuery<LinkDetails> query = getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
}
