package MultiDownLoad;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/multidownload")
public class DownLoadTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String filepath = "http://localhost:8080/QFFThird_war_exploded/homework.jpg";
        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,4);
        load.downloadPart();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

//    public static void main(String[] args) {
//        String filepath = "http://127.0.0.1:8080/file/loadfile.mkv";
//        MultiTheradDownLoad load = new MultiTheradDownLoad(filepath ,4);
//        load.downloadPart();
//    }
}