import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse; 
import jakarta.servlet.http.HttpSession; 
 
import bean.Customer; 
import dao.CustomerDAO; 
 
public class LoginAction implements Action { 
 public String execute( 
  HttpServletRequest request,HttpServletResponse response) throws Exception { 
  HttpSession session=request.getSession(); 
   
  Customer customer=(Customer)session.getAttribute("customer"); 
  if (customer!=null) { 
   return "index.jsp"; 
  } 
   
  String loginId  =request.getParameter("loginId"); 
  String password =request.getParameter("password"); 
  CustomerDAO dao =new CustomerDAO(); 
  customer=dao.search(loginId, password); 
 
  if (customer!=null) { 
   session.setAttribute("customer",customer); 
   return "WEB-INF/login-out.jsp"; 
  } 
  return "WEB-INF/login-error.jsp"; 
 
 } 
 
}