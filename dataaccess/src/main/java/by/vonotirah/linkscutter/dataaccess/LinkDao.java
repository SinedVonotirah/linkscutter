package by.vonotirah.linkscutter.dataaccess;

import java.util.List;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface LinkDao extends AbstractDao<Long, Link> {

	Link getLinkByCode(String code);

	boolean checkCodeExist(String code);

	List<Link> getLinksByTag(Long tagId);

	List<Link> getAllLinks();

	List<Link> getLinksByUser(UserAccount userAccount);
}
