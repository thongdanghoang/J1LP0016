package test;

import model.dto.AccountDTO;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Optional<AccountDTO> auth = new Login().auth();
            auth.ifPresent(e -> {
                if (e.getRole().equals("BOSS")) {
                    new AccountManager().handle(e);
                } else if (e.getRole().equals("ACC-1")) {
                    new DealerManager().handle(e);
                }
            });
            System.out.print("Do you want to continue? (y/others): ");
            String isContinue = Input.getString();
            if (!isContinue.equalsIgnoreCase("y")) {
                break;
            }
        }
        Input.close();
    }
}
