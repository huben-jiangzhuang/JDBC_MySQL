package jdbc;

//驱动管理类
import java.sql.DriverManager;
//数据库连接类
import java.sql.Connection;
//执行sql对象
import java.sql.Statement;
//结果集对象
/*
四、ResultSet
    1.方法
        *boolean next() 游标向下移动一行,并判断当前行是否有数据，有返回true。(游标默认在第一行之前，第一次调用next()游标移动到第一行)
        *Xxx getXxx(列号或列名) 获取某一行某一列的值。Xxx代表数据类型，每一列的数据类型不同。列号从1开始。
 */
import java.sql.ResultSet;
//执行sql对象
import java.sql.PreparedStatement;




public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1.导入驱动jar包
        //2.注册驱动
     /*
    一、DriverManager：驱动管理类
    功能：
         1）注册驱动方法：导入都是java.sql的接口，面向接口的编程。需要使用一个方法指定使用那个数据库的实现类
         方法：static void registerDriver(Driver driver)
         代码实现：Class.forName("com.mysql.jdbc.Driver");告诉程序，用的左侧目录树中libs_mysql-connector-java-5.1.49-bin.jar_com.mysql.jdbc.Driver
         打开com.mysql.jdbc.Driver，有一个静态代码块
         static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
        注意：mysql5之后可以省略这个步骤（建议添加），因为在libs_mysql-connector-java-5.1.49-bin.jar_META-INF_services_java.sql.Driver中默认添加了驱动。
        2）获取数据库连接
        方法：static Connection	getConnection(String url, String user, String password)尝试建立与给定数据库URL的连接。
        代码实现: DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test", "mysql_test", "BJjbHSKFGESJrtpP")
        参数：
            * url:连接不同数据库，语法不同，这里是mysql。jdbc:mysql://IP:端口/数据库名称
            * user:用户名
            * password:密码
     */
        Class.forName("com.mysql.jdbc.Driver");
        //3.获取数据库连接对象
        /*二、Connection：数据库连接接口
         功能
            1）获取执行sql的对象
            * Statement createStatement()创建一个 Statement对象，用于将SQL语句发送到数据库。
            * PreparedStatement	prepareStatement(String sql)创建一个 PreparedStatement对象，用于将参数化的SQL语句发送到数据库。
            2）管理事务
            开启事务：void setAutoCommit(boolean autoCommit)将此连接的自动提交模式设置为给定状态。
            提交事务：void commit()使自上次提交/回滚以来所做的所有更改都将永久性，并释放此 Connection对象当前持有的任何数据库锁。
            回滚事务：void rollback()撤消在当前事务中所做的所有更改，并释放此 Connection对象当前持有的任何数据库锁。
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test", "mysql_test", "BJjbHSKFGESJrtpP");
        //4.定义sql语句
        String sql = "update book set author = 'haha' where author = 'yjj' ";
        //5.获取执行sql的对象 Statement
        /*三、Statement：用于执行静态SQL语句并返回其生成的结果的对象。
        功能
         1）boolean	execute(String sql) 执行给定的所有SQL语句
         2）int	executeUpdate(String sql) 执行DML(INSERT,UPDATE,DELETE)返回影响行数，DDL(create，alter，drop)不返回结果。
         3）ResultSet executeQuery(String sql)执行给定的select语句，返回单个 ResultSet对象
         */
        Statement statement = conn.createStatement();
        //6.执行sql
        int count = statement.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        statement.close();
        conn.close();
    }
}
