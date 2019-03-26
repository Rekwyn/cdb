package dao;

import java.util.List;

import model.Company;

public interface CompanyDao {

	/**
	 * Methods availables for classes who implements this interface.
	 */
	public void delete(Company company);

	public List<Company> getAll();

	public Company getByName(Company company);

	public Company getById(Company company);
}
