package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;

public class GetAllLinksControllerTest extends AbstractControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthControllerTest.class);

	private Link firstLink;

	private Link secondLink;

	// TODO create test for NotFoundException

	@Before
	public void createLinks() {
		firstLink = createRandomLink("testLogin");
		secondLink = createRandomLink("testLogin");
	}

	@After
	public void deleteLinks() {
		linkService.deleteLinkById(firstLink.getId());
		linkService.deleteLinkById(secondLink.getId());
	}

	@Test
	public void getAllLinks() throws Exception {
		LOGGER.info("----------------getAllLinks()----------------------");

		MvcResult result = mockMvc.perform(get("/links/").with(user(userDetails))).andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		List<Link> links = Arrays.asList(mapper.readValue(content, Link[].class));

		Assert.assertNotNull(content);
		Assert.assertEquals(links.get(0), firstLink);
		Assert.assertEquals(links.get(1), secondLink);
	}

	@Test
	public void getAllLinksAsAnon() throws Exception {
		LOGGER.info("----------------getAllLinksAsAnon()----------------------");

		mockMvc.perform(get("/links/")).andExpect(status().isUnauthorized());

	}
}
