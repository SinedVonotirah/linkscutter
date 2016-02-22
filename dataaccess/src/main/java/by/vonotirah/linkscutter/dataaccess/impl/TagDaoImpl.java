package by.vonotirah.linkscutter.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.TagDao;
import by.vonotirah.linkscutter.datamodel.Tag;

@Repository
public class TagDaoImpl extends AbstractDaoImpl<Long, Tag> implements TagDao{
	
	protected TagDaoImpl(){
		super(Tag.class);
	}
}
