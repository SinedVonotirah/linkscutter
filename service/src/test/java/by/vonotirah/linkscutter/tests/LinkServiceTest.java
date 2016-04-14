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

	@Test
	public void linkCrudTest() {
		LOGGER.info("----------------linkCrudTest()----------------------");
		UserAccount userAccount = userService.getUserById(1L);

		// -------Create new Link for UserAccount with ID = 1
		String url = randomString("http://");
		String userLogin = userAccount.getLogin();
		String description = randomString("Description ");
		List<String> tags = getRandomTagsList();
		Link newLink = linkService.createNewLink(url, userLogin, description, tags);

		// -------Get Link by GenCode
		Link extractedLink = linkService.getLinkByCode(newLink.getGenCode());
		Assert.assertNotNull(extractedLink);
		Assert.assertEquals(newLink.getGenCode(), extractedLink.getGenCode());
		Assert.assertEquals(newLink.getUrl(), extractedLink.getUrl());
		Assert.assertEquals(newLink.getLinkDetails().getDescription(), extractedLink.getLinkDetails().getDescription());
		Assert.assertEquals(newLink.getLinkDetails().getTags(), extractedLink.getLinkDetails().getTags());

		// -------Update Link by UserAccount with ID = 1
		Long linkId = extractedLink.getId();
		String newDescription = randomString("Description ");
		List<String> newTags = getRandomTagsList();
		linkDetailsService.updateLinkDetails(linkId, newDescription, newTags);
		Link updatedLink = linkService.getLinkByCode(extractedLink.getGenCode());
		Assert.assertNotNull(updatedLink);
		Assert.assertEquals(updatedLink.getGenCode(), extractedLink.getGenCode());
		Assert.assertEquals(updatedLink.getUrl(), extractedLink.getUrl());
		Assert.assertNotEquals(updatedLink.getLinkDetails().getDescription(),
				extractedLink.getLinkDetails().getDescription());
		Assert.assertNotEquals(updatedLink.getLinkDetails().getTags(), extractedLink.getLinkDetails().getTags());

		// -------Delete Link by ID
		linkService.deleteLinkById(linkId);
		Assert.assertNull(linkService.getLinkById(linkId));
		Assert.assertNull(linkDetailsService.getLinkDetailsById(linkId));

	}

	@Test
	public void getAllLinksByUserTest() {
		LOGGER.info("----------------getAllLinksByUserTest()----------------------");
		UserAccount userAccount = userService.getUserById(1L);

		// -------Create n links for UserAccount with ID = 1
		int linksCount = randomInteger(3, 5);
		for (int i = 0; i < linksCount; i++) {
			String url = randomString("http://");
			String userLogin = userAccount.getLogin();
			String description = randomString("Description ");
			List<String> tags = getRandomTagsList();
			linkService.createNewLink(url, userLogin, description, tags);
		}

		// -------Get All Links by UserAccount with ID = 1
		List<Link> extractedLinkList = linkService.getLinksByUser(userAccount);
		for (Link extractedLink : extractedLinkList) {
			Assert.assertEquals(extractedLink.getUserAccount(), userAccount);
			linkService.deleteLinkById(extractedLink.getId());
		}

	}

	@Test
	public void getLinksByTagTest() {
		LOGGER.info("----------------getLinksByTagTest()----------------------");
		UserAccount userAccount = userService.getUserById(1L);
		String sameTag = randomString("Tag");
		List<Link> createdLinks = new ArrayList<Link>();

		// -------Create n links for UserAccount with ID = 1 and with the same
		// tag
		int linksCount = randomInteger(3, 5);
		for (int i = 0; i < linksCount; i++) {
			String url = randomString("http://");
			String userLogin = userAccount.getLogin();
			String description = randomString("Description ");
			List<String> tags = getRandomTagsList();
			tags.add(sameTag);
			Link createdLink = linkService.createNewLink(url, userLogin, description, tags);
			createdLinks.add(linkService.getLinkByCode(createdLink.getGenCode()));
		}

		Tag tag = tagService.getTagByName(sameTag);
		List<Link> extractedLinks = linkService.getLinksByTag(tag.getId());
		Assert.assertEquals(createdLinks, extractedLinks);

		for (Link link : createdLinks) {
			linkService.deleteLinkById(link.getId());
		}

	}

}
