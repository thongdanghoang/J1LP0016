package test;

import model.dto.AccountDTO;
import model.service.AccountService;

public class AccountManager {

    public void handle(AccountDTO accountDTO) {
        AccountService service = new AccountService();
        System.out.println("ACCOUNT MANAGER");
        help();
        while(true){
            System.out.print(accountDTO.getUsername().concat("@J1.L.P0016:~$ "));
            String cmd = Input.getString();
            if (cmd.toLowerCase().equals("create")){
                System.out.print("Input username: ");
                String username = Input.getString();
                System.out.print("Input password: ");
                String password = Input.getString();
                System.out.print("Input role: ");
                String role = Input.getString();
                try {
                    service.create(new AccountDTO(username,password,role));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            if (cmd.toLowerCase().equals("read")){
                System.out.println("loading...");
                try {
                    service.findAll().forEach(System.out::println);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            if (cmd.toLowerCase().equals("update")){
            }
            if (cmd.toLowerCase().equals("delete")){
            }
            if (cmd.toLowerCase().equals("exit")){
                break;
            }
        }
    }

    private void help() {
        System.out.println("COMMAND LIST: ");
        System.out.println("create - insert new account to system.");
        System.out.println("read - print all accounts' information.");
        System.out.println("update - edit an account through that id.");
        System.out.println("delete - destroy an account base on id.");
        System.out.println("exit - log out account manager.");
    }
}
