package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.Tag;

public class GetLinksByTagControllerTest extends AbstractControllerTest {

	private Link firstLink;
	private Link secondLink;
	private Long sameTagId;

	private static final Logger LOGGER = LoggerFactory.getLogger(GetLinksByTagControllerTest.class);

	@Before
	public void createLinks() {
		firstLink = createRandomLink(FIRST_TEST_LOGIN);
		secondLink = createRandomLink(SECOND_TEST_LOGIN);
		Set<Tag> tags = firstLink.getLinkDetails().getTags();
		for (Tag tag : tags) {
			if (tag.getName().equals("sameTag")) {
				sameTagId = tag.getId();
				break;
			}
		}
	}

	@After
	public void deleteLinks() {
		linkService.deleteLinkById(firstLink.getId());
		linkService.deleteLinkById(secondLink.getId());
	}

	@Test
	public void getLinksByTag() throws Exception {
		LOGGER.info("----------------getLinksByTag()----------------------");

		System.out.println(sameTagId);

		MvcResult result = mockMvc.perform(get("/tag/{tagId}", sameTagId)).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Link> links = Arrays.asList(mapper.readValue(content, Link[].class));

		Assert.assertNotNull(content);
		Assert.assertEquals(links.get(0), firstLink);
		Assert.assertEquals(links.get(1), secondLink);
	}

	@Test
	public void getLinkByExistenTag() throws Exception {
		LOGGER.info("----------------getLinkByExistenTag----------------------");

		mockMvc.perform(get("/tag/{tagId}", 0L)).andExpect(status().isNotFound()).andReturn();

	}
}
