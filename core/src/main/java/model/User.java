package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="login", nullable=false, unique=true)
	private String login;
	
	@Column(name="password")
	private String password;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
//	public List<String> getRole() {
//		return this.role;
//	}
	
	@Override
	public String toString() {
		return "User " + this.getId() + " | " + this.getLogin();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public static class UserBuilder {

		private Long id;
		private String login;
		private String password;

		public User build() {
			User user = new User();

			user.setId(this.id);
			user.setLogin(this.login);
			user.setPassword(this.password);
			
			return user;
		}

		/**
		 * @param id the user id
		 * @return the userbuilder instance
		 */
		public UserBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * @param name the user name
		 * @return the userbuilder instance
		 */
		public UserBuilder setName(String login) {
			this.login = login;
			return this;
		}
		
		/**
		 * 
		 * @param password the user's password
		 * @return the userbuilder instance
		 */
		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
	}

	public List<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}
