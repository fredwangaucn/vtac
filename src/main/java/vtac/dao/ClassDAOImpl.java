package vtac.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vtac.model.Class;
import vtac.model.Student;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDAOImpl implements ClassDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addClass(Class cl) {
		sessionFactory.getCurrentSession().save(cl);
	}

	public Map<String, Object> listClass() {

		final Map<String, Object> classListMap = new HashMap();
		
		List<Class> resultList=
        sessionFactory.getCurrentSession()
        .createQuery("from Class")
        .list();
		
		if(null!=resultList&&resultList.size()>0){
			Class theFirstClass=resultList.get(0);
			classListMap.put("theFirstClassId", theFirstClass.getId());
		}
		
		classListMap.put("classList", resultList);
		
		return classListMap;
	}

	public void removeClass(Class cl) {
		Class cl0 = (Class) sessionFactory.getCurrentSession().load(
				Class.class, cl.getId());
		if (null != cl0) {
			sessionFactory.getCurrentSession().delete(cl0);
		}
	}
 
	public void updateClass(Class cl) {
		Class cl0 = (Class) sessionFactory.getCurrentSession().load(
				Class.class, cl.getId());
		if (null != cl0) {
			sessionFactory.getCurrentSession().update(cl);
		}
	}

}

