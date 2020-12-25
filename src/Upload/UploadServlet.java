package Upload;


import JDBC.Upload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 该类用于文件上传，并分用户、分类别存放
 * 上传到u盘中，先分用户文件夹，再分文件类别文件夹
 */
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
    private String username;
    private String contentType;
    private String time;
    private String type;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UploadUtils uploadUtils = new UploadUtils();
        //获取文件上传时间
        Upload upload = new Upload();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        time = dateFormat.format(date);
        //判断是否为多段上传
        if (ServletFileUpload.isMultipartContent(req)) {
            //实例化工厂类  ServletFileUpload构造函数的参数为工厂类对象 FileItemFactory接口
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //得到每一个表单项，并封装到List集合中
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        //获取普通表单项
                        //防止参数乱码
                        fileItem.getString("UTF-8");
                        username = fileItem.getString();
                        System.out.println(username);
                    } else {
                        //获取文件表单项
                        contentType = fileItem.getContentType();
                        System.out.println(contentType);
                        File file = new File("F:" + File.separator + "云盘存储目录" + File.separator + username);
                        if (!file.exists()) {
                            file.mkdir();
                            type = uploadUtils.judge(contentType);
                            fileItem.write(new File(file.getPath() + File.separator + type, fileItem.getName()));
                            upload.insert(username, contentType, time);
                        } else {
                            type = uploadUtils.judge(contentType);
                            if (new File(file.getPath() + File.separator + type, fileItem.getName()).exists()) {
                                resp.setContentType("text/html;charset=utf-8");
                                PrintWriter pw = resp.getWriter();
                                pw.write("该文件已存在");
                            } else {
                                fileItem.write(new File(file.getPath() + File.separator + type, fileItem.getName()));
                                upload.insert(username, contentType, time);
                            }
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}


