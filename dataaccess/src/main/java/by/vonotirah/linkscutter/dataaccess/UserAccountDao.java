package by.vonotirah.linkscutter.dataaccess;

import by.vonotirah.linkscutter.datamodel.UserAccount;

public interface UserAccountDao extends AbstractDao<Long, UserAccount> {

	UserAccount getUserByLogin(String login);

	UserAccount getUserByMail(String mail);

	boolean chekUserLoginExist(String login);

	boolean chekUserEmailExist(String mail);
}
