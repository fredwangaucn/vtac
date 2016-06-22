package vtac.dao;

import java.util.List;
import java.util.Map;

import vtac.model.Class;

public interface ClassDAO {
	public void addClass(Class cl);
	public Map<String, Object> listClass();
	public void removeClass(Class cl); 
	public void updateClass(Class cl);
	
}
