package spring3project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleHelloWorld {

	@RequestMapping(method=RequestMethod.GET)
	public String helloWorld(Model model)
	{
		model.addAttribute("name","world");
		return "hello/hello";
	}
	
}
