package by.vonotirah.linkscutter.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface LinkService {

	Link getLinkById(Long id);

	Link getLinkByCode(String code);

	URI linkRedirect(String code) throws URISyntaxException;

	// test only
	boolean chekLinkExistByCode(String code);

	List<Link> getLinksByUser(UserAccount userAccount);

	void deleteLinkById(Long id);

	List<Link> getLinksByUser(UserAccount userAccount, SingularAttribute<Link, ?> attr, boolean ascending,
			int startRecord, int pageSize);

	Long getLinksCountByUser(UserAccount userAccount);

	Link createNewLink(Link link, UserAccount userAccount);

	List<Link> getLinksByTag(Long tagId, SingularAttribute<Link, ?> attr, boolean ascending, int startRecord,
			int pageSize);

	List<Link> getLinksByTag(Long tagId);

	Long getLinksCountByTag(Long tagId);

}
