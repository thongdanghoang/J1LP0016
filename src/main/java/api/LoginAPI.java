package api;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.AccountDTO;
import model.service.AccountService;
import model.service.LoginService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author thongdanghoang
 */
@WebServlet(name = "LoginAPI", value = "/LoginAPI")
public class LoginAPI extends HttpServlet {

    private final LoginService service = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            //Login Success
            AccountDTO login = service.login(username, password);
            switch (login.getRole()) {
                case "BOSS":
                     resp.sendRedirect("account.html");
                    break;
                case "ACC-1":
                    resp.sendRedirect("#");
                    break;
                default:
            }

        } catch (Exception e) {
            //print out passwords in the browser and then send it to the server to your server
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            resp.sendRedirect("index.html");
        }
    }
}
