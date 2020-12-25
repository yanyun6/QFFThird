package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 该类用于存储用户注册信息到数据库
 * 以及对request包中login类和register类提供调用的方法
 */
public class User {
    private String username;
    private String password;
    private String again;
    private String email;
    private String phonenumber;
    private String birthday;
    private String gender;

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    private String checkcode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgain() {
        return again;
    }

    public void setAgain(String again) {
        this.again = again;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User() {
    }


    public void Insert() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "insert into YunPanUser(User,PassWord,Email,PhoneNumber,Sex,BirthDay,pemail) values (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phonenumber);
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setString(7, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps, connection, resultSet);
        }
    }

    public boolean loginjudge() {
        if (username == null || password == null) {
            return false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "select *from YunPanUser where User=? and PassWord=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection, resultSet);
        }
        return false;

    }
public boolean registerJudge(){
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = JdbcUtils.getConnection();
        String sql = "select *from YunPanUser where User=? or Email=? or PhoneNumber=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3,phonenumber);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        JdbcUtils.close(preparedStatement, connection, resultSet);
    }
    return true;
}

}
