package by.vonotirah.linkscutter.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.vonotirah.linkscutter.dataaccess.StatisticsDao;
import by.vonotirah.linkscutter.datamodel.Statistics;

@Repository
public class StatisticsDaoImpl extends AbstractDaoImpl<Long, Statistics> implements StatisticsDao{
	
	protected StatisticsDaoImpl(){
		super(Statistics.class);
	}

}
