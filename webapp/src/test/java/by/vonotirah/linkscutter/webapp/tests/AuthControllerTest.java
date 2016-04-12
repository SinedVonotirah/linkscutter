package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthControllerTest extends AbstractControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthControllerTest.class);

	@Test
	public void loginWithIncorrectCredentials() throws Exception {
		LOGGER.info("----------------loginWithIncorrectCredentials()----------------------");
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "incorrectLogin").param("password", "incorrectPassword"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void loginWithCorrectCredentials() throws Exception {
		LOGGER.info("----------------loginWithCorrectCredentials()----------------------");
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", "testLogin")
				.param("password", "testPassword")).andExpect(status().isOk());
	}

	@Test
	public void logout() throws Exception {
		LOGGER.info("----------------logout()----------------------");
		UserDetails UserDetails = userDetailsService.loadUserByUsername("testLogin");

		mockMvc.perform(get("/logout").with(user(UserDetails))).andExpect(status().isOk());
	}
}
