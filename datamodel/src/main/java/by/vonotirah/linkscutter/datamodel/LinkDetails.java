package by.vonotirah.linkscutter.datamodel;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class LinkDetails extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@Column
	private Long counter;

	@Column
	private Date created;

	/*
	 * @MapsId
	 * 
	 * @OneToOne(mappedBy = "linkDetails") private Link link;
	 */

	@JoinTable(name = "link_details_2_tag", joinColumns = {
			@JoinColumn(name = "link_details_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
	private Set<Tag> tags = new TreeSet<Tag>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
