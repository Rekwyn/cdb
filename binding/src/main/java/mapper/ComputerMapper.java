package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.ValidatorException;
import model.Company;
import model.Computer;
import validator.Validator;

@Component
public class ComputerMapper implements ObjectMapper<Computer, ComputerDTO> {

	private ComputerMapper() {
	}

	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Logger logger = LoggerFactory.getLogger(ComputerMapper.class);

	@Autowired
	private Validator validator;

	@Override
	public Computer mapDtoToObject(ComputerDTO dto) throws ValidatorException {

		Computer computer = new Computer.ComputerBuilder().build();

		validator.validate(dto);

		if (dto.getId() == null) {
			computer.setId(null);
		} else {
			computer.setId(Long.parseLong(dto.getId()));
		}

		computer.setName(dto.getName());

		try {
			computer.setIntroduced(this.convertStringToDate(dto.getIntroduced()));
			computer.setDiscontinued(this.convertStringToDate(dto.getDiscontinued()));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		if (dto.getCompanyId() == null || dto.getCompanyId().isEmpty()) {
			computer.setCompany(null);
		} else {
			computer.setCompany(new Company.CompanyBuilder().setId(Long.parseLong(dto.getCompanyId())).build());
		}

		return computer;
	}

	@Override
	public ComputerDTO mapObjectToDto(Computer computer) {

		ComputerDTO cptrDto = new ComputerDTO.ComputerDTOBuilder().build();
				
		cptrDto.setId(Long.toString(computer.getId()));
		cptrDto.setName(computer.getName());
		
		if (computer.getIntroduced() == null) {
			cptrDto.setIntroduced(null);
		} else {
			cptrDto.setIntroduced(this.convertDateToString(computer.getIntroduced()));
		}
		if (computer.getDiscontinued() == null) {
			cptrDto.setDiscontinued(null);
		} else {
			cptrDto.setDiscontinued(this.convertDateToString(computer.getDiscontinued()));
		} 
		
		if (computer.getCompany() == null) {
			cptrDto.setCompanyId(null);
		} else {
			cptrDto.setCompanyId(Long.toString(computer.getCompany().getId()));
		}

		return cptrDto;
	}

	/**
	 * Convert given String to Date.
	 * 
	 * @param date the date
	 * @return the converted date
	 * @throws ParseException
	 */
	public Date convertStringToDate(String date) throws ParseException {
		return date == null || "".equals(date) ? null : inputFormat.parse(date);
	}

	/**
	 * Convert given Date to String.
	 * 
	 * @param date the date
	 * @return the converted date
	 * @throws ParseException
	 */
	public String convertDateToString(Date date) {
		return date == null ? null : inputFormat.format(date);
	}

}
