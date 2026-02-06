import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistFormAction implements Action {
    public String execute(
            HttpServletRequest request,HttpServletResponse response) throws Exception {
        return "regist-in.jsp";
    }
}
