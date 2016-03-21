package by.vonotirah.linkscutter.dataaccess.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.LinkDao;
import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.datamodel.LinkDetails_;
import by.vonotirah.linkscutter.datamodel.Link_;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.datamodel.Tag_;

@Repository
public class LinkDaoImpl extends AbstractDaoImpl<Long, Link>implements LinkDao {

	protected LinkDaoImpl() {
		super(Link.class);
	}

	@Override
	public Link getLinkByCode(String code) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Link_.genCode), code));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getSingleResult();
	}

	@Override
	public boolean checkCodeExist(String code) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Link_.genCode), code));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getResultList().size() == 1;
	}

	@Override
	public List<Link> getLinksByTag(String tag) {

		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> linkRoot = criteria.from(Link.class);
		Join<Link, LinkDetails> linkDetailsJoin = linkRoot.join(Link_.linkDetails);
		Join<LinkDetails, Tag> tagJoin = linkDetailsJoin.join(LinkDetails_.tags);
		criteria.select(linkRoot);
		criteria.where(cBuilder.equal(tagJoin.get(Tag_.name), tag));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getResultList();
	}

}
