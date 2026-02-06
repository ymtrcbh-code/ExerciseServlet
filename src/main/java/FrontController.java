import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import bean.Customer;

import jakarta.servlet.annotation.WebServlet;

/*  ServletクラスとそれにアクセスするURLパターンを定義する。 
http://localhost:8080/ExerciseServlet/*.actionが呼ばれたら、FrontControllerクラスの 
   doPostまたはdoGetを呼ぶ。どちらが呼ばれるかはHTTPメソッドの指定による */
@WebServlet(urlPatterns = ("*.action"))
public class FrontController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            // セッションタイムアウトの判定
            HttpSession session = request.getSession(false);
            if (session == null || session.isNew()) {
                // セッションタイムアウトの場合
                response.sendRedirect("session-error.jsp");
                return;
            }
            try {
            // URLよりアクションのクラス名を作成
            /* ex. /Login.action -> LoginActionに変換 */
            String path = request.getServletPath().substring(1);
            String name = path.replace(".a", "A").replace('/', '.');
            // セッション情報を読み取る
            Customer customer = (Customer)session.getAttribute("customer");
            // ログイン中か否かの判定
            if (customer == null&&!name.equals("LoginAction")) {
                    // ログインせずにアクセスした場合
                response.sendRedirect("invaild-access.jsp");
                return;
            }
            // コントローラークラスのインスタンス
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
            String url = action.execute(request, response);
            /* 返却されたurl(JSP)にforwardして処理をディスパッチする */
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
}