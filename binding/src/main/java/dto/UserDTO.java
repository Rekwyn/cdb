package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

	private String id;
	
	@NotNull
	@NotEmpty
	private String login;
	
	@NotNull
	@NotEmpty
	private String password;

	public UserDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setName(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User " + this.getId() + " | " + this.getLogin();
	}

	public static class UserDTOBuilder {

		private String id;
		private String login;
		private String password;

		public UserDTOBuilder() {
		}

		/**
		 * Build a new User Dto Object.
		 * 
		 * @return userDto the user Dto Object
		 */
		public UserDTO build() {
			UserDTO userDto = new UserDTO();

			userDto.setId(this.id);
			userDto.setName(this.login);
			userDto.setPassword(this.password);

			return userDto;
		}

		/**
		 * Set the id of the user dto object.
		 * 
		 * @param id the id of the user dto object
		 * @return this the current user instance
		 */
		public UserDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * Set the name of the user dto object.
		 * 
		 * @param name the name of the user dto object
		 * @return this the current user instance
		 */
		public UserDTOBuilder setName(String login) {
			this.login = login;
			return this;
		}
		
		/**
		 * Set the password of the user dto object.
		 * 
		 * @param password the password of the user dto object
		 * @return this the current user instance
		 */
		public UserDTOBuilder setPassword(String password) {
			this.password = password;
			return this;
		}
	}
}
