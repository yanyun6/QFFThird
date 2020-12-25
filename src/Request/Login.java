package Request;

import JDBC.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 该类用于检验验证码、用户名，密码是否正确、以及判断用户是否已经登录
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        //是生成的初始验证码
        String checkcode_str = (String) session.getAttribute("checkcode_str");
        String[] usernames = map.get("username");
        String username = usernames[0];
        String[] checkCodes = map.get("checkcode");
        String checkcode = checkCodes[0];
        if (username.equals(session.getAttribute("username"))) {
            req.setAttribute("have_login", "该账户已登录");
            req.getRequestDispatcher("/云盘登录页面.jsp").forward(req, resp);
        }
        //这个是map集合获取的表单的验证码
            if (checkcode_str.equalsIgnoreCase(checkcode)) {
                boolean flag = user.loginjudge();
                if (flag == true) {
                    resp.setContentType("text/html;charset=utf-8");
                    try {
                        session.setAttribute("username", username);
                        String msg = URLEncoder.encode("文件上传或下载", "utf-8");
                        resp.sendRedirect("/QFFThirds_war_exploded/" + msg + ".jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    try {
                        req.setAttribute("login_error", "用户名或密码错误");
                        req.getRequestDispatcher("/云盘登录页面.jsp").forward(req, resp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                req.setAttribute("check_error", "验证码错误");
                req.getRequestDispatcher("/云盘登录页面.jsp").forward(req, resp);
            }
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}