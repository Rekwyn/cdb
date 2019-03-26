package services;

import java.util.List;

import dto.CompanyDTO;

/**
 * Methods availables for classes who implements this interface.
 */
public interface CompanyServices {
	public void delete(CompanyDTO companyDto);

	public CompanyDTO getByName(CompanyDTO companyDto);

	public CompanyDTO getById(CompanyDTO companyDto);

	public List<CompanyDTO> getAll();
}
