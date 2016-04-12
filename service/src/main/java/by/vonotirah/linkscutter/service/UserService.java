package by.vonotirah.linkscutter.service;

import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface UserService {

	void createNewUser(UserAccount userAccount);

	void createNewUser(String login, String mail, String password);

	UserAccount getUserById(Long id);

	UserAccount getUserByLogin(String login);

	UserAccount getUserByMail(String mail);

	void updateUser(UserAccount userAccount);

	void deleteUser(UserAccount userAccount);

	boolean chekUserMailExist(UserAccount user);

	boolean chekUserLoginExist(UserAccount user);

	UserAccount userRegistration(UserAccount user);
}
