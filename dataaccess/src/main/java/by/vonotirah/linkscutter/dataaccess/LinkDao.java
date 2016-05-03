package by.vonotirah.linkscutter.dataaccess;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface LinkDao extends AbstractDao<Long, Link> {

	Link getLinkByCode(String code);

	abstract boolean checkCodeExist(String code);

	List<Link> getLinksByTag(Long tagId);

	List<Link> getAllLinks();

	List<Link> getLinksByUser(UserAccount userAccount);

	List<Link> getLinksByUser(UserAccount userAccount, SingularAttribute<Link, ?> attr, boolean ascending,
			int startRecord, int pageSize);

	Long getLinksCountByUser(UserAccount userAccount);

	List<Link> getLinksByTag(Long tagId, SingularAttribute<Link, ?> attr, boolean ascending, int startRecord,
			int pageSize);

	Long getLinksCountByTag(Long tagId);
}
