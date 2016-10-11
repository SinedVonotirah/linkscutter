package by.vonotirah.linkscutter.tests;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.TagService;
import by.vonotirah.linkscutter.service.UserService;
import by.vonotirah.linkscutter.service.exceptions.LinkNotFoundException;

public class LinkServiceTest extends AbstractServiceTest {

	@Inject
	private UserService userService;

	@Inject
	private LinkService linkService;

	@Inject
	private LinkDetailsService linkDetailsService;

	@Inject
	private TagService tagService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceTest.class);

	@Test(expected = LinkNotFoundException.class)
	public void linkCrudTest() {
		UserAccount userAccount = userService.getUserById(1L);

		// Create new Link for UserAccount with ID = 1

		Link link = getRandomLink();
		Link newLink = linkService.createNewLink(link, userAccount);

		// Get Link by GenCode
		Link extractedLink = linkService.getLinkByCode(newLink.getGenCode());
		Assert.assertNotNull(extractedLink);
		Assert.assertEquals(newLink.getGenCode(), extractedLink.getGenCode());
		Assert.assertEquals(newLink.getUrl(), extractedLink.getUrl());
		Assert.assertEquals(newLink.getLinkDetails().getDescription(), extractedLink.getLinkDetails().getDescription());
		Assert.assertEquals(newLink.getLinkDetails().getTags(), extractedLink.getLinkDetails().getTags());

		// Update Link by UserAccount with ID = 1
		Link linkForUpdate = getRandomLink();
		linkForUpdate.setId(extractedLink.getId());
		linkForUpdate.setUrl(extractedLink.getUrl());
		linkDetailsService.updateLinkDetails(linkForUpdate);
		Link updatedLink = linkService.getLinkByCode(extractedLink.getGenCode());
		Assert.assertNotNull(updatedLink);
		Assert.assertEquals(updatedLink.getGenCode(), extractedLink.getGenCode());
		Assert.assertEquals(updatedLink.getUrl(), extractedLink.getUrl());
		Assert.assertNotEquals(updatedLink.getLinkDetails().getDescription(),
				extractedLink.getLinkDetails().getDescription());
		Assert.assertNotEquals(updatedLink.getLinkDetails().getTags(), extractedLink.getLinkDetails().getTags());

		// Delete Link by ID
		linkService.deleteLinkById(updatedLink.getId());
		Assert.assertNull(linkService.getLinkById(updatedLink.getId()));
		Assert.assertNull(linkDetailsService.getLinkDetailsById(updatedLink.getId()));

	}

	@Test
	public void getAllLinksByUserTest() {
		UserAccount userAccount = userService.getUserById(1L);

		// Create n links for UserAccount with ID = 1
		int linksCount = randomInteger(3, 5);
		for (int i = 0; i < linksCount; i++) {
			Link link = getRandomLink();

			linkService.createNewLink(link, userAccount);
		}

		// Get All Links by UserAccount with ID = 1
		List<Link> extractedLinkList = linkService.getLinksByUser(userAccount);
		for (Link extractedLink : extractedLinkList) {
			Assert.assertEquals(extractedLink.getUserAccount(), userAccount);
			linkService.deleteLinkById(extractedLink.getId());
		}

	}

	@Test
	public void getLinksByTagTest() {
		UserAccount userAccount = userService.getUserById(1L);
		Tag sameTag = new Tag();
		sameTag.setName(randomString("Tag"));
		List<Link> createdLinks = new ArrayList<Link>();

		// Create n links for UserAccount with ID = 1 and with the same tag

		int randomLinksCount = randomInteger(3, 5);
		for (int i = 0; i < randomLinksCount; i++) {
			Link link = getRandomLink();
			link.getLinkDetails().getTags().add(sameTag);
			Link createdLink = linkService.createNewLink(link, userAccount);
			createdLinks.add(linkService.getLinkByCode(createdLink.getGenCode()));
		}

		// Get links by sameTag

		Tag tag = tagService.getTagByName(sameTag.getName());
		List<Link> extractedLinks = linkService.getLinksByTag(tag.getId());
		Assert.assertEquals(createdLinks, extractedLinks);

		for (Link link : createdLinks) {
			linkService.deleteLinkById(link.getId());
		}
	}

	@Test
	public void getLinksCountByUser() {
		UserAccount userAccount = userService.getUserById(1L);

		List<Link> createdLinks = new ArrayList<Link>();

		// Create n links for UserAccount with ID = 1

		int randomLinksCount = randomInteger(3, 5);
		for (int i = 0; i < randomLinksCount; i++) {
			Link link = getRandomLink();
			Link createdLink = linkService.createNewLink(link, userAccount);
			createdLinks.add(linkService.getLinkByCode(createdLink.getGenCode()));
		}

		// Get links count by userAccount

		Long linksCount = linkService.getLinksCountByUser(userAccount);
		Assert.assertEquals(linksCount, Long.valueOf(createdLinks.size()));

		for (Link link : createdLinks) {
			linkService.deleteLinkById(link.getId());
		}
	}

}
