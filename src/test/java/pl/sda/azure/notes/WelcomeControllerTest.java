package pl.sda.azure.notes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WelcomeController.class)
class WelcomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoteRepository noteRepository;

	@Test
	public void hello() throws Exception {
		mockMvc.perform(get("/hello").param("name", "Azure!"))
				.andExpect(status().isOk())
				.andExpect(view().name("welcome"))
				.andExpect(model().attribute("message", equalTo("Azure!")))
				.andExpect(content().string(containsString("Hello, Azure!")));
	}
}
