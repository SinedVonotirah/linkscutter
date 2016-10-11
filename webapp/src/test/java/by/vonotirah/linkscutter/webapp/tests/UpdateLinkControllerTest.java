package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.vonotirah.linkscutter.datamodel.Link;

public class UpdateLinkControllerTest extends AbstractControllerTest {

	private Link link;

	private Link firstLink;

	private Link secondLink;

	@Before
	public void createLinks() {
		link = getRandomLink();
		firstLink = createRandomLink(firstUserAccount4Tests);
		secondLink = createRandomLink(secondUserAccount4Tests);
	}

	@After
	public void deleteLink() {
		linkService.deleteLinkById(firstLink.getId());
		linkService.deleteLinkById(secondLink.getId());
	}

	@Test
	public void updateLink() throws Exception {

		link.setId(firstLink.getId());

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link))
				.with(user(userDetails))).andExpect(status().isOk());

		Link updatedLink = linkService.getLinkById(firstLink.getId());
		Assert.assertNotEquals(firstLink.getLinkDetails().getDescription(),
				updatedLink.getLinkDetails().getDescription());
		Assert.assertNotEquals(firstLink.getLinkDetails().getTags(), updatedLink.getLinkDetails().getTags());

	}

	@Test
	public void updateLinkAsAnon() throws Exception {

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link)))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void updateForeignLink() throws Exception {

		link.setId(secondLink.getId());

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link))
				.with(user(userDetails))).andExpect(status().isLocked());
	}

	@Test
	public void updateLinkWithoutId() throws Exception {

		mockMvc.perform(put("/links/").contentType(APPLICATION_JSON_UTF8).content(convertObjectToJsonBytes(link))
				.with(user(userDetails))).andExpect(status().isBadRequest());
	}
}
