package by.vonotirah.linkscutter.datamodel;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserAccount extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String login;

	@Column
	private String password;
	
	@Column
	private String mail;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "userAccount")
	private Set<Link> links;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}


}
