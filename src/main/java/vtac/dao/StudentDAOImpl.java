package vtac.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vtac.model.Class;
import vtac.model.Student;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addStudent(Student st) {
		sessionFactory.getCurrentSession().save(st);
	}

	public void updateStudent(Student st) {
		
		Student st0 = (Student) sessionFactory.getCurrentSession().load(
				Student.class, st.getId());
		if (null != st0) {
			sessionFactory.getCurrentSession().update(st);
		}		
		 
	}

	public List<Student> listStudent() {
		return sessionFactory.getCurrentSession().createQuery("from Student").list();
	}

	public Map<String, Object> listStudent(Integer classId) {
		
			final Map<String, Object> studentListMap = new HashMap();
			
			List<Student> resultList=
            sessionFactory.getCurrentSession()
            .createQuery("from Student where classId =?")
            .setParameter(0, classId)
            .list();
			
			studentListMap.put("studentList", resultList);
			
			return studentListMap;
	}

	public void removeStudent(Student st) {
		Student st0 = (Student) sessionFactory.getCurrentSession().load(
				Student.class, st.getId());
		if (null != st0) {
			sessionFactory.getCurrentSession().delete(st0);
		}
	}
}

