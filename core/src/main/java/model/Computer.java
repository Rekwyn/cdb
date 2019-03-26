package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="introduced")
	private Date introduced;
	
	@Column(name="discontinued")
	private Date discontinued;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;

	public Computer() {
	}

	@Override
	public String toString() {
		return "Computer " + this.getId() + " | " + this.getName() + " | " + this.introduced + " | "
				+ this.getDiscontinued() + " | " + this.getCompany();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + company.getId());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = (int) (prime * result + id);
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		Computer other = (Computer) obj;
		if (company.getId() != other.company.getId())
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class ComputerBuilder {

		private Long id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private Company company;

		public Computer build() {
			Computer computer = new Computer();

			computer.setId(this.id);
			computer.setName(this.name);
			computer.setIntroduced(this.introduced);
			computer.setDiscontinued(this.discontinued);
			computer.setCompany(this.company);

			return computer;
		}

		/**
		 * @param id the computer id
		 * @return the computerbuilder instance
		 */
		public ComputerBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * @param name the computer name
		 * @return The computerbuilder instance
		 */
		public ComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		/**
		 * @param introduced the computer introduced date
		 * @return the computerbuilder instance
		 */
		public ComputerBuilder setIntroduced(Date introduced) {
			this.introduced = introduced;
			return this;
		}

		/**
		 * @param discontinued the computer discontinued date
		 * @return the computerbuilder instance
		 */
		public ComputerBuilder setDiscontinued(Date discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		/**
		 * @param company the computer company
		 * @return the computerbuilder instance
		 */
		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}
	}
}
