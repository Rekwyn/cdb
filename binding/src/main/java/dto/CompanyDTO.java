package dto;

public class CompanyDTO {

	private String id;
	private String name;

	public CompanyDTO() {
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
	
	@Override
	public String toString() {
		return "Company " + this.getId() + " | " + this.getName();
	}

	public static class CompanyDTOBuilder {

		private String id;
		private String name;

		public CompanyDTOBuilder() {
		}

		/**
		 * Build a new Company Dto Object.
		 * 
		 * @return cpnyDto The Company Dto Object
		 */
		public CompanyDTO build() {
			CompanyDTO cpnyDto = new CompanyDTO();

			cpnyDto.setId(this.id);
			cpnyDto.setName(this.name);

			return cpnyDto;
		}

		/**
		 * Set the id of the Company Dto Object.
		 * 
		 * @param id Id of the Company Dto Object
		 * @return this The current Instance
		 */
		public CompanyDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		/**
		 * Set the name of the Company Dto Object.
		 * 
		 * @param name Name of the Company Dto Object
		 * @return this The current Instance
		 */
		public CompanyDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}
	}
}
