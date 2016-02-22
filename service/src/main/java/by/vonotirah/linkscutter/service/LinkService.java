package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.Link;

public interface LinkService {

	void createNewLink(Link link);
	
	Link getLinkById(Long id);
	
	Link getLinkByCode(String code);
}
