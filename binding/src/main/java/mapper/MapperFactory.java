package mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

	@Autowired
	CompanyMapper companyMapper;

	@Autowired
	ComputerMapper computerMapper;

	private MapperFactory() {
	}

	public CompanyMapper getCompanyMapper() {
		return companyMapper;
	}

	public ComputerMapper getComputerMapper() {
		return computerMapper;
	}
}
