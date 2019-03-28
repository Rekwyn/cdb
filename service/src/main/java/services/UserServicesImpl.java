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
import org.springframework.stereotype.Service;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

@Service
public class UserServicesImpl implements UserDetailsService, UserDao {
	
	@Autowired
	private UserDaoImpl myUserDao;
	
	@Override
	public User findByName(String username) {
		return myUserDao.findByName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<String> l = Arrays.asList("admin");
		User user = myUserDao.findByName(username);
		 return  new org.springframework.security.core.userdetails.User
		          (user.getPassword().toLowerCase(), "", true, true, 
		          true, true, getAuthorities(l));
	}
	
	private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
