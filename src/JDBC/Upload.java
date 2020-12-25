package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 该类用于存储文件上传的用户名、类型、时间到数据库
 */
public class Upload {
    public void insert(String username,String type,String time){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into PublicStorage(username,type,time) values (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, type);
            ps.setString(3, time);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps, connection, resultSet);
        }
    }

}
