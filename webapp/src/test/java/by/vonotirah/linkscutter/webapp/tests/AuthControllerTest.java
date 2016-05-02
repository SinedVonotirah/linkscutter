package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthControllerTest extends AbstractControllerTest {

	@Test
	public void loginWithIncorrectCredentials() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("username", "incorrectLogin").param("password", "incorrectPassword"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void loginWithCorrectCredentials() throws Exception {
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", "testLogin")
				.param("password", "testPassword")).andExpect(status().isOk());
	}

	@Test
	public void logout() throws Exception {
		UserDetails UserDetails = userDetailsService.loadUserByUsername("testLogin");

		mockMvc.perform(get("/logout").with(user(UserDetails))).andExpect(status().isOk());
	}
}
