package by.vonotirah.linkscutter.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import by.vonotirah.linkscutter.datamodel.Link;

public interface LinkService {

	void createNewLink(Link link);
	
	Link createNewLink(String url, String login, String description, String tags);
	
	Link getLinkById(Long id);
	
	Link getLinkByCode(String code);
	
	URI linkRedirect(String code) throws URISyntaxException;
	
	boolean chekLinkExistByCode(String code);

	List<Link> getLinksByTag(String tag);

	
	
}
