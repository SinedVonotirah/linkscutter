package by.vonotirah.linkscutter.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.LinkDetailsDao;
import by.vonotirah.linkscutter.datamodel.LinkDetails;

@Repository
public class LinkDetailsDaoImpl extends AbstractDaoImpl<Long, LinkDetails> implements LinkDetailsDao {

	protected LinkDetailsDaoImpl(){
		super(LinkDetails.class);
	}
}
