package by.vonotirah.linkscutter.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface LinkService {

	void createNewLink(Link link);

	Link getLinkById(Long id);

	Link getLinkByCode(String code);

	URI linkRedirect(String code) throws URISyntaxException;

	boolean chekLinkExistByCode(String code);

	List<Link> getAllLinks();

	List<Link> getLinksByUser(UserAccount userAccount);

	void deleteLinkById(Long id);

	Link createNewLink(String url, String login, String description, List<String> tags);

	List<Link> getLinksByTag(Long tagId);

}
