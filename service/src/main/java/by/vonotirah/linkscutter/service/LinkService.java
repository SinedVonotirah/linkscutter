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

	boolean chekLinkExistByCode(String code);

	List<Link> getLinksByUser(UserAccount userAccount);

	void deleteLinkById(Long id);

	Link createNewLink(String url, String login, String description, List<String> tags);

	List<Link> getLinksByTag(Long tagId);

	List<Link> getLinksByUser(UserAccount userAccount, SingularAttribute<Link, ?> attr, boolean ascending,
			int startRecord, int pageSize);

	Long getLinksCountByUser(UserAccount userAccount);

}
