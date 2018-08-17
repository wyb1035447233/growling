package paoxiao.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * 鏁版嵁搴撳伐鍏风被
 */
public class DBUtils {

	/**
	 * 鑾峰彇鏁版嵁搴撹繛鎺�
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/growling";
			String user = "root";
			String password ="123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("鑾峰彇鏁版嵁搴撹繛鎺ュ紓甯�"+e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("鑾峰彇鏁版嵁搴撹繛鎺ュ紓甯�"+e.getMessage());
		}
		
	/*	         1.鍔犺浇jdbc椹卞姩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//2.瀹氫箟杩炴帴url
				String url = "jdbc:oracle:thin:@localhost:1521:neuedu";
				//3.鑾峰彇鏁版嵁搴撹繛鎺�    
				Connection conn = DriverManager.getConnection(url,"scott","tiger");*/
		
		/*try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/lvcity");
			conn = ds.getConnection();
			System.out.println("connect sucess!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		}
*/
		return conn;
	}

	/**
	 * 寮�鍚簨鍔�
	 * 
	 * @param conn
	 */
	public static void beginTransaction(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("寮�鍚簨鍔″紓甯�"+e.getMessage());
		}
	}

	/**
	 * 鎻愪氦浜嬪姟
	 * 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("鍏抽棴浜嬪姟寮傚父"+e.getMessage());
		}
	}

	/**
	 * 鍥炴粴浜嬪姟
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("鍥炴粴浜嬪姟寮傚父"+e.getMessage());
		}
	}

	/**
	 * 鍏抽棴杩炴帴
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("鍏抽棴杩炴帴寮傚父"+e.getMessage());
		}
	}

	/**
	 * 鍏抽棴statement
	 * 
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("鍏抽棴璇彞鎴栫粨鏋滈泦寮傚父"+e.getMessage());
		}
	}
}
