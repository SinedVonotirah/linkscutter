package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;

public interface LinkDetailsService {

	// TODO divide this interface into 2
	// LinkDetailsService4LinkService & LinkDetailsService4Rest

	// LinkDetailsService4Rest
	void updateLinkDetails(Link link);

	// LinkDetailsService4Rest
	LinkDetails getLinkDetailsById(Long id);

	// LinkDetailsService4LinkService
	LinkDetails createLinkDetails(Link link);
}
