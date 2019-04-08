package dao;

import java.util.List;

import model.Computer;

public interface ComputerDao {

	/**
	 * Methods availables for classes who implements this interface.
	 */
	public Long count();

	public List<Computer> search(Computer computer);

	public Computer get(Long id);

	public void add(Computer computer);

	public void update(Computer computer);

	public void delete(Computer computer);

	public List<Computer> getAll(int page, int nbOfRows, String sort);
}
