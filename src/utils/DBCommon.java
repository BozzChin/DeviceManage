package utils;  
  
import java.sql.*;     
  
public class DBCommon {   
	
        public static Connection conn;//��������     
        public static Statement stmt;//����STMT     
        public ResultSet rs;//��������     
        
        public static String driver = "com.mysql.jdbc.Driver"; //��������     
        public static String url = PropertyReader.getValue("mysql.properties", "url"); //����URL     
        public static String user = PropertyReader.getValue("mysql.properties", "username");; //�����û���     
        public static String password = PropertyReader.getValue("mysql.properties", "password");; //��������   
        
        //����CONN     
        static {     
            try {   
                Class.forName(driver);     
                conn = DriverManager.getConnection(url, user, password);  
                System.out.println("-------���ӳɹ�------");  
            } catch(ClassNotFoundException classnotfoundexception) {     
                  classnotfoundexception.printStackTrace();     
                System.err.println("db: " + classnotfoundexception.getMessage());     
            } catch(SQLException sqlexception) {     
                System.err.println("db.getconn(): " + sqlexception.getMessage());     
            }     
        }   
        
        //���캯����Ĭ�ϼӲ������ļ�Ϊjdbc.driver     
        public DBCommon(){     
            this.conn = this.getConn();  
        }
        
        //����Conn     
        public Connection getConn(){     
            return this.conn;     
        } 
        
        //ִ�в���     
           public void insert(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeInset:" + sqlexception.getMessage());     
            } finally {     
                     
            }     
        } 
           
        //ִ��ɾ��     
        public void delete(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeDelete:" + sqlexception.getMessage());     
            }     
        }  
        
        //ִ�и���     
        public void update(String sql) {     
            try {     
                stmt = conn.createStatement();     
                int i = stmt.executeUpdate(sql);     
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeUpdate:" + sqlexception.getMessage());     
            }     
        } 
        
        //��ѯ�����     
        public ResultSet select(String sql) {     
            try {  
                conn=DriverManager.getConnection(url,user,password);  
                stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);       
                rs = stmt.executeQuery(sql);   
                System.out.println("ȡ�ý����");  
            } catch(SQLException sqlexception) {     
                System.err.println("db.executeQuery: " + sqlexception.getMessage());     
            }     
            return rs;     
        }  
        
        /**   
         *�ر����ݿ����������ݿ�����������ݿ�����   
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
          *�ر����ݿ�����������ݿ����Ӷ���   
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
         
        //������     
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