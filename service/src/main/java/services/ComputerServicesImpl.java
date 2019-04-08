package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ComputerDao;
import dto.ComputerDTO;
import exception.ValidatorException;
import mapper.MapperFactory;
import model.Computer;

@Service
public class ComputerServicesImpl implements ComputerServices {

	@Autowired
	private ComputerDao computerDao;

	@Autowired
	private MapperFactory mapper;

	private Computer computer;

	public ComputerServicesImpl() {
	}

	/**
	 * Call the associated DAO methods.
	 */
	@Override
	public Long count() {
		return computerDao.count();
	}

	@Override
	public List<ComputerDTO> search(ComputerDTO computerDto) throws ValidatorException {
		List<ComputerDTO> computers = new ArrayList<>();

		for (Computer current : computerDao.search(mapper.getComputerMapper().mapDtoToObject(computerDto))) {
			computers.add(mapper.getComputerMapper().mapObjectToDto(current));
		}

		return computers;
	}

	@Override
	public List<ComputerDTO> getAll(int page, int nbOfRows, String sort) {
		List<ComputerDTO> computers = new ArrayList<>();

		for (Computer current : computerDao.getAll(page, nbOfRows, sort)) {
			computers.add(mapper.getComputerMapper().mapObjectToDto(current));
		}

		return computers;
	}

	@Override
	public ComputerDTO get(String id) {
		return mapper.getComputerMapper().mapObjectToDto(computerDao.get(Long.parseLong(id)));
	}

	@Override
	public void add(ComputerDTO computerDto) throws ValidatorException {
		computerDao.add(mapper.getComputerMapper().mapDtoToObject(computerDto));
	}

	@Override
	public void update(ComputerDTO computerDto) throws ValidatorException {
		computerDao.update(mapper.getComputerMapper().mapDtoToObject(computerDto));
	}

	@Override
	public void delete(ComputerDTO computerDto) throws ValidatorException {
		computerDao.delete(mapper.getComputerMapper().mapDtoToObject(computerDto));
	}
}
