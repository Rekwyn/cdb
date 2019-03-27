package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserServicesImpl implements UserDetailsService, UserDao {
	
	@Autowired
	private UserDaoImpl myUserRepo;
	
	@Override
	public User findByName(String username) {
		return myUserRepo.findByName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
