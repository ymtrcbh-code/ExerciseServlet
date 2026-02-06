import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CustomerDAO;

public class RegistAction implements Action {
    public String execute(
      HttpServletRequest request,HttpServletResponse response) throws Exception { 
            String loginId = request.getParameter("loginId");
            String password = request.getParameter(password);
            CustomerDAO dao = new CustomerDAO();
            boolean exists =dao.search(loginId);
            
            if (exists) {
                return "regist-error.jsp";
            }
            dao.register(loginId, password);
            return "regist-out.jsp";
    }
}
