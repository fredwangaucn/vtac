package vtac.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {
	 
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="FIRSTNAME")
	private String firstName;

	@Column(name="LASTNAME")
	private String lastName;

	@Column(name="AGE")
	private Integer age;

	@Column(name="GPA")
	private Double gpa;

	@Column(name="CLASSID")
	private Integer classId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public String setFirstName(String firstName) {
		return this.firstName=firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public String setLastName(String lastName) {
		return this.lastName=lastName;
	}

	public Integer getAge() {
		return age;
	}
	public Integer setAge(Integer age) {
		return this.age=age;
	}

	public Double getGpa() {
		return gpa;
	}
	public Double setGpa(Double gpa) {
		return this.gpa=gpa;
	}
	
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
}
