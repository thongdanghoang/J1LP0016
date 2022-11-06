package api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.AccountDTO;
import model.service.LoginService;

import java.io.IOException;

/**
 * @author thongdanghoang
 */
@WebServlet(name = "LoginAPI", value = "/LoginAPI")
public class LoginAPI extends HttpServlet {

    private LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            //Success
        } catch (Exception e) {
            //print out passwords in the browser and then send it to the server to your server
            System.err.println(e.getMessage());
        }
    }
}
