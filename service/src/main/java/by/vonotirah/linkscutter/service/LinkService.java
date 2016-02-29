package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface LinkService {

	void createNewLink(Link link);
	
	void createNewLink(UserAccount userAccount, String url);
	
	void createNewLink(String url, String login);
	
	void createNewLink(String url, String login, String description);
	
	Link getLinkById(Long id);
	
	Link getLinkByCode(String code);
	
	
}
