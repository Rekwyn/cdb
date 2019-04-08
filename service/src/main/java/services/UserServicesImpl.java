package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dao.UserDaoImpl;
import dto.UserDTO;
import model.User;

@Service
public class UserServicesImpl implements UserDetailsService {
	
	@Autowired
	private UserDaoImpl myUserDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		List<String> l = Arrays.asList("admin");
		User user = myUserDao.findByLogin(login);
		System.out.println(user.getPassword());
		 return  new org.springframework.security.core.userdetails.User
		          (user.getLogin(), user.getPassword(), true, true, 
		          true, true, getAuthorities(l));
	}
	
	/**
	 * Create an list of roles for each user.
	 * 
	 * @param roles the original list of roles
	 * @return authorities the authorities list
	 */
	private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
	
	/**
	 * Register a new user into the DB.
	 * 
	 * @param accountDto the user informations in dto object
	 * @return the saved user
	 */
	public User registerNewUserAccount(UserDTO accountDto) {
	    User user = new User();
	    user.setLogin(accountDto.getLogin());
	    user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
	    return myUserDao.save(user);
	}
}
