package paoxiao.service;
import java.util.List;
import java.util.Map;
import paoxiao.model.Contact;
public interface ContactService {
	public List<Contact> findAllContact(Map<String, Object> map);
	public int ContactCount();
	public int addContact(Contact Contact);
	public int updateContact(Contact Contact);
	public int deleteContact(Contact Contact);
}
