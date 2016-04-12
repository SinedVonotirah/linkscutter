package by.vonotirah.linkscutter.webapp.tests;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
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
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.webapp.models.LinkModel;
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

	protected MockMvc mockMvc;

	protected static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected UserDetails userDetails;

	protected static final String FIRST_TEST_LOGIN = "testLogin";

	protected static final String SECOND_TEST_LOGIN = "testLogin2";

	private final static Random random = new Random();

	private static final RandomData RANDOM_DATA = new RandomDataImpl();

	private static final int RANDOM_STRING_SIZE = 6;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain)
				.build();

		userDetails = userDetailsService.loadUserByUsername(FIRST_TEST_LOGIN);
	}

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
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

	public List<String> getRandomTagsList() {
		List<String> tags = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			tags.add(randomString("Tag"));
		}
		tags.add("sameTag");
		return tags;
	}

	protected LinkModel getLinkModel() {
		LinkModel linkModel = new LinkModel();

		linkModel.setUrl(randomString("http://UrlL"));
		linkModel.setDescription(randomString("Descrition"));
		linkModel.setTags(getRandomTagsList());

		return linkModel;
	}

	protected Link createRandomLink(String login) {
		LinkModel linkModel = getLinkModel();
		Link link = linkService.createNewLink(linkModel.getUrl(), login, linkModel.getDescription(),
				linkModel.getTags());
		return link;
	}

	protected UserAccount getRandomUserAccount() {
		UserAccount userAccount = new UserAccount();

		userAccount.setLogin(randomString("Login "));
		userAccount.setMail(randomString("Mail "));
		userAccount.setPassword(randomString("Password "));
		return userAccount;
	}
}
