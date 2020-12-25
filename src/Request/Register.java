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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 该类用于判断注册时用户名、手机号码、邮箱等是否已经存在
 */
@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String checkcode_str = (String) session.getAttribute("checkcode_str");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String[] checkCodes = parameterMap.get("checkcode");
        String checkcode = checkCodes[0];
        if (checkcode_str.equalsIgnoreCase(checkcode)) {
            boolean login = user.registerJudge();
            if (login == false) {
                user.Insert();
                resp.setContentType("text/html;charset=utf-8");
                try {
                    PrintWriter pw = resp.getWriter();
                    pw.write("注册成功");
                    String msg = URLEncoder.encode("云盘登录页面", "utf-8");
                    resp.sendRedirect("/QFFThird_war_exploded/" + msg + ".jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                resp.setContentType("text/html;charset=utf-8");
                try {
                    req.setAttribute("register_error", "用户名或手机号或电子邮箱已存在");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            req.setAttribute("checkcode_error", "验证码错误");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
