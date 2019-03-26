package mapper;

import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import model.Company;

@Component
public class CompanyMapper implements ObjectMapper<Company, CompanyDTO> {

	private CompanyMapper() {
	}

	@Override
	public Company mapDtoToObject(CompanyDTO dto) {

		Company company = new Company.CompanyBuilder().build();

		if (dto.getId() == null) {
			company.setId(null);
		} else {
			company.setId(Long.parseLong(dto.getId()));
		}

		company.setName(dto.getName());

		return company;
	}

	@Override
	public CompanyDTO mapObjectToDto(Company company) {

		CompanyDTO cpnyDto = new CompanyDTO.CompanyDTOBuilder().setId(Long.toString(company.getId()))
				.setName(company.getName()).build();

		return cpnyDto;
	}
}
