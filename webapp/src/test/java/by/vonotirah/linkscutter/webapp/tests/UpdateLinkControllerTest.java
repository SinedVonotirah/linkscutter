package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.webapp.models.LinkModel;

public class UpdateLinkControllerTest extends AbstractControllerTest {

	private LinkModel linkModel;

	private Link firstLink;

	private Link secondLink;

	@Before
	public void createLinks() {
		linkModel = getLinkModel();
		firstLink = createRandomLink(FIRST_TEST_LOGIN);
		secondLink = createRandomLink(SECOND_TEST_LOGIN);
	}

	@After
	public void deleteLink() {
		linkService.deleteLinkById(firstLink.getId());
		linkService.deleteLinkById(secondLink.getId());
	}

	@Test
	public void updateLink() throws Exception {

		linkModel.setId(firstLink.getId());

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel))
				.with(user(userDetails))).andExpect(status().isOk());

		Link updatedLink = linkService.getLinkById(firstLink.getId());
		Assert.assertNotEquals(firstLink.getLinkDetails().getDescription(),
				updatedLink.getLinkDetails().getDescription());
		Assert.assertNotEquals(firstLink.getLinkDetails().getTags(), updatedLink.getLinkDetails().getTags());

	}

	@Test
	public void updateLinkAsAnon() throws Exception {

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void updateForeignLink() throws Exception {

		linkModel.setId(secondLink.getId());

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel))
				.with(user(userDetails))).andExpect(status().isLocked());
	}

	@Test
	public void updateLinkWithoutId() throws Exception {

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(linkModel))
				.with(user(userDetails))).andExpect(status().isBadRequest());
	}
}
