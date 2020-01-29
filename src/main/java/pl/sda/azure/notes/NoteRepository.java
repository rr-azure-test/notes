package pl.sda.azure.notes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

	Note findByName(String name);

	List<Note> findAllByOrderByPriorityAscIdDesc();
}
