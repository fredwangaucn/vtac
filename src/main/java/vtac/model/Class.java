package vtac.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLASS")
public class Class {
	 
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="NAME")
	private String name;

	@Column(name="LOCATION")
	private String location;

	@Column(name="TEACHER")
	private String teacher;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public String setName(String name) {
		return this.name=name;
	}
	
	public String getLocation() {
		return location;
	}
	public String setLocation(String location) {
		return this.location=location;
	}

	public String getTeacher() {
		return teacher;
	}
	public String setTeacher(String teacher) {
		return this.teacher=teacher;
	}
	
	
}
