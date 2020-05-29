package jdbc;

import java.sql.*;

public class JdbcDemo5 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            //1.指定实现类
            Class.forName("com.mysql.jdbc.Driver");
            //2.sql语句
            String sql = "select * from book";
            //3.连接到数据库
            conn = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test", "mysql_test", "BJjbHSKFGESJrtpP");
            //4.获取执行sql的对象
            stat = conn.createStatement();
            //5.执行sql
            rs= stat.executeQuery(sql);
            //遍历获取数据
            /*
            ResultSet
            1.方法
            *boolean next() 游标向下移动一行,并判断当前行是否有数据，有返回true。(游标默认在第一行之前，第一次调用next()游标移动到第一行)
            *Xxx getXxx(列号或列名) 获取某一行某一列的值。Xxx代表数据类型，每一列的数据类型不同。列号从1开始。
            */
            while(rs.next()){
                String name = rs.getString(1);
                String author = rs.getString(2);
                System.out.println(name+","+author);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //不论之前是否异常，全部释放资源。避免内存溢出
            //当conn异常后，无法执行到stat,避免空指针异常，先判断下。
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
