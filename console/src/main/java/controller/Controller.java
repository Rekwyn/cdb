package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerFieldException;
import exception.MapperException;
import exception.ValidatorException;
import services.CompanyServices;
import services.ComputerServices;
import view.DisplayView;

@Component
public class Controller {

	@Autowired
	private CompanyServices companyServices;

	@Autowired
	private ComputerServices computerServices;

	@Autowired
	private DisplayView view;

	private Controller() {
	}

	/**
	 * Display menu and wait for user input.
	 * 
	 * @return user input
	 */
	public String onSetup() {
		view.displayHelp();
		return view.waitForCommand();
	}

	/**
	 * Execute commands based on the user input.
	 * 
	 * @param msg user input
	 * @throws ComputerFieldException
	 * @throws ValidatorException
	 * @throws MapperException
	 */
	public void executeCommand(String msg) throws ComputerFieldException, ValidatorException, MapperException {

		StringBuilder sb = new StringBuilder();

		switch (msg) {
		case "/l company":
			getAllCompanies(sb);
			break;
		case "/l computer":
			getAllComputers(sb);
			break;
		case "/f company":
			getCompany(sb);
			break;
		case "/f computer":
			getComputer(sb);
			break;
		case "/a computer":
			addComputer(sb);
			break;
		case "/u computer":
			updateComputer(sb);
			break;
		case "/d computer":
			deleteComputer(sb);
			break;
		case "/d company":
			deleteCompany(sb);
			break;
		default:
			this.view.displayErrorCommand();
		}
		this.view.displayResult(sb.toString());
	}

	/**
	 * Execute the specified command.
	 * 
	 * @param sb array that stores each item values
	 * @throws ValidatorException
	 */
	public void deleteCompany(StringBuilder sb) throws ComputerFieldException, ValidatorException {
		String[] attributes = { "id" };
		companyServices.delete(this.fillCompanyAttributes(attributes));
	}

	public void getAllCompanies(StringBuilder sb) {
		List<CompanyDTO> companies = companyServices.getAll();
		for (CompanyDTO current : companies) {
			sb.append(current.toString() + "\n");
		}
	}

	public void getAllComputers(StringBuilder sb) {
		List<ComputerDTO> computers = computerServices.getAll(1, 2000, "id");
		for (ComputerDTO current : computers) {
			sb.append(current.toString() + "\n");
		}
	}

	public void getCompany(StringBuilder sb) throws ComputerFieldException, ValidatorException {
		String[] attributes = { "name" };
		CompanyDTO company = companyServices.getByName(this.fillCompanyAttributes(attributes));
		sb.append(company == null ? "Company not found.\n" : company.toString());
	}

	public void getComputer(StringBuilder sb) throws ComputerFieldException, MapperException, ValidatorException {
		String[] attributes = { "name" };
		ComputerDTO computer = computerServices.get(this.fillComputerAttributes(attributes));
		sb.append(computer == null ? "Computer not found.\n" : computer.toString());
	}

	public void addComputer(StringBuilder sb) throws ComputerFieldException, MapperException, ValidatorException {
		String[] attributes = { "name", "introduced", "company_id" };
		computerServices.add(this.fillComputerAttributes(attributes));
		sb.append("Computer successfully added.");
	}

	public void updateComputer(StringBuilder sb) throws ComputerFieldException, MapperException, ValidatorException {
		String[] attributes = { "id", "name", "introduced", "discontinued", "company_id" };
		computerServices.update(this.fillComputerAttributes(attributes));
		sb.append("Computer successfully updated.");
	}

	public void deleteComputer(StringBuilder sb) throws ComputerFieldException, MapperException, ValidatorException {
		String[] attributes = { "id" };
		computerServices.delete(this.fillComputerAttributes(attributes));
		sb.append("Computer successfully deleted.");
	}

	/**
	 * Instantiate a new object to fill it's parameters.
	 * 
	 * @param attr array of blank attributes to set
	 * @return Computer object that represents a computer
	 * @throws ComputerFieldException if a field is badly formatted
	 * @throws MapperException
	 * @throws ValidatorException
	 */
	public ComputerDTO fillComputerAttributes(String[] attr)
			throws ComputerFieldException, MapperException, ValidatorException {

		ComputerDTO computerDto = new ComputerDTO.ComputerDTOBuilder().build();

		for (String currentAttribute : attr) {
			String value = this.view.askingInputComputer(currentAttribute);
			switch (currentAttribute) {
			case "id":
				computerDto.setId(value);
			case "name":
				computerDto.setName(value);
				break;
			case "introduced":
				computerDto.setIntroduced(value);
				break;
			case "discontinued":
				computerDto.setDiscontinued(value);
				break;
			case "company_id":
				computerDto.setCompanyId(value);
			}
		}

		return computerDto;
	}

	/**
	 * Instantiate a new object to fill it's parameters.
	 * 
	 * @param attr array of blank attributes to set
	 * @return Company an object that represents a company
	 * @throws ComputerFieldException if a field if badly formatted
	 * @throws ValidatorException
	 */
	public CompanyDTO fillCompanyAttributes(String[] attr) throws ComputerFieldException, ValidatorException {

		CompanyDTO companyDto = new CompanyDTO.CompanyDTOBuilder().build();

		for (String currentAttribute : attr) {
			String value = this.view.askingInputCompany(currentAttribute);
			switch (currentAttribute) {
			case "id":
				companyDto.setId(value);
				break;
			case "name":
				companyDto.setName(value);
				break;
			}
		}

		return companyDto;
	}
}
