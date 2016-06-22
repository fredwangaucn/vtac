package vtac.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vtac.service.*;
import vtac.model.Class;
import vtac.model.Student;

@Controller 
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/fetchStudents.html")
	public ModelAndView welcomePage(final String successMessage) {
		final ModelAndView modelAndView = new ModelAndView();
		final List<Student> studentsList = studentService.listStudent();
        modelAndView.addObject("students", studentsList);
		modelAndView.setViewName("classes");
		return modelAndView;
	}
	
	
	/**
     * This method retrieves students list of a selected class.
     * @param pageRequested : requested page number of type {@code String}
     * @param classId : the id of the selected class of type {@code String}
     * @return students list  
     */
	@RequestMapping(value = "api/ajax/fetchStudentsByClassId.html")
    @ResponseBody
    public Map<String, Object> fetchStudentsByClassId(@RequestParam("pageNumber") final String pageRequested, @RequestParam("classId") String classId) {
	
		final Map<String, Object> objectStudents = studentService.listStudent(Integer.parseInt(classId));
	
		return objectStudents;
    }
	
	
	@RequestMapping(value = "api/ajax/studentCreate.html", method = RequestMethod.POST)
	@ResponseBody
	public Student create(@RequestParam("firstName") final String firstName,@RequestParam("lastName") final String lastName, @RequestParam("age") final Integer age, @RequestParam("gpa") final Double gpa, @RequestParam("classId") final Integer classId) {

		Student st = new Student();
		st.setFirstName(firstName);
		st.setLastName(lastName);
		st.setAge(age);
		st.setGpa(gpa);
		st.setClassId(classId);
		
		studentService.addStudent(st);

		return st;
	}
	
	@RequestMapping(value = "api/ajax/studentUpdate.html", method = RequestMethod.POST)
	@ResponseBody
	public Student update(@RequestParam("id") final Integer id, @RequestParam("firstName") final String firstName,@RequestParam("lastName") final String lastName, @RequestParam("age") final Integer age, @RequestParam("gpa") final Double gpa, @RequestParam("classId") final Integer classId) {

		Student st = new Student();
		st.setId(id);
		st.setFirstName(firstName);
		st.setLastName(lastName);
		st.setAge(age);
		st.setGpa(gpa);
		st.setClassId(classId);
		
		studentService.updateStudent(st);

		return st;
	}

	@RequestMapping(value = "api/ajax/studentDestroy.html")
    @ResponseBody
    public Student destroy(@RequestParam("id") final int id) {
		
		Student st = new Student();
		st.setId(id);
		
		studentService.removeStudent(st);
		
		return st;
    }
	
}
