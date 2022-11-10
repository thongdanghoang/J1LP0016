package api;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.service.AccountService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccountAPI", value = "/AccountAPI")
public class AccountAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            out.print(new Gson().toJson(new AccountService().findAll()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
