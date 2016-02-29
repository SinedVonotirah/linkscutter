package by.vonotirah.linkscutter.datamodel;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LinkDetails.class)
public abstract class LinkDetails_ extends by.vonotirah.linkscutter.datamodel.AbstractEntity_ {

	public static volatile SingularAttribute<LinkDetails, Date> created;
	public static volatile SingularAttribute<LinkDetails, String> description;
	public static volatile SingularAttribute<LinkDetails, Long> id;
	public static volatile SingularAttribute<LinkDetails, Long> counter;
	public static volatile SetAttribute<LinkDetails, Tag> tags;

}

