package ikt.learn.VideoKlub.Services;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.UserFilter;
import ikt.learn.VideoKlub.Model.User;

public interface IUserInterface {

	boolean deleteUserById(Long id);

	User findByUserId(Long id);

	Long saveUser(User user) throws TotalCountException;

	int totalPrice(UserFilter filter);
}
