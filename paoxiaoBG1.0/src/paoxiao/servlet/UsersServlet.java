package paoxiao.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONObject;
import paoxiao.model.Users;
import paoxiao.service.ContactService;
import paoxiao.service.UsersService;
import paoxiao.service.impl.ContactServiceImpl;
import paoxiao.service.impl.UsersServiceImpl;
/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsersService UsersService = UsersServiceImpl.getInstance();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		if ("login".equals(action)) {
			doLogin(request, response);
		} else if ("findAll".equals(action)) {
			dofindAllUsers(request, response);
		} else if ("addUsers".equals(action)) {
			doAddUsers(request, response);
		} else if ("deleteUsers".equals(action)) {
			doDelUsers(request, response);
		} else if ("updateUsers".equals(action)) {
			doUpdateUsers(request, response);
		} else if ("exit".equals(action)) {
			doExit(request, response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	/**
	 * 灏辨槸閿�姣乻ession閲岄潰鐨刄sers锛岀劧鍚庤烦杞埌鐧婚檰鐣岄潰
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 鐢ㄦ埛瀛樺湪锛屽彲浠ョ櫥褰�
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			session.setAttribute("user", null);
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	private void doDelUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		Users Users = new Users();
		Users.setUserId(userId);;
		int res = UsersService.deleteUsers(Users);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "delete contact fail !!!");
		}
		String str = JSONObject.toJSONString(map);
		response.getWriter().write(str);
	}
	private void doAddUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users newusers = new Users();
		newusers.setUserHead(request.getParameter("userHead"));
		newusers.setUserPassword(request.getParameter("userPassword"));
		newusers.setUserName(request.getParameter("userName"));
		
		int result = UsersService.addUsers(newusers);
		// 鐢熸垚杩斿洖缁撴灉鐨刴ap
		Map<String, Object> map = new HashMap<String, Object>();
		if (result > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "Save user fail !");
		}
		// 灏唌ap杞垚JSON瀵硅薄
		String str = JSONObject.toJSONString(map);
		// 杩斿洖缁撴灉
		response.getWriter().write(str);
	}
	private void dofindAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// page锛氶〉鐮侊紝璧峰鍊� 1銆� rows锛氭瘡椤垫樉绀鸿銆� page涓哄墠鍙拌鏌ヨ鐨勯〉锛宺ows涓哄墠鍙扮殑姣忛〉璁板綍鏁�
		int rows = Integer.parseInt(request.getParameter("rows"));
		// System.out.println(rows);
		int page = Integer.parseInt(request.getParameter("page"));
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);
		// 鏌ユ壘鎸囧畾椤电殑璁板綍
		List<Users> list = UsersService.findAllUsers(pageMap);
		// System.out.println(list.size());
		// 鏌ユ壘鎬昏褰曟暟
		int total = UsersService.UsersCount();
		// JSON涓紝total璁板綍鎬绘暟锛宺ows璁板綍銆倀otal涓哄悗鍙拌繑鍥炵殑锛堟暟鎹簱鐨勶級鎬昏褰曟暟锛岋紙绗簩涓級rows涓哄悗鍙拌繑鍥炵殑json瀵硅薄鏁扮粍銆�
		map.put("rows", list);
		map.put("total", total);
		String str = JSONObject.toJSONString(map);
		// System.out.println(map.toString());
		response.getWriter().write(str);
	}
	private void doUpdateUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Users updateuser = new Users();
		updateuser.setUserId(Integer.parseInt(request.getParameter("userId")));
		updateuser.setUserHead(request.getParameter("userHead"));
		updateuser.setUserPassword(request.getParameter("userPassword"));
		updateuser.setUserName(request.getParameter("userName"));
		int result = UsersService.updateUsers(updateuser);
		// 鐢熸垚杩斿洖缁撴灉鐨刴ap
		Map<String, Object> map = new HashMap<String, Object>();
		if (result > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "Save user fail !");
		}
		// 灏唌ap杞垚JSON瀵硅薄
		String str = JSONObject.toJSONString(map);
		// 杩斿洖缁撴灉
		response.getWriter().write(str);
	}
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 鑾峰彇鐧诲綍鐨勫弬鏁�
		String username = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		// 璋冪敤service鐨勬柟娉�
		UsersService usersService = UsersServiceImpl.getInstance();
		// 杩斿洖鍊兼斁鍦╱sers瀵硅薄
		Users users = usersService.login(username, passwd);
		// 鍒ゆ柇鐢ㄦ埛鏄惁瀛樺湪
		if (users == null) {
			// 鐢ㄦ埛涓嶅瓨鍦�
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			// 鐢ㄦ埛瀛樺湪锛屽彲浠ョ櫥褰�
			HttpSession session = request.getSession();
			session.setAttribute("user", users);
			response.sendRedirect(request.getContextPath() + "/Admin/index.jsp");
		}
	}
}
