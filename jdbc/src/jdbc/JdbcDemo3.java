package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo3 {
    public static void main(String[] args) throws Exception {
        //1.设置实现类
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库192.168.132.2:3306/mysql_test","mysql_test", "BJjbHSKFGESJrtpP"
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/my_test", "mysql_test", "BJjbHSKFGESJrtpP");
        //3.创建可操作数据库的对象
        Statement stat = conn.createStatement();
        //4.sql语句
        String sql = "update book set author = 'ggg' where author = 'haha'";
        //5.执行sql
        int i = stat.executeUpdate(sql);
        System.out.println(i);
        //6.释放资源
        stat.close();
        conn.close();
    }
}
