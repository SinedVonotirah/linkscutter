package by.vonotirah.linkscutter.webapp.models;

import java.util.List;

import by.vonotirah.linkscutter.datamodel.Link;

public class LinksModel {

	private List<Link> links;

	private Long count;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
