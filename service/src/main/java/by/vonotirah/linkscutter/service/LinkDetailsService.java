package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;

public interface LinkDetailsService {

	void updateLinkDetails(Link link);

	LinkDetails getLinkDetailsById(Long id);

	LinkDetails createLinkDetails(Link link);
}
