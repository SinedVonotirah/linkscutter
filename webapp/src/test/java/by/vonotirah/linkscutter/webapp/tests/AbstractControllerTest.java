package by.vonotirah.linkscutter.webapp.tests;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;
import by.vonotirah.linkscutter.webapp.restcontrollers.RestApiController;
import by.vonotirah.linkscutter.webapp.security.CustomUserDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:mvc-dispatcher-servlet.xml", "classpath:applicationContext.xml" })
@TestPropertySource("classpath:test_application.properties")
@WebAppConfiguration
public abstract class AbstractControllerTest {

	@Resource
	protected FilterChainProxy springSecurityFilterChain;

	@Resource
	protected WebApplicationContext webApplicationContext;

	@InjectMocks
	protected RestApiController controller;

	@Inject
	protected LinkService linkService;

	@Inject
	protected CustomUserDetailsService userDetailsService;

	@Inject
	protected UserService userService;

	protected MockMvc mockMvc;

	protected UserDetails userDetails;

	protected UserAccount firstUserAccount4Tests;

	protected UserAccount secondUserAccount4Tests;

	protected static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final Random RANDOM = new Random();

	private static final RandomData RANDOM_DATA = new RandomDataImpl();

	private static final int RANDOM_STRING_SIZE = 6;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();

		firstUserAccount4Tests = createRandomUserAccount();
		secondUserAccount4Tests = createRandomUserAccount();
		userDetails = userDetailsService.loadUserByUsername(firstUserAccount4Tests.getLogin());
	}

	@After
	public void endTest() {
		userService.deleteUser(firstUserAccount4Tests);
		userService.deleteUser(secondUserAccount4Tests);

	}

	public static byte[] convertObjectToJsonBytes(final Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static String randomString() {
		return RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
	}

	public static String randomString(final String prefix) {
		return String.format("%s-%s", new Object[] { prefix, randomString() });
	}

	public static int randomInteger() {
		return randomInteger(0, 9999);
	}

	public static int randomInteger(final int lower, final int upper) {
		return RANDOM_DATA.nextInt(lower, upper);
	}

	public Link getRandomLink() {
		Link link = new Link();
		link.setUrl(randomString("http://URL"));
		LinkDetails linkDetails = new LinkDetails();
		linkDetails.setDescription(randomString("Description"));
		linkDetails.setTags(getRandomTags());
		link.setLinkDetails(linkDetails);
		return link;
	}

	public Set<Tag> getRandomTags() {
		int tagsCounter = randomInteger(0, 10);
		Set<Tag> tags = new HashSet<Tag>();
		for (int i = 0; i < tagsCounter; i++) {
			Tag tag = new Tag();
			tag.setName(randomString("Tag"));
			tags.add(tag);
		}
		Tag tag = new Tag();
		tag.setName("sameTag");
		tags.add(tag);
		return tags;
	}

	protected Link createRandomLink(UserAccount userAccount) {
		Link linkForCreate = getRandomLink();
		Link link = linkService.createNewLink(linkForCreate, userAccount);
		return link;
	}

	protected UserAccount getRandomUserAccount() {
		UserAccount userAccount = new UserAccount();

		userAccount.setLogin(randomString("Login "));
		userAccount.setMail(randomString("Mail@"));
		userAccount.setPassword(randomString("Password "));
		return userAccount;
	}

	protected UserAccount createRandomUserAccount() {
		UserAccount userAccount = getRandomUserAccount();
		userService.createNewUser(userAccount);
		return userAccount;
	}
}
