package vtac.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vtac.dao.ClassDAO;
import vtac.model.Class;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDAO classDAO;
	
	@Transactional
	public void addClass(Class cl) {
		classDAO.addClass(cl);
	}

	@Transactional
	public void updateClass(Class cl) {
		classDAO.updateClass(cl);
	}

	@Transactional
	public Map<String, Object> listClass() {

		return classDAO.listClass();
	}
 
	@Transactional
	public void removeClass(Class cl) {
		classDAO.removeClass(cl);
	}
}
