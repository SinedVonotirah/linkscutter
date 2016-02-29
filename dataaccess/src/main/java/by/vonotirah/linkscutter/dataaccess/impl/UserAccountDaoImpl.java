package by.vonotirah.linkscutter.dataaccess.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.UserAccountDao;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.datamodel.UserAccount_;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<Long, UserAccount> implements UserAccountDao {

	protected UserAccountDaoImpl(){
		super(UserAccount.class);		
	}
	
	@Override
	public UserAccount getUserByLogin(String login){
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAccount> criteria = cBuilder.createQuery(UserAccount.class);
		Root<UserAccount> root = criteria.from(UserAccount.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(UserAccount_.login), login));
		TypedQuery<UserAccount> query = getEntityManager().createQuery(criteria);
		return query.getSingleResult();
	}
	
	@Override
	public UserAccount getUserByMail(String mail){
		CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAccount> criteria = cBuilder.createQuery(UserAccount.class);
		Root<UserAccount> root = criteria.from(UserAccount.class);
		criteria.select(root);
		criteria.where(cBuilder.equal(root.get(UserAccount_.mail), mail));
		TypedQuery<UserAccount> query = getEntityManager().createQuery(criteria);
		return query.getSingleResult();		
	}
}
