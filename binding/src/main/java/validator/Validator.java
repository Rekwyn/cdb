package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import dto.ComputerDTO;
import exception.ValidatorException;

@Component
public class Validator {

	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date parsedDiscontinued;
	private Date parsedIntroduced;
	private Long parsedCompanyId;
	private Long parsedId;

	private Validator() {
	}

	public void validate(ComputerDTO computer) throws ValidatorException {
		this.verifyId(computer.getId());
		this.verifyName(computer.getName());
		this.verifyDate(computer.getIntroduced(), computer.getDiscontinued());
		this.verifyCompanyId(computer.getCompanyId());
	}

	/**
	 * Verify id input.
	 * 
	 * @param id the id
	 * @throws ValidatorException
	 */
	private void verifyId(String id) throws ValidatorException {

		if (id == null || id.isEmpty()) {
			return;

		} else {

			try {

				parsedId = Long.parseLong(id);

				if (parsedId <= 0) {
					throw new ValidatorException("Id cannot be inferior or equal to 0.");
				}

			} catch (NumberFormatException e) {
				throw new ValidatorException("Id is not a number.");
			}
		}
	}

	/**
	 * Verify name input.
	 * 
	 * @param name the name
	 * @throws ValidatorException
	 */
	private void verifyName(String name) throws ValidatorException {

		if (name == null || name.isEmpty()) {
			throw new ValidatorException("Name cannot be empty.");
		}
	}

	/**
	 * Verify date input & order.
	 * 
	 * @param introduced   the introduced date
	 * @param discontinued the discontinued date
	 * @throws ValidatorException
	 */
	private void verifyDate(String introduced, String discontinued) throws ValidatorException {

		if ((introduced == null || introduced.isEmpty()) && (discontinued == null || discontinued.isEmpty())) {
		}

		else if (discontinued == null || discontinued.isEmpty()) {
			parsedIntroduced = parseDate(introduced);

		} else if (introduced == null || discontinued.isEmpty()) {
			parsedDiscontinued = parseDate(discontinued);

		} else {
			parsedIntroduced = parseDate(introduced);
			parsedDiscontinued = parseDate(discontinued);

			if (parsedIntroduced.after(parsedDiscontinued)) {
				throw new ValidatorException("Introduced date cannot be superior to Discontinued.");
			}
		}
	}

	/**
	 * Verify companyId input.
	 * 
	 * @param companyId the company id
	 * @throws ValidatorException
	 */
	private void verifyCompanyId(String companyId) throws ValidatorException {
		if (companyId == null || companyId.isEmpty()) {
		}

		else {

			try {

				parsedCompanyId = Long.parseLong(companyId);

				if (parsedCompanyId <= 0) {
					throw new ValidatorException("Company Id cannot be inferior or equal to 0.");
				}

			} catch (NumberFormatException e) {
				throw new ValidatorException("Company Id is not a number.");
			}
		}
	}

	/**
	 * Parsing date tool.
	 * 
	 * @param date the computer date
	 * @return conversion of string to date
	 * @throws ValidatorException
	 */
	private Date parseDate(String date) throws ValidatorException {

		try {
			return inputFormat.parse(date);
		} catch (ParseException e) {
			throw new ValidatorException("Date is wrongly formatted.");
		}
	}
}
