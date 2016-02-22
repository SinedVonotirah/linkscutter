package by.vonotirah.linkscutter.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.DescriptionDao;
import by.vonotirah.linkscutter.datamodel.Description;

@Repository
public class DescriptionDaoImpl extends AbstractDaoImpl<Long, Description> implements DescriptionDao{
	
	protected DescriptionDaoImpl(){
		super(Description.class);
	}

}
