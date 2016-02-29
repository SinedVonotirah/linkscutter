package by.vonotirah.linkscutter.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Link.class)
public abstract class Link_ extends by.vonotirah.linkscutter.datamodel.AbstractEntity_ {

	public static volatile SingularAttribute<Link, String> genCode;
	public static volatile SingularAttribute<Link, UserAccount> userAccount;
	public static volatile SingularAttribute<Link, LinkDetails> linkDetails;
	public static volatile SingularAttribute<Link, Long> id;
	public static volatile SingularAttribute<Link, String> url;

}

