package by.vonotirah.linkscutter.datamodel;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Link extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column 
	private String genCode;
	
	@Column
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;
	
	@JoinTable(name = "link_2_tag", joinColumns = { @JoinColumn(name = "link_id")}, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	@ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Tag> tags = new TreeSet<Tag>();	
	
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Statistics statistics;
    
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Description description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenCode() {
		return genCode;
	}

	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
