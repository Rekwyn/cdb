package services;

import java.util.List;

import dto.ComputerDTO;
import exception.ValidatorException;

public interface ComputerServices {

	/**
	 * All methods availables for classes who implements this interface.
	 */
	public Long count();

	public List<ComputerDTO> search(ComputerDTO computerDto) throws ValidatorException;

	public List<ComputerDTO> getAll(int page, int nbOfRows, String sort);

	public ComputerDTO get(String id);

	public void add(ComputerDTO computerDto) throws ValidatorException;

	public void update(ComputerDTO computerDto) throws ValidatorException;

	public void delete(ComputerDTO computerDto) throws ValidatorException;
}
