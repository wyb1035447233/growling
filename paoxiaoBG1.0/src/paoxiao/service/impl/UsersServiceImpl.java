package paoxiao.service.impl;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import paoxiao.common.DBUtils;
import paoxiao.dao.UsersDao;
import paoxiao.dao.impl.UsersDaoImpl;
import paoxiao.model.Users;
import paoxiao.service.UsersService;
public class UsersServiceImpl implements UsersService {
	private static final UsersService instance = new UsersServiceImpl();
	public static UsersService getInstance() {
		return instance;
	}
	@Override
	public int addUsers(Users Users) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			UsersDao UsersDao = new UsersDaoImpl(conn);
			result = UsersDao.addUsers(Users);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("增加Users错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	@Override
	public int updateUsers(Users Users) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			UsersDao UsersDao = new UsersDaoImpl(conn);
			result = UsersDao.updateUsers(Users);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("更新Users错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	@Override
	public int deleteUsers(Users Users) {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			UsersDao UsersDao = new UsersDaoImpl(conn);
			result = UsersDao.deleteUsers(Users);
			DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("删除Users错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	public List findAllUsers(Map<String, Object> map) {
		Connection conn = null;
		List<Users> Userss = null;
		try {
			conn = DBUtils.getConnection();
			UsersDao UsersDao = new UsersDaoImpl(conn);
			Userss = UsersDao.findAllUsers(map);
		} catch (Exception e) {
			System.out.println("查询所有Users错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return Userss;
	}
	@Override
	public int UsersCount() {
		Connection conn = null;
		int result = 0;
		try {
			conn = DBUtils.getConnection();
			UsersDao UsersDao = new UsersDaoImpl(conn);
			result = UsersDao.UsersCount();
		} catch (Exception e) {
			System.out.println("查询统计所有Users错误" + e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return result;
	}
	@Override
	public Users login(String username, String passwrod) {
		Users users = null;
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			UsersDao usersDao = new UsersDaoImpl(conn);
			users = usersDao.login(username, passwrod);
		} catch (Exception exception) {
			System.out.println("登录查询用户是出现异常：" + exception.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
		}
		return users;
	}
}
