package paoxiao.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import paoxiao.common.DBUtils;
import paoxiao.dao.ContactDao;
import paoxiao.model.Contact;
public class ContactDaoImpl implements ContactDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;
	/**
	 * 构造方法
	 * 
	 * @param conn
	 *            数据库连接
	 */
	public ContactDaoImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public int addContact(Contact Contact) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("insert into Contact(contactname,tel,qq,web,zipcode,fax,address) values(?,?,?,?,?,?,?)");
			pstam.setString(1, Contact.getContactname());
			pstam.setString(2, Contact.getTel());
			pstam.setString(3, Contact.getQq());
			pstam.setString(4, Contact.getWeb());
			pstam.setString(5, Contact.getZipcode());
			pstam.setString(6, Contact.getFax());
			pstam.setString(7, Contact.getAddress());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("增加Contact出错.错误信息是 ：" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	@Override
	public int updateContact(Contact Contact) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("update Contact set contactname = ?,tel= ?,qq= ?,web= ?,zipcode= ?,fax= ?,"
					+ "address= ? where contactid = ?");
			pstam.setString(1, Contact.getContactname());
			pstam.setString(2, Contact.getTel());
			pstam.setString(3, Contact.getQq());
			pstam.setString(4, Contact.getWeb());
			pstam.setString(5, Contact.getZipcode());
			pstam.setString(6, Contact.getFax());
			pstam.setString(7, Contact.getAddress());
			pstam.setInt(8, Contact.getContactid());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("修改Contact出错.错误信息是 ：" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	@Override
	public int deleteContact(Contact Contact) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("DELETE FROM Contact WHERE contactid =?");
			pstam.setInt(1, Contact.getContactid());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("删除Contact出错.错误信息是 ：" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	@Override
	public int ContactCount() {
		int result = 0;
		PreparedStatement pstam = null;
		ResultSet rs = null;
		try {
			pstam = conn.prepareStatement("select count(*) from Contact");
			rs = pstam.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			System.out.println("在查询统计Contact的时候出错了.错误信息是 ：" + e.getMessage());
		} finally {
			DBUtils.closeStatement(rs, pstam);
		}
		return result;
	}
	@Override
	public List<Contact> findAllContact(Map<String, Object> map) {
		List list = new ArrayList();
		PreparedStatement pstam = null;
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		try {
			pstam = conn.prepareStatement("select * from Contact limit ?,?");
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			while (rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount(); 
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			System.out.println("在查询全部Contact的时候出错了.错误信息是 ：" + e.getMessage());
		} finally {
			DBUtils.closeStatement(rs, pstam);
		}
		return list;
	}
}
