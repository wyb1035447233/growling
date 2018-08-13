package paoxiao.service;
import java.util.List;
import java.util.Map;
import paoxiao.model.Users;

public interface UsersService {
	public Users login(String username,String passwrod);
	public List<Users> findAllUsers(Map<String, Object> map);
	public int UsersCount();
	public int addUsers(Users Users);
	public int updateUsers(Users Users);
	public int deleteUsers(Users Users);
}
