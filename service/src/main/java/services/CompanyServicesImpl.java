package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompanyDao;
import dto.CompanyDTO;
import mapper.MapperFactory;
import model.Company;

@Service
public class CompanyServicesImpl implements CompanyServices {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private MapperFactory mapper;

	private Company company;

	public CompanyServicesImpl() {
	}

	/**
	 * Call the associated DAO methods.
	 */
	@Transactional
	public void delete(CompanyDTO companyDto) {
		companyDao.delete(mapper.getCompanyMapper().mapDtoToObject(companyDto));
	}

	@Override
	public CompanyDTO getByName(CompanyDTO companyDto) {
		company = mapper.getCompanyMapper().mapDtoToObject(companyDto);
		return mapper.getCompanyMapper().mapObjectToDto(companyDao.getByName(company));
	}

	@Override
	public CompanyDTO getById(CompanyDTO companyDto) {
		company = mapper.getCompanyMapper().mapDtoToObject(companyDto);
		return mapper.getCompanyMapper().mapObjectToDto(companyDao.getById(company));
	}

	@Override
	public List<CompanyDTO> getAll() {
		List<CompanyDTO> companies = new ArrayList<>();

		for (Company current : companyDao.getAll()) {
			companies.add(mapper.getCompanyMapper().mapObjectToDto(current));
		}

		return companies;
	}
}
