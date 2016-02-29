package by.vonotirah.linkscutter.dataaccess;

import by.vonotirah.linkscutter.datamodel.Link;

public interface LinkDao extends AbstractDao<Long, Link> {

	Link getLinkByCode(String code);
	
	boolean checkCodeExist(String code);
}
