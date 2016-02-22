package by.vonotirah.linkscutter.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-02-22T15:35:43.664+0300")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ extends AbstractEntity_ {
	public static volatile SingularAttribute<UserAccount, Long> id;
	public static volatile SingularAttribute<UserAccount, String> login;
	public static volatile SingularAttribute<UserAccount, String> password;
	public static volatile SetAttribute<UserAccount, Link> links;
}
