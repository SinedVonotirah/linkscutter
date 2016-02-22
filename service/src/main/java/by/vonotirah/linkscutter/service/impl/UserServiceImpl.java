package by.vonotirah.linkscutter.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.UserAccountDao;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserAccountDao userAccountDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Override
	@Transactional
	public void createNewUser(UserAccount userAccount){
		Validate.isTrue(userAccount.getId() == null, "'createNewUser' method did not pass validation");
		userAccountDao.insertEntity(userAccount);		
		LOGGER.info("User successfully created");
	}
	
	@Override
	@Transactional
	public UserAccount getUserByLogin(String login){
		UserAccount userAccount = userAccountDao.getUserByLogin(login);
		LOGGER.info("UserAccount with 'LOGIN' - " +login+ " successfully extracted");
		return userAccount;
	}
	
	@Override
	@Transactional
	public void updateUser(UserAccount userAccount){
		userAccountDao.updateEntity(userAccount);
		LOGGER.info("UserAccount successfully updated");
	}
	
	@Override
	@Transactional
	public UserAccount getUserById(Long id){
		UserAccount userAccount = userAccountDao.getEntityById(id);
		LOGGER.info("UserAccount with 'ID' - " +id+ " successfully extracted");
		return userAccount;		
	}
	
	@Override
	@Transactional
	public void deleteUser(UserAccount userAccount){
		userAccountDao.deleteEntityById(userAccount.getId());
		LOGGER.info("UserAccount with 'ID' - " +userAccount.getId()+ " successfully deleted");		
	}
}
