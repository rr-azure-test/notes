package pl.sda.azure.notes;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class NoteRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private NoteRepository noteRepository;

	@Test
	public void shouldFindNoteNamedTest() {
		// given
		Note test = new Note("test", 5);
		entityManager.persist(test);
		entityManager.flush();

		// when
		Note found = noteRepository.findByName("test");

		// then
		assertThat(found.getName()).isEqualTo(test.getName());
		assertThat(found.getPriority()).isEqualTo(test.getPriority());
	}
}