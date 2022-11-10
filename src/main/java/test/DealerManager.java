package test;

import model.dto.AccountDTO;
import model.service.AccountService;
import model.service.DealerService;

public class DealerManager {
    public void handle(AccountDTO accountDTO) {
        DealerService service = new DealerService();
        System.out.println("DEALER MANAGER");
        help();
        while(true){
            System.out.print(accountDTO.getUsername().concat("@J1.L.P0016:~$ "));
            String cmd = Input.getString();
            if (cmd.toLowerCase().equals("create")){
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
        System.out.println("create - insert new dealer to system.");
        System.out.println("read - print all dealers' information.");
        System.out.println("update - edit an dealer through that id.");
        System.out.println("delete - destroy an dealer base on id.");
        System.out.println("exit - log out dealer manager.");
    }
}
