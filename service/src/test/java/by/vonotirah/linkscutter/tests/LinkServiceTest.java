package by.vonotirah.linkscutter.tests;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;

public class LinkServiceTest extends AbstractServiceTest {

	@Inject
	private LinkService linkService;
	
	@Inject
	private LinkDetailsService linkDetailsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceTest.class);
	
/*	@Test
	public void linkCrudTest(){
		final Link createdLink = linkService.createNewLink(randomString("URL - "), "test", randomString(), "tag1 tag2 tag3");
		
		final Link extractedLink = linkService.getLinkByCode(createdLink.getGenCode());
		Assert.assertNotNull(extractedLink);
		Assert.assertEquals(createdLink.getUrl(), extractedLink.getUrl());
		
		linkDetailsService.updateLinkDetails(extractedLink.getId(), randomString(), "tag4 tag5 tag6");
		
		final Link updatedLink = linkService.getLinkByCode(createdLink.getGenCode());
		
		//доделать или удалить 
		
		
	}*/
	
	@Test
	public void linksSearchByTagTest(){
		
		List<Link> linkList = linkService.getLinksByTag("tag1");
		for ( Link link : linkList){
			
			System.out.println(link.toString());
		}
		
	}
}
