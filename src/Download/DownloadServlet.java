package Download;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 该类用于文件下载 但不具有普适性
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要下载的文件名
        String downloadFileName="1.jpg";
        //读取下载的内容（通过ServletContext对象）
        ServletContext servletContext = getServletContext();
        //设置响应的数据类型
        //servletContext 对象可以通过此方法来获取参数（下载文件的路径）的数据类型
        String mimeType = servletContext.getMimeType("/DownloadResource/"+downloadFileName);
        String realPath = servletContext.getRealPath( downloadFileName);
        System.out.println(realPath);
        //resp通过此方法来设置响应头中的数据类型
        resp.setContentType(mimeType);
        System.out.println(mimeType);
        //resp通过此方法设置响应头收到的数据是用来下载的
        //Content-Disposition 内容处理方式
        //attachment 附件（用来下载的）
        //filename 文件名 可以和下载的原文件名不同
        //解决中文乱码问题 +URLEncode.encode("文件名”，“UTF-8")
        if(req.getHeader("User-Agent").contains("Firefox")){   //=?表示编码的开始  ?=表示编码的结束
            resp.setHeader("Content-Disposition","attachment;filename==?UTF-8?B?"+new BASE64Encoder().encode("".getBytes("UTF-8"))+"?=");
        }
        else {
            resp.setHeader("Content-Disposition","attachment;filename="+downloadFileName);
        }
        //获取输入流对象，将要下载的文件读取到服务器端
        InputStream resourceAsStream = servletContext.getResourceAsStream("/DownloadResource/"+downloadFileName);
        System.out.println(resourceAsStream);
        //通过resp对象来获取输出流对象
        ServletOutputStream outputStream = resp.getOutputStream();
        //IO 包里的IOUtils类中的copy方法 读取输入流的数据，复制给输出流输出
        IOUtils.copyLarge(resourceAsStream,outputStream);

    }
}
