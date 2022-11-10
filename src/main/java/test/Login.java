package test;

import model.dto.AccountDTO;
import model.service.LoginService;

import java.util.Optional;
import java.util.Scanner;

public class Login {
    public Optional<AccountDTO> auth() {
        System.out.println("LOGIN REQUEST");
        System.out.print("username: ");
        String username = Input.getString();
        System.out.print("password: ");
        String password = Input.getString();
        System.out.println("loading...");
        //because of testing, I call service directly without API
        try {
            AccountDTO login = new LoginService().login(username, password);
            return Optional.of(login);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }
}
