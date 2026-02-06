package dao; 
 
import bean.Customer; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
 
public class CustomerDAO extends DAO { 
        /* ログイン名とパスワードからDBを検索 */ 
 public Customer search(String loginId, String password) throws Exception { 
  Customer customer=null; 
                /* DBとの接続 */ 
  Connection con=getConnection(); 
                /* SQL文の準備 */ 
  PreparedStatement st; 
  st=con.prepareStatement( 
  "select * from customer where binary login_id=? and binary password=?"); 
  st.setString(1, loginId); 
  st.setString(2, password); 
                /* SQL文の実行 */ 
  ResultSet rs=st.executeQuery(); 
 
  while(rs.next()) { 
            /* DBに該当するlogin/passwordが見つかったら、customer Beanを 
              作成する */ 
   customer=new Customer(); 
   customer.setId(rs.getInt("id")); 
   customer.setLoginId(rs.getString("login_id")); 
   customer.setPassword(rs.getString("password")); 
  } 
                /* DBとの接続クローズ処理 */ 
        rs.close(); 
  st.close(); 
  con.close(); 
  return customer; 
 } 
 
 public boolean serch(String loginId) throws Exseption {
     Connection con=getConnection();

     PreparedStatement st;
     st=con.prepareStatement(
             "select * from customer where binary login_id=?");
     st.setString(1, loginId);
     ResultSet rs=st.executeQuery();

     if (rs.next()) {
         st.close();
         con.close();
         return true;
     }

     rs.close();
     st.close();
     con.close();
     return false;
 }

 public void register(String loginId, String password) throws Exception {
     //Customer customer=null;

     Connection con=getConnection();

     PreparedStatement st;
     st=con.prepareStatement(
             "insert into customer values(null,?,?)");
     st.setString(1, loginId);
     st.setString(2, password);
     st.execute();

     st.close();
     con.close();
 }

}

