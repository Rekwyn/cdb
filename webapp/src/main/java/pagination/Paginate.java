package pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Computer;
import services.ComputerServices;

@Component
public class Paginate {

	int page;
	int nbOfRows;
	int nbOfPages;
	Long maxNbOfRows;

	@Autowired
	ComputerServices computerServices;

	List<Computer> maxComputers;

	public Paginate() {
	}

	public void setupPagination(int nbOfRows) {
		maxNbOfRows = computerServices.count();
		nbOfPages = (int) (maxNbOfRows / nbOfRows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNbOfRows() {
		return nbOfRows;
	}

	public void setNbOfRows(int nbOfRows) {
		this.nbOfRows = nbOfRows;
	}

	public int getNbOfPages() {
		return nbOfPages;
	}

	public void setNbOfPages(int nbOfPages) {
		this.nbOfPages = nbOfPages;
	}

	public Long getMaxNbOfRows() {
		return maxNbOfRows;
	}

	public void setMaxNbOfRows(Long maxNbOfRows) {
		this.maxNbOfRows = maxNbOfRows;
	}

	public List<Computer> getMaxComputers() {
		return maxComputers;
	}

	public void setMaxComputers(ArrayList<Computer> maxComputers) {
		this.maxComputers = maxComputers;
	}
}
