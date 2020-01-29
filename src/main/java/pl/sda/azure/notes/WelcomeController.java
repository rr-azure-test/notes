package pl.sda.azure.notes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	@Value("${welcome.message}")
	private String message;

	@GetMapping("/hello")
	public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

		if (name == null || name.isEmpty()) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("message", name);
		}

		return "welcome";
	}
}
