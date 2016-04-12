package by.vonotirah.linkscutter.webapp.tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.webapp.models.LinkModel;

public class CreateLinkControllerTest extends AbstractControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthControllerTest.class);

	@Test
	public void createLinkAsAnon() throws Exception {
		LOGGER.info("----------------createLinkAsAnon()----------------------");

		LinkModel linkModel = getLinkModel();

		mockMvc.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void createLink() throws Exception {
		LOGGER.info("----------------createLink()----------------------");

		LinkModel linkModel = getLinkModel();

		MvcResult result = mockMvc
				.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel))
						.with(user(userDetails)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.url", is(linkModel.getUrl())))
				.andExpect(jsonPath("$.linkDetails.description", is(linkModel.getDescription()))).andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		Link link = mapper.readValue(content, Link.class);
		linkService.deleteLinkById(link.getId());
	}

	@Test
	public void createLinkWithoutUrl() throws Exception {
		LOGGER.info("----------------createLinkWithoutUrl()----------------------");

		LinkModel linkModel = getLinkModel();
		linkModel.setUrl("");

		mockMvc.perform(post("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel))
				.with(user(userDetails))).andExpect(status().isNoContent());
	}

}
