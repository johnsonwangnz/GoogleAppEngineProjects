package spring3project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/helloWorld")
	public String helloWorld(Model model)
	{
		model.addAttribute("name","world");
		return "hello/hello";
	}

}
