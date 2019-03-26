package servlet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dto.*;
import exception.ValidatorException;
import pagination.*;
import services.*;

@Controller
@RequestMapping("/")
public class ComputerController {

	private Logger logger = LoggerFactory.getLogger(ComputerController.class);

	@Autowired
	private ComputerServices computerServices;

	@Autowired
	private CompanyServices companyServices;

	@Autowired
	private Paginate p;

	@GetMapping("/")
	public ModelAndView getDashboard(@RequestParam(defaultValue = "1", name = "page") String sPage,
			@RequestParam(defaultValue = "50", name = "rows") String sRows,
			@RequestParam(defaultValue = "", name = "sort") String sort,
			@RequestParam(defaultValue = "", name = "searchName") String searchName, ModelAndView mv) {

		int page = Integer.parseInt(sPage);
		int rows = Integer.parseInt(sRows);
		p.setupPagination(rows);
		List<ComputerDTO> computers = computerServices.getAll(page, rows, sort);

		if (!searchName.isEmpty()) {
			computers = new ArrayList<ComputerDTO>();
			ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder().setName(searchName).build();
			try {
				computers = computerServices.search(computer);
			} catch (ValidatorException e) {
				logger.error(e.getMessage());
			}
		}

		mv.setViewName("dashboard");
		mv.addObject("computers", computers);
		mv.addObject("page", page);
		mv.addObject("totalPage", p.getNbOfPages());
		mv.addObject("maxNbOfRows", p.getMaxNbOfRows());
		mv.addObject("nbOfRows", rows);
		mv.addObject("sort", sort);

		logger.info("Redirect to Dashboard page.");
		return mv;
	}

	@GetMapping("/edit")
	public ModelAndView getEdit(@RequestParam(name = "id") String id, ModelAndView mv) {

		List<CompanyDTO> companies = new ArrayList<>();
		companies = companyServices.getAll();

		mv.setViewName("editComputer");
		mv.addObject("id", id);
		mv.addObject("companies", companies);

		logger.info("Redirect to EditComputer page.");
		return mv;
	}

	@PostMapping("/edit")
	public RedirectView postEdit(@RequestParam(name = "id") String id,
			@RequestParam(name = "computerName") String computerName,
			@RequestParam(name = "introduced") String introduced,
			@RequestParam(name = "discontinued") String discontinued,
			@RequestParam(name = "companyId") String companyId, ModelAndView mv) {

		ComputerDTO cptrDto = new ComputerDTO.ComputerDTOBuilder().setId(id).setName(computerName)
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompanyId(companyId).build();

		try {
			computerServices.update(cptrDto);
		} catch (ValidatorException e) {
			logger.error(e.getMessage());
		}

		logger.info("Redirect to Dashboard page.");
		return new RedirectView("/webapp");

	}

	@GetMapping("/add")
	public ModelAndView getAdd(ModelAndView mv) {

		List<CompanyDTO> companies = new ArrayList<CompanyDTO>();
		companies = companyServices.getAll();

		mv.setViewName("addComputer");
		mv.addObject("companies", companies);

		logger.info("Redirect to AddComputer page.");
		return mv;
	}

	@PostMapping("/add")
	public RedirectView postAdd(@RequestParam(name = "computerName") String computerName,
			@RequestParam(name = "introduced") String introduced,
			@RequestParam(name = "discontinued") String discontinued,
			@RequestParam(name = "companyId") String companyId) {

		ComputerDTO computerDto = new ComputerDTO.ComputerDTOBuilder().setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyId(companyId).build();

		try {
			computerServices.add(computerDto);
			logger.info("Computer has been correctly created.");
		} catch (ValidatorException e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("Redirect to Dashboard.");
		return new RedirectView("/webapp");
	}

	@PostMapping("/delete")
	public RedirectView postDelete(@RequestParam(name = "selection") String selection) {

		String[] idTable = selection.split(",");

		for (String idComputer : idTable) {
			ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder().setName("Delete").setId(idComputer).build();

			try {
				computerServices.delete(computer);
			} catch (ValidatorException e) {
				logger.error(e.getMessage());
			}
		}

		logger.info("Redirect to Dashboard page.");
		return new RedirectView("/webapp");
	}
}
