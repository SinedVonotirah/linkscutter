package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface UserService {

	// TODO divide this interface into 2
	// UserService4Rest & UserService4Test
	// userService4Tests extends UserService4Rest

	// UserService4Rest
	UserAccount createNewUser(UserAccount userAccount);

	// UserService4Test
	UserAccount getUserById(Long id);

	// UserService4Rest
	UserAccount getUserByLogin(String login);

	// UserService4Test
	void updateUser(UserAccount userAccount);

	// UserService4Test
	void deleteUser(UserAccount userAccount);
}
