package by.vonotirah.linkscutter.dataaccess;

import by.vonotirah.linkscutter.datamodel.Tag;

public interface TagDao extends AbstractDao<Long, Tag> {

	boolean tagExist (String name);
	
	Tag gatTagByName (String name);
	
}
