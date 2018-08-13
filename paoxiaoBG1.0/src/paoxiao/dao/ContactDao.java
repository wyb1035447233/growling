package paoxiao.dao;
import java.util.List;
import java.util.Map;
import paoxiao.model.Contact;
public interface ContactDao {
	public int ContactCount();
	public List<Contact> findAllContact(Map<String, Object> map);
	public int addContact(Contact Contact);
	public int updateContact(Contact Contact);
	public int deleteContact(Contact Contact);
}	
