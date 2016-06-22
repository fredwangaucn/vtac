package vtac.dao;

import java.util.List;
import java.util.Map;

import vtac.model.Student;

public interface StudentDAO { 
	public void addStudent(Student st);
	public void updateStudent(Student st);
	public List<Student> listStudent();
	public Map<String, Object> listStudent(Integer classId);
	public void removeStudent(Student st);
}
