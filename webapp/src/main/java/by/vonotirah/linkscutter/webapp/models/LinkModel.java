package by.vonotirah.linkscutter.webapp.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LinkModel {

	private Long id;

	@NotNull
	@Size(min = 1)
	private String url;

	@Size(min = 0, max = 50)
	private String description;

	private List<String> tags;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
