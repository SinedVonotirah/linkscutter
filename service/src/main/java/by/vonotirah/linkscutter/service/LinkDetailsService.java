package by.vonotirah.linkscutter.service;

import java.util.List;

import by.vonotirah.linkscutter.datamodel.LinkDetails;

public interface LinkDetailsService {

	LinkDetails createLinkDetails(String description, List<String> tags);

	void updateLinkDetails(Long id, String description, List<String> tags);

	LinkDetails getLinkDetailsById(Long id);
}
