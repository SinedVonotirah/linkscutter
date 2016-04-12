package by.vonotirah.linkscutter.datamodel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Link extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String genCode;

	@Column
	private String url;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private LinkDetails linkDetails;

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

	public LinkDetails getLinkDetails() {
		return linkDetails;
	}

	public void setLinkDetails(LinkDetails linkDetails) {
		this.linkDetails = linkDetails;
	}

	public String toString() {
		return new String("Link id - " + this.id);
	}

}
