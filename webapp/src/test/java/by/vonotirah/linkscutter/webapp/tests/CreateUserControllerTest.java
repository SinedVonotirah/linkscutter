package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.UserService;

public class CreateUserControllerTest extends AbstractControllerTest {

	@Inject
	private UserService userService;
	private UserAccount userAccount;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthControllerTest.class);

	@Before
	public void createUserAccount() {
		userAccount = getRandomUserAccount();
	}

	@Test
	public void createUser() throws Exception {

		LOGGER.info("----------------createUser()----------------------");

		MvcResult result = mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		UserAccount createdUserAccount = mapper.readValue(content, UserAccount.class);

		Assert.assertEquals(userAccount.getLogin(), createdUserAccount.getLogin());
		Assert.assertEquals(userAccount.getMail(), createdUserAccount.getMail());
		Assert.assertEquals(userAccount.getPassword(), createdUserAccount.getPassword());
		System.out.println(createdUserAccount.getId());
		userService.deleteUser(createdUserAccount);
	}

	@Test
	public void createUserWithExistingLogin() throws Exception {

		LOGGER.info("----------------createUserWithExistingLogin()----------------------");

		userAccount.setLogin(FIRST_TEST_LOGIN);

		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}
}
