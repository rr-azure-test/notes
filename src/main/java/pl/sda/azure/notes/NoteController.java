package pl.sda.azure.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	RedirectAttributesHelper redirectAttributesHelper;

	@GetMapping("/notes")
	public String list(Model model) {
		model.addAttribute("notes", noteRepository.findAllByOrderByPriorityAscIdDesc());
		return "list";
	}

	@GetMapping("/notes/add")
	public String add(Note note) {
		return "add";
	}

	@PostMapping("/notes")
	public String add(@Valid Note note, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "add";
		}

		noteRepository.save(note);

		redirectAttributesHelper.addSuccessAttribute(redirectAttributes, String.format("Note (%s) added", note.getName()));

		return "redirect:/notes";
	}

	@GetMapping(path = "/notes/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {

		model.addAttribute("note", noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Note (#%d) not found!", id))));

		return "edit";
	}

	@PostMapping(path = "/notes/{id}")
	public String edit(@PathVariable Integer id, @Valid Note note, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "edit";
		}

		Note fromBase = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Note (#%d) not found!", id)));

		fromBase.setName(note.getName());
		fromBase.setPriority(note.getPriority());

		noteRepository.save(fromBase);

		redirectAttributesHelper.addSuccessAttribute(redirectAttributes, String.format("Note (#%d) changed", id));

		return "redirect:/notes";
	}

	@PostMapping(path = "/notes/{id}/remove")
	public String remove(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

		Note fromBase = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Note (#%d) not found!", id)));

		noteRepository.delete(fromBase);

		redirectAttributesHelper.addSuccessAttribute(redirectAttributes, String.format("Note (%s) removed", fromBase.getName()));

		return "redirect:/notes";
	}

}
