package by.vonotirah.linkscutter.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-22T15:35:43.662+0300")
@StaticMetamodel(Link.class)
public class Link_ extends AbstractEntity_ {
	public static volatile SingularAttribute<Link, Long> id;
	public static volatile SingularAttribute<Link, String> genCode;
	public static volatile SingularAttribute<Link, String> url;
	public static volatile SingularAttribute<Link, UserAccount> userAccount;
	public static volatile SetAttribute<Link, Tag> tags;
	public static volatile SingularAttribute<Link, Statistics> statistics;
	public static volatile SingularAttribute<Link, Description> description;
}
