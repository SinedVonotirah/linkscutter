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
import by.vonotirah.linkscutter.service.exceptions.AccountDoesNotExistException;
import by.vonotirah.linkscutter.service.exceptions.AccountExistsException;

@Service
public class UserServiceImpl implements UserService {

	// TODO implements new interface UserService4Tests

	@Inject
	private UserAccountDao userAccountDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public UserAccount createNewUser(UserAccount userAccount) {
		Validate.isTrue(userAccount.getId() == null, "'createNewUser' method did not pass validation");

		if (userAccountDao.chekUserLoginExist(userAccount.getLogin())) {
			throw new AccountExistsException("Login exist");
		}
		if (userAccountDao.chekUserEmailExist(userAccount.getMail())) {
			throw new AccountExistsException("Mail exist");
		}
		userAccountDao.insertEntity(userAccount);
		LOGGER.info("User successfully created");
		return userAccount;
	}

	@Override
	@Transactional
	public UserAccount getUserById(Long id) {
		UserAccount userAccount = userAccountDao.getEntityById(id);
		LOGGER.info("UserAccount with 'ID' - " + id + " successfully extracted");
		return userAccount;
	}

	@Override
	@Transactional
	public UserAccount getUserByLogin(String login) {
		UserAccount userAccount = userAccountDao.getUserByLogin(login);
		if (userAccount == null) {
			throw new AccountDoesNotExistException("UserAccount with Login - " + login + " does not exist");
		}
		LOGGER.info("UserAccount with 'LOGIN' - " + login + " successfully extracted");
		return userAccount;
	}

	@Override
	@Transactional
	public void updateUser(UserAccount userAccount) {
		userAccountDao.updateEntity(userAccount);
		LOGGER.info("UserAccount successfully updated");
	}

	@Override
	@Transactional
	public void deleteUser(UserAccount userAccount) {
		userAccountDao.deleteEntityById(userAccount.getId());
		LOGGER.info("UserAccount with 'ID' - " + userAccount.getId() + " successfully deleted");
	}

}
