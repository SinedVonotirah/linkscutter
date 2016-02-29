package by.vonotirah.linkscutter.tests;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;

public class UserServiceTest extends AbstractServiceTest {

	@Inject
	private UserService userService;
	
	@Inject
	private LinkService linkService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Test
	public void userAccountCRUDTest(){
		final UserAccount userAccount = createRandomUserAccount();
		userService.createNewUser(userAccount);
		
		String login = userAccount.getLogin();
		final UserAccount createdUser = userService.getUserByLogin(login);
		Assert.assertNotNull(createdUser);
		Assert.assertEquals(createdUser.getLogin(), userAccount.getLogin());
		
		createdUser.setLogin(randomString("New Login - "));
		userService.updateUser(createdUser);
		UserAccount updatedUser = userService.getUserById(createdUser.getId());
		Assert.assertEquals(createdUser.getLogin(), updatedUser.getLogin());
		Assert.assertNotEquals(userAccount.getLogin(), updatedUser.getLogin());
		
		userService.deleteUser(updatedUser);
		Assert.assertNull(userService.getUserById(userAccount.getId()));
	}
	
	@Test
	public void linkCRUDTest(){
		final UserAccount userAccount = createRandomUserAccount();
		userService.createNewUser(userAccount);
		linkService.createNewLink(userAccount, "asdasdasd");
	}
	
}
