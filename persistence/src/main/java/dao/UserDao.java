package dao;

import model.User;

public interface UserDao {

	/**
	 * Retrieve specific user from DB.
	 * 
	 * @param login the user login
	 * @return the user
	 */
	public User findByLogin(String login);
	
	/**
	 * Save new user in DB.
	 * 
	 * @param user the user informations
	 * @return the user
	 */
	public User save(User user);
}
