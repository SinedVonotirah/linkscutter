package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface UserService {

	void createNewUser(UserAccount userAccount);
	
	UserAccount getUserByLogin(String login);
	
	UserAccount getUserById(Long id);
	
	void updateUser(UserAccount userAccount);
	
	void deleteUser(UserAccount userAccount);
}
