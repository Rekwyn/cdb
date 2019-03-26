package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	public Company() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company " + this.getId() + " | " + this.getName();
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class CompanyBuilder {

		private Long id;
		private String name;

		public Company build() {
			Company company = new Company();

			company.setId(this.id);
			company.setName(this.name);

			return company;
		}

		/**
		 * @param id The company id
		 * @return the companybuilder instance
		 */
		public CompanyBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * @param name the company name
		 * @return the companybuilder instance
		 */
		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}
	}
}
