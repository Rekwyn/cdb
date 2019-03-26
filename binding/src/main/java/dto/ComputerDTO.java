package dto;

public class ComputerDTO {

	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String companyId;

	public ComputerDTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		return "Computer " + this.getId() + " | " + this.getName() + " | " + this.introduced + " | "
				+ this.getDiscontinued() + " | " + this.getCompanyId();
	}

	public static class ComputerDTOBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private String companyId;

		public ComputerDTOBuilder() {
		}

		/**
		 * Build a new Computer Dto Object.
		 * 
		 * @return cptr The Computer Dto Object
		 */
		public ComputerDTO build() {
			ComputerDTO cptrDto = new ComputerDTO();
			cptrDto.setId(this.id);
			cptrDto.setName(this.name);
			cptrDto.setIntroduced(this.introduced);
			cptrDto.setDiscontinued(this.discontinued);
			cptrDto.setCompanyId(this.companyId);
			return cptrDto;
		}

		/**
		 * Set the id of the computer.
		 * 
		 * @param id The computer id
		 * @return The current instance
		 */
		public ComputerDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * Set the name of the computer.
		 * 
		 * @param name The computer name
		 * @return The current instance
		 */
		public ComputerDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Set the introduced date of the computer.
		 * 
		 * @param introduced The computer introduced date
		 * @return The current instance
		 */
		public ComputerDTOBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		/**
		 * Set the discontinued date of the computer.
		 * 
		 * @param discontinued The computer discontinued date
		 * @return The current instance
		 */
		public ComputerDTOBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		/**
		 * Set the company id of the computer.
		 * 
		 * @param companyId The computer company id
		 * @return The current instance
		 */
		public ComputerDTOBuilder setCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}
	}
}
