package pl.sda.azure.notes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model model) {

		Map<String, String> links = new HashMap<>();
		links.put("hello", "/hello");
		links.put("notes", "/notes");

		model.addAttribute("links", links);

		return "main";
	}
}
