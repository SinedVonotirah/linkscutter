package by.vonotirah.linkscutter.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.datamodel.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@TestPropertySource("classpath:test_application.properties")
public abstract class AbstractServiceTest {

	private static final Random RANDOM = new Random();

	protected static final RandomData RANDOM_DATA = new RandomDataImpl();

	private static final int RANDOM_STRING_SIZE = 6;

	public static String randomString() {
		return RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
	}

	public static String randomString(final String prefix) {
		return String.format("%s-%s", new Object[] { prefix, randomString() });
	}

	public static int randomTestObjectsCount() {
		return RANDOM_DATA.nextInt(0, 5) + 1;
	}

	public static int randomInteger() {
		return randomInteger(0, 9999);
	}

	public static int randomInteger(final int lower, final int upper) {
		return RANDOM_DATA.nextInt(lower, upper);
	}

	public static boolean randomBoolean() {
		return Math.random() < 0.5;
	}

	public static long randomLong() {
		return RANDOM_DATA.nextLong(0, 9999999);
	}

	public static long randomLong(final Long lower, final Long upper) {
		return RANDOM_DATA.nextLong(lower, upper);
	}

	public static BigDecimal randomBigDecimal() {
		return new BigDecimal(randomDouble()).setScale(2, RoundingMode.HALF_UP);
	}

	public static double randomDouble() {
		final double value = RANDOM.nextDouble() + randomInteger();
		return Math.round(value * 1e2) / 1e2;

	}

	public static Date randomDate() {
		final int year = randBetween(1900, 2010);
		final GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);
		final int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
	}

	public static int randBetween(final int start, final int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public UserAccount getRandomUserAccount() {
		final UserAccount userAccount = new UserAccount();
		userAccount.setLogin(randomString("Login - "));
		userAccount.setPassword(randomString("Password - "));
		userAccount.setMail(randomString("Mail - "));
		return userAccount;
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
		return tags;
	}

	public List<String> getRandomTagsList() {
		List<String> tags = new ArrayList<String>();
		int tagsCounter = randomInteger(2, 4);
		for (int i = 0; i < tagsCounter; i++) {
			tags.add(randomString("Tag"));
		}
		return tags;
	}

}
