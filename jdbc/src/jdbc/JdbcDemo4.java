package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo4 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        try {
            //1.指定实现类
            Class.forName("com.mysql.jdbc.Driver");
            //2.sql语句
            String sql = "update book set author = 'ggg' where author = 'haha'";
            //3.连接到数据库
            conn = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test", "mysql_test", "BJjbHSKFGESJrtpP");
            //4.获取执行sql的对象
            stat = conn.createStatement();
            //5.执行sql
            int count = stat.executeUpdate(sql);
            System.out.println(count);
            if(count>=0){
                System.out.println("执行成功");
            }else{
                System.out.println("执行失败");
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //不论之前是否异常，全部释放资源。避免内存溢出
            //当conn异常后，无法执行到stat,避免空指针异常，先判断下。
            if(stat != null){
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
