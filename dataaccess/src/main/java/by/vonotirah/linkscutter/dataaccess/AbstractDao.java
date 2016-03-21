package by.vonotirah.linkscutter.dataaccess;

import java.util.List;

public interface AbstractDao<ID, Entity> {

	Entity getEntityById(ID id);
	Entity insertEntity(Entity entity);
	Entity updateEntity(Entity entity);
	void deleteEntityById(ID id);
	void deleteAllEntity();
	List<Entity> getAllEntity();	
	
}
