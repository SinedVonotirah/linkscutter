package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.UserService;

public class CreateUserControllerTest extends AbstractControllerTest {

	@Inject
	private UserService userService;
	private UserAccount userAccount;

	@Before
	public void createUserAccount() {
		userAccount = getRandomUserAccount();
	}

	@Test
	public void createUser() throws Exception {
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
		userAccount.setLogin(firstUserAccount4Tests.getLogin());

		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isConflict());
	}

	@Test
	public void createUserWithExistingMail() throws Exception {
		userAccount.setLogin(firstUserAccount4Tests.getLogin());

		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isConflict());
	}

	@Test
	public void createUserWithEmptyLogin() throws Exception {
		userAccount.setLogin(null);
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithEmptyMail() throws Exception {
		userAccount.setMail(null);
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithEmptyPassword() throws Exception {
		userAccount.setPassword(null);
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithShortLogin() throws Exception {
		userAccount.setLogin("");
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithShortMail() throws Exception {
		userAccount.setMail("");
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithShortPassword() throws Exception {
		userAccount.setPassword("");
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}

	@Test
	public void createUserWithWrongEmail() throws Exception {
		userAccount.setMail("wrongEmailPattern");
		mockMvc.perform(post("/registration/").contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(userAccount))).andExpect(status().isBadRequest());
	}
}
