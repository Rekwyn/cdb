package model;

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
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@Column(name="password")
	private String password;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User " + this.getId() + " | " + this.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + id);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class UserBuilder {

		private Long id;
		private String name;
		private String password;

		public User build() {
			User user = new User();

			user.setId(this.id);
			user.setName(this.name);
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
		public UserBuilder setName(String name) {
			this.name = name;
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
}
