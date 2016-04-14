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
import by.vonotirah.linkscutter.datamodel.UserAccount;

@Repository
public class LinkDaoImpl extends AbstractDaoImpl<Long, Link> implements LinkDao {

	protected LinkDaoImpl() {
		super(Link.class);
	}

	@Override
	public Link getLinkByCode(final String code) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Link_.genCode), code));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getSingleResult();
	}

	@Override
	public boolean checkCodeExist(final String code) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Link_.genCode), code));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getResultList().size() == 1;
	}

	@Override
	public List<Link> getLinksByTag(final Long tagId) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> linkRoot = criteria.from(Link.class);
		Join<Link, LinkDetails> linkDetailsJoin = linkRoot.join(Link_.linkDetails);
		Join<LinkDetails, Tag> tagJoin = linkDetailsJoin.join(LinkDetails_.tags);
		criteria.select(linkRoot);
		criteria.where(cBuilder.equal(tagJoin.get(Tag_.id), tagId));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Link> getAllLinks() {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		List<Link> results = query.getResultList();
		return results;

	}

	@Override
	public List<Link> getLinksByUser(final UserAccount userAccount) {
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Link> criteria = cBuilder.createQuery(Link.class);
		Root<Link> root = criteria.from(Link.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(Link_.userAccount), userAccount));
		TypedQuery<Link> query = getEntityManager().createQuery(criteria);
		List<Link> results = query.getResultList();
		return results;
	}

}
