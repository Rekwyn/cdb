package servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDTO;

@Controller
@RequestMapping("/register")
public class UserController {
	
	@GetMapping("/register")
	public ModelAndView showRegister() {
		
		UserDTO userDto = new UserDTO();
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("register");
		mv.addObject("user", userDto);
		
		return mv;	
	}

}
