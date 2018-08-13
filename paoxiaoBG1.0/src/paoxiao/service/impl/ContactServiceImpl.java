package paoxiao.service.impl;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import paoxiao.common.DBUtils;
import paoxiao.dao.ContactDao;
import paoxiao.dao.impl.ContactDaoImpl;
import paoxiao.model.Contact;
import paoxiao.service.ContactService;
public class ContactServiceImpl implements ContactService {
	private static final ContactService instance = new ContactServiceImpl();
	public static ContactService getInstance() {
		return instance;
	}
	@Override
	public int addContact(Contact Contact) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			ContactDao ContactDao = new ContactDaoImpl(conn);
			result = ContactDao.addContact(Contact);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("增加Contact错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	@Override
	public int updateContact(Contact Contact) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			ContactDao ContactDao = new ContactDaoImpl(conn);
			result = ContactDao.updateContact(Contact);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("更新Contact错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	@Override
	public int deleteContact(Contact Contact) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			ContactDao ContactDao = new ContactDaoImpl(conn);
			result = ContactDao.deleteContact(Contact);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("删除Contact错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	public List findAllContact(Map<String, Object> map) {
		Connection conn = null;
		List<Contact> Contacts = null;
		try {
			conn = DBUtils.getConnection();
			ContactDao ContactDao = new ContactDaoImpl(conn);
			Contacts = ContactDao.findAllContact(map);
		} catch (Exception e) {
			System.out.println("查询所有Contact错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return Contacts;
	}
	@Override
	public int ContactCount() {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			ContactDao ContactDao = new ContactDaoImpl(conn);
			result = ContactDao.ContactCount();
		} catch (Exception e) {
			System.out.println("查询统计所有Contact错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
}
