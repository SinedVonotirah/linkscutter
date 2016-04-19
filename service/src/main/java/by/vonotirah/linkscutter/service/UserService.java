package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface UserService {

	UserAccount createNewUser(UserAccount userAccount);

	UserAccount getUserById(Long id);

	UserAccount getUserByLogin(String login);

	void updateUser(UserAccount userAccount);

	void deleteUser(UserAccount userAccount);
}
