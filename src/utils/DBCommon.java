package utils;  
  
import java.sql.*;     
  
public class DBCommon {   
	
        public static Connection conn;//定义连接     
        public static Statement stmt;//定义STMT     
        public ResultSet rs;//定义结果集     
        
        public static String driver = "com.mysql.jdbc.Driver"; //定义驱动     
        public static String url = PropertyReader.getValue("mysql.properties", "url"); //定义URL     
        public static String user = PropertyReader.getValue("mysql.properties", "username");; //定义用户名     
        public static String password = PropertyReader.getValue("mysql.properties", "password");; //定义密码   
        
        //设置CONN     
        static {     
            try {   
                Class.forName(driver);     
                conn = DriverManager.getConnection(url, user, password);  
                System.out.println("-------连接成功------");  
            } catch(ClassNotFoundException classnotfoundexception) {     
                  classnotfoundexception.printStackTrace();     
                System.err.println("db: " + classnotfoundexception.getMessage());     
            } catch(SQLException sqlexception) {     
                System.err.println("db.getconn(): " + sqlexception.getMessage());     
            }     
        }   
        
        //构造函数，默认加裁配置文件为jdbc.driver     
        public DBCommon(){     
            this.conn = this.getConn();  
        }
        
        //返回Conn     
        public Connection getConn(){     
            return this.conn;     
        } 
        
        //执行插入     
           public void insert(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeInset:" + sqlexception.getMessage());     
            } finally {     
                     
            }     
        } 
           
        //执行删除     
        public void delete(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeDelete:" + sqlexception.getMessage());     
            }     
        }  
        
        //执行更新     
        public void update(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeUpdate:" + sqlexception.getMessage());     
            }     
        } 
        
        //查询结果集     
        public ResultSet select(String sql) {     
            try {  
                conn=DriverManager.getConnection(url,user,password);  
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);       
                rs = stmt.executeQuery(sql);   
                System.out.println("取得结果集");  
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeQuery: " + sqlexception.getMessage());     
            }     
            return rs;     
        }  
        
        /**   
         *关闭数据库结果集，数据库操作对象，数据库链接   
           @Function: Close all the statement and conn int this instance and close the parameter ResultSet   
           @Param: ResultSet   
           @Exception: SQLException,Exception   
          **/    
         public void close(ResultSet rs) throws SQLException, Exception {     
        
           if (rs != null) {     
             rs.close();     
             rs = null;     
           }     
        
           if (stmt != null) {     
             stmt.close();     
             stmt = null;     
           }     
        
           if (conn != null) {     
             conn.close();     
             conn = null;     
           }     
         }     
        
         /**   
          *关闭数据库操作对象，数据库连接对象   
          * Close all the statement and conn int this instance   
          * @throws SQLException   
          * @throws Exception   
          */    
         public void close() throws SQLException, Exception {     
           if (stmt != null) {     
             stmt.close();     
             stmt = null;     
           }     
        
           if (conn != null) {     
             conn.close();     
             conn = null;     
           }     
         } 
         
        //测试类     
       public static void main(String []args){  
           DBCommon db=new DBCommon();  
           db.getConn();  
          ResultSet rs=db.select("select * from t_user where i_id = 1");  
          try {  
              while(rs.next()){  
                  System.out.println(rs.getInt(1));  
                  System.out.println(rs.getString(3));  
                    
              }  
          } catch (SQLException e) {  
              // TODO Auto-generated catch block  
              e.printStackTrace();  
          }  
       }  
    }    