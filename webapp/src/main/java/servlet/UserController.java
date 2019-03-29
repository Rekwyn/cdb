package servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dto.UserDTO;
import dto.UserDTO.UserDTOBuilder;
import services.UserServicesImpl;

@Controller
public class UserController {
	
	@Autowired
	UserServicesImpl myUserServices;
	
	@GetMapping("/register")
	public ModelAndView showRegister() {
		
		UserDTO userDto = new UserDTO();
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("register");
		mv.addObject("user", userDto);
		return mv;	
	}
	
	@PostMapping("/register")
	public RedirectView saveNewUser(@RequestParam(name = "login") String login,
			@RequestParam(name="password") String password) {
		
		UserDTO newAccountDto = new UserDTO.UserDTOBuilder().setName(login).setPassword(password).build();
		myUserServices.registerNewUserAccount(newAccountDto);
		
		return new RedirectView("/webapp");
	}

}
