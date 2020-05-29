package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class jdbcDemo2 {
    public static void main(String[] args) throws Exception {
        //1.导入java.sql包，是面向接口的编程。所以要先指定mysql实现类
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接，存放在Connection中
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test","mysql_test", "BJjbHSKFGESJrtpP");
        //3.定义sql语句
        String sql = "update book set author = 'ggg' where author = 'haha'";
        //4.可执行数据库的对象
        Statement stat = conn.createStatement();
        //5.执行sql语句
        int count = stat.executeUpdate(sql);
        //6.输出被影响行数
        System.out.println(count);
        //7.释放资源
        stat.close();
        conn.close();
    }
}
