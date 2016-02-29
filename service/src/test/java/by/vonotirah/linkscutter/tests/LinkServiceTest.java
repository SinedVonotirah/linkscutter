package by.vonotirah.linkscutter.tests;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.service.LinkService;

public class LinkServiceTest extends AbstractServiceTest {

	@Inject
	private LinkService linkService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceTest.class);
	
	@Test
	public void linkCRUDTest(){
		
	}
}
