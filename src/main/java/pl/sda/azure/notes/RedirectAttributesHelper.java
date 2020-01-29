package pl.sda.azure.notes;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Component
public class RedirectAttributesHelper {

	private static final String SUCCESS_ATTRIBUTE_NAME = "_OK";
	private static final String FAILURE_ATTRIBUTE_NAME = "_ERROR";


	public void addSuccessAttribute(RedirectAttributes redirectAttributes, String message) {
		redirectAttributes.addFlashAttribute(SUCCESS_ATTRIBUTE_NAME, message);
	}

	public void addFailureAttribute(RedirectAttributes redirectAttributes, String message) {
		redirectAttributes.addFlashAttribute(FAILURE_ATTRIBUTE_NAME, message);
	}

	public void rewriteRedirectAttributesToModel(Model model, RedirectAttributes redirectAttributes) {
		Map<String, ?> attributes = redirectAttributes.getFlashAttributes();
		if (attributes.containsKey(FAILURE_ATTRIBUTE_NAME)) {
			model.addAttribute(FAILURE_ATTRIBUTE_NAME, attributes.get(FAILURE_ATTRIBUTE_NAME));
		}

		if (attributes.containsKey(SUCCESS_ATTRIBUTE_NAME)) {
			model.addAttribute(SUCCESS_ATTRIBUTE_NAME, attributes.get(SUCCESS_ATTRIBUTE_NAME));
		}
	}
}
