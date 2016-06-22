package vtac.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vtac.service.*;
import vtac.dao.ClassDAO;
import vtac.model.Class;
import vtac.model.Student;

@Controller 
public class ClassController {
	
	@Autowired
	private ClassService classService;

	@RequestMapping(value = "/index.html")
	public ModelAndView welcomePage(final String successMessage) {
		final ModelAndView modelAndView = new ModelAndView();
		final Map<String, Object> classesMap = classService.listClass();
		modelAndView.addObject("classes", classesMap);
		modelAndView.setViewName("classStudentManagement");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "api/ajax/fetchClasses.html")
    @ResponseBody
    public Map<String, Object> fetchClasses(@RequestParam("pageNumber") final String pageRequested) {
		final Map<String, Object> classesMap = classService.listClass();
		return classesMap;
    }

	@RequestMapping(value = "api/ajax/classCreate.html", method = RequestMethod.POST)
	@ResponseBody
	public Class create(@RequestParam("name") final String name,@RequestParam("location") final String location, @RequestParam("teacher") final String teacher) {

		Class cl = new Class();
		cl.setName(name);
		cl.setLocation(location);
		cl.setTeacher(teacher);

		classService.addClass(cl);

		return cl;
	}

	@RequestMapping(value = "api/ajax/classUpdate.html", method = RequestMethod.POST)
	@ResponseBody
	public Class update(@RequestParam("id") final Integer id, @RequestParam("name") final String name,@RequestParam("location") final String location, @RequestParam("teacher") final String teacher) {

		Class cl = new Class();
		
		cl.setId(id);
		cl.setName(name);
		cl.setLocation(location);
		cl.setTeacher(teacher);

		classService.updateClass(cl);

		return cl;
	}

	@RequestMapping(value = "api/ajax/classDestroy.html")
    @ResponseBody
    public Class destroy(@RequestParam("id") final int id) {
		
		Class cl = new Class();
		cl.setId(id);
		
		classService.removeClass(cl);
		return cl;
    }


}
