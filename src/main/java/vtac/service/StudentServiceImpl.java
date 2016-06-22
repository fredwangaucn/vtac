package vtac.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vtac.dao.StudentDAO;
import vtac.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
 
	@Autowired
	private StudentDAO studentDAO;
	
	@Transactional
	public void addStudent(Student st) {
		studentDAO.addStudent(st);
	}

	@Transactional
	public void updateStudent(Student st) {
		studentDAO.updateStudent(st);
	}

	@Transactional
	public List<Student> listStudent() {
		return studentDAO.listStudent();
	}

	@Transactional
	public Map<String, Object> listStudent(Integer classId) {
		return studentDAO.listStudent(classId);
	}
	@Transactional
	public void removeStudent(Student st) {
		studentDAO.removeStudent(st);
	}
}
