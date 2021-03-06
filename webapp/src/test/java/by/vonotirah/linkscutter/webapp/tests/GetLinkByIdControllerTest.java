package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;

public class GetLinkByIdControllerTest extends AbstractControllerTest {

	private Link link;

	@Before
	public void createLink() {
		link = createRandomLink(firstUserAccount4Tests);
	}

	@After
	public void deleteLink() {
		linkService.deleteLinkById(link.getId());
	}

	@Test
	public void getLinkById() throws Exception {

		MvcResult result = mockMvc.perform(get("/link/{id}", link.getId())).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		Link extractedLink = mapper.readValue(content, Link.class);
		Assert.assertEquals(link, extractedLink);
	}

	@Test
	public void getNonExistingLinkById() throws Exception {

		mockMvc.perform(get("/link/{id}", 0L)).andExpect(status().isNotFound());

	}

}
