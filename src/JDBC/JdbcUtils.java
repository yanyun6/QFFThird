package JDBC;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类，用于连接数据库
 */
public class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /**
     *静态代码块 实例化对象时优先调用，而且只调用一次，静态块的主要作用是属性初始化
     */
    static {
        //读取资源文件，获取值
        //创建properties集合类
        try {
            Properties properties = new Properties();
            //获取src目录下的路径 ClassLoader 类加载器 加载字节码文件进内存
            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
            System.out.println(classLoader);
            URL resource = classLoader.getResource("jDBCUtils.properties");
            System.out.println(resource);
            System.out.println(resource.getPath());
            String path = resource.getPath();
            System.out.println(path);
            properties.load(new FileReader(path));
            url = properties.getProperty("url");
            System.out.println("url" + url);
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }

    public static void close(Statement stmt, Connection conn, ResultSet resultSet) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}


