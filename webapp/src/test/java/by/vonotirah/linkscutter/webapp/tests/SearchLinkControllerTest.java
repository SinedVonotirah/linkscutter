package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;

public class SearchLinkControllerTest extends AbstractControllerTest {

	private Link link;
	private String searchUrl;

	@Before
	public void createLink() {
		link = createRandomLink("testLogin");
		searchUrl = randomString().concat(link.getGenCode());
	}

	@After
	public void deleteLink() {
		linkService.deleteLinkById(link.getId());
	}

	@Test
	public void searchLink() throws Exception {

		MvcResult result = mockMvc.perform(post("/search").contentType(APPLICATION_JSON_UTF8).content(searchUrl))
				.andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		Link extractedLink = mapper.readValue(content, Link.class);
		Assert.assertEquals(link, extractedLink);
	}

	@Test
	public void searchNonExistenLink() throws Exception {

		mockMvc.perform(post("/search").contentType(APPLICATION_JSON_UTF8).content("000000"))
				.andExpect(status().isNotFound());

	}

}
