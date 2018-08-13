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
import paoxiao.dao.UsersDao;
import paoxiao.model.Users;
public class UsersDaoImpl implements UsersDao {
	/**
	 * 鏁版嵁搴撹繛鎺�
	 */
	private Connection conn;
	/**
	 * 鏋勯�犳柟娉�
	 * 
	 * @param conn
	 *            鏁版嵁搴撹繛鎺�
	 */
	public UsersDaoImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public int addUsers(Users Users) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("insert into user(userHead,userPassword,userName) values(?,?,?)");			
			pstam.setString(1, Users.getUserHead());
			pstam.setString(2, Users.getUserPassword());
			pstam.setString(3, Users.getUserName());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("澧炲姞Users鍑洪敊.閿欒淇℃伅鏄� 锛�" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	@Override
	public int updateUsers(Users Users) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("update user set userHead= ?,userPassword= ?,userName= ? where userId = ?");
			pstam.setString(1, Users.getUserHead());
			pstam.setString(2, Users.getUserPassword());
			pstam.setString(3, Users.getUserName());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("淇敼Users鍑洪敊.閿欒淇℃伅鏄� 锛�" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	@Override
	public int deleteUsers(Users Users) {
		int result = 0;
		PreparedStatement pstam = null;
		try {
			pstam = conn.prepareStatement("DELETE FROM user WHERE userId =?");
			pstam.setInt(1, Users.getUserId());
			result = pstam.executeUpdate();
		} catch (SQLException e) {
			System.out.println("鍒犻櫎Users鍑洪敊.閿欒淇℃伅鏄� 锛�" + e.getMessage());
		} finally {
			DBUtils.closeStatement(null, pstam);
		}
		return result;
	}
	public int UsersCount() {
		int result = 0;
		PreparedStatement pstam = null;
		ResultSet rs = null;
		try {
			pstam = conn.prepareStatement("select count(*) from user");
			rs = pstam.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			System.out.println("鍦ㄦ煡璇㈢粺璁sers鐨勬椂鍊欏嚭閿欎簡.閿欒淇℃伅鏄� 锛�" + e.getMessage());
		} finally {
			DBUtils.closeStatement(rs, pstam);
		}
		return result;
	}
	@Override
	public List<Users> findAllUsers(Map<String, Object> map) {
		List list = new ArrayList();
		PreparedStatement pstam = null;
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		try {
			pstam = conn.prepareStatement("select * from user limit ?,?");
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
			System.out.println("鍦ㄦ煡璇㈠叏閮║sers鐨勬椂鍊欏嚭閿欎簡.閿欒淇℃伅鏄� 锛�" + e.getMessage());
		} finally {
			DBUtils.closeStatement(rs, pstam);
		}
		return list;
	}
	@Override
	public Users login(String username, String passwrod) {
		Users user = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			pStatement = conn.prepareStatement("select * from user where userName=? and userPassword=?");
			pStatement.setString(1, username);
			pStatement.setString(2, passwrod);
			rSet = pStatement.executeQuery();
			if (rSet.next()) {
				user = new Users();
				user.setUserId(rSet.getInt("userId"));
				user.setUserName(rSet.getString("userName"));
				user. setUserPassword(rSet.getString("userPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(rSet, pStatement);
		}
		return user;
	}
}
