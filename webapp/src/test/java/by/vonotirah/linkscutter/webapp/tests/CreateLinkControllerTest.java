package by.vonotirah.linkscutter.webapp.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;

public class CreateLinkControllerTest extends AbstractControllerTest {

	@Test
	public void createLinkAsAnon() throws Exception {
		Link link = getRandomLink();

		mockMvc.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void createLink() throws Exception {
		Link link = getRandomLink();

		MvcResult result = mockMvc
				.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link))
						.with(user(userDetails)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.url", is(link.getUrl())))
				.andExpect(jsonPath("$.linkDetails.description", is(link.getLinkDetails().getDescription())))
				.andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		Link linkFromResponse = mapper.readValue(content, Link.class);
		linkService.deleteLinkById(linkFromResponse.getId());
	}

	@Test
	public void createLinkWithoutUrl() throws Exception {
		Link link = getRandomLink();
		link.setUrl(null);

		mockMvc.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link))
				.with(user(userDetails))).andExpect(status().isBadRequest());
	}

}
