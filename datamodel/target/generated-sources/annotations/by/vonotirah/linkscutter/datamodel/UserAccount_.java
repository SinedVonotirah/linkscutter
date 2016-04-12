package by.vonotirah.linkscutter.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAccount.class)
public abstract class UserAccount_ extends by.vonotirah.linkscutter.datamodel.AbstractEntity_ {

	public static volatile SingularAttribute<UserAccount, String> password;
	public static volatile SingularAttribute<UserAccount, String> mail;
	public static volatile SingularAttribute<UserAccount, Long> id;
	public static volatile SingularAttribute<UserAccount, String> login;

}

