package paoxiao.dao;
import java.util.List;
import java.util.Map;
import paoxiao.model.Users;

public interface UsersDao {
	public Users login(String username,String password);
	public int UsersCount();
	public List<Users> findAllUsers(Map<String, Object> map);
	public int addUsers(Users Users);
	public int updateUsers(Users Users);
	public int deleteUsers(Users Users);
}
