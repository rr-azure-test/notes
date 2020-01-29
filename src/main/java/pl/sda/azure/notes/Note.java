package pl.sda.azure.notes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Note {

	private static final int DEFAULT_PRIORITY = 6;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String name;

	@Min(value = 1)
	@Max(value = 6)
	private Integer priority;


	public Note() {
	}

	public Note(String name, Integer priority) {
		this.name = name;
		this.priority = priority;
	}

	public Note(String name) {
		this.name = name;
		this.priority = DEFAULT_PRIORITY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Note note = (Note) o;
		return id.equals(note.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Note{" +
				"id=" + id +
				", name='" + name + '\'' +
				", priority=" + priority +
				'}';
	}
}
