import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeAction implements Action {
    public String execute(
            HttpServletRequest request, HttpServletResponse response ) throws Exception {
        return "login-out.jsp";
    }

}
