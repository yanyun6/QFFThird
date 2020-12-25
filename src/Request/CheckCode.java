package Request;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 该类用于生成一个验证码，并将验证码存储到session中，为验证验证码是否正确做准备
 */
@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //创建一个对象，在内存中存图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //美化图片
        Graphics graphics = bufferedImage.getGraphics();//获取画笔对象
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);//填充背景颜色
        graphics.setColor(Color.BLUE);
        graphics.drawLine(0, 0, width - 1, height - 1);//画边框
        //写验证码
        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghigklmnopqrst0123456789";
        //生成随机角标,并生成随机验证码
        Random random = new Random();
        StringBuilder sb=new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
            graphics.drawString(ch + "", width / 5 * i, height / 2);
        }
        String checkcode_str = sb.toString();
        HttpSession session = req.getSession();
        session.setAttribute("checkcode_str",checkcode_str);
        //生成干扰线
        graphics.setColor(Color.GREEN);
        for (int i = 0; i <10 ; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        //输出图片
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
