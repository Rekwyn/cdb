package servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.ComputerDTO;
import exception.ValidatorException;
import pagination.Paginate;
import services.ComputerServices;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	
	private Logger logger = LoggerFactory.getLogger(ComputerController.class);
	
	@Autowired
	private ComputerServices computerServices;
	
	@Autowired
	private Paginate p;
	
	@GetMapping("/get/{id}") 
	public ComputerDTO getComputer(@PathVariable String id) {
		
		return computerServices.get(id);
	}
	
	@GetMapping("/list")
	public List<ComputerDTO> listComputers(@RequestParam(defaultValue = "1", name = "page") String sPage,
			@RequestParam(defaultValue = "50", name = "rows") String sRows,
			@RequestParam(defaultValue = "", name = "sort") String sort) {
		
		int page = Integer.parseInt(sPage);
		int rows = Integer.parseInt(sRows);
		p.setupPagination(rows);
		List<ComputerDTO> computers = computerServices.getAll(page, rows, sort);

		return computers;
	}
	
	@GetMapping("/search/{name}")
	public List<ComputerDTO> searchComputers(@PathVariable String name) {

		ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder().setName(name).build();
		
		try {
			return computerServices.search(computer);
		} catch (ValidatorException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	@PostMapping("/add") 
	public ResponseEntity<ComputerDTO> addComputer(ComputerDTO computerDto) {
		
		try {
			computerServices.add(computerDto);
			logger.info("Computer has been correctly created.");
			return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.CONFLICT);
		}   
	}
	
	@PostMapping("/delete/{id}")
	public void deleteComputer(@PathVariable String id) {

		try {
			computerServices.delete(id);
			logger.info("Computer has been correctly deleted.");
		} catch (ValidatorException e) {
			logger.error(e.getMessage(), e);
		} 
	}
	
	@PostMapping("/update")
	public ResponseEntity<ComputerDTO> updateComputer(ComputerDTO computerDto) {
		
		try {
			computerServices.update(computerDto);
			logger.info("Computer has been correctly updated.");
			return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.OK);
		} catch (ValidatorException e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<ComputerDTO>(computerDto, HttpStatus.CONFLICT);
		}
	}
}
