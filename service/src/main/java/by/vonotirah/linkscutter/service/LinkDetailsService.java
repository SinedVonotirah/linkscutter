package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.LinkDetails;

public interface LinkDetailsService {

	LinkDetails createLinkDetails();

	LinkDetails createLinkDetails(String description, String tags);

	void updateLinkDetails(Long id, String description, String tags);
}
