package by.vonotirah.linkscutter.webapp.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.vonotirah.linkscutter.datamodel.Link;

public class DeleteLinkControllerTest extends AbstractControllerTest {

	private Link linkFirstUser;

	private Link linkSecondUser;

	@Before
	public void createLinkForTest() {
		linkFirstUser = createRandomLink("testLogin");
		linkSecondUser = createRandomLink("testLogin2");
	}

	@After
	public void deleteCreatedLinks() {
		if (linkService.chekLinkExistByCode(linkFirstUser.getGenCode())) {
			linkService.deleteLinkById(linkFirstUser.getId());
		}

		linkService.deleteLinkById(linkSecondUser.getId());
	}

	@Test
	public void deleteLinkAsAnon() throws Exception {

		mockMvc.perform(delete("/links/{id}", linkFirstUser.getId())).andExpect(status().isUnauthorized());
	}

	@Test
	public void deleteLink() throws Exception {

		mockMvc.perform(delete("/links/{id}", linkFirstUser.getId()).with(user(userDetails)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteNonExistenLink() throws Exception {

		mockMvc.perform(delete("/links/{id}", 0L).with(user(userDetails))).andExpect(status().isNotFound());
	}

	@Test
	public void deleteForeignLink() throws Exception {

		mockMvc.perform(delete("/links/{id}", linkSecondUser.getId()).with(user(userDetails)))
				.andExpect(status().isLocked());
	}

}
