package controller.strategy;

import controller.Kontroler;

import javax.swing.*;
import java.util.List;

public class AddNewUserToDBStrategy implements PressedButtonStrategy {

    private Kontroler kontroler;

    public AddNewUserToDBStrategy() {
        kontroler = Kontroler.getInstance();
        kontroler.connectToDatabase();
    }

    @Override
    public void pressedButton(List<String> data) {
        kontroler.registerNewUser(data);
        JOptionPane.showMessageDialog(null, "Uspješno registriran račun!", "Uspjeh!", JOptionPane.INFORMATION_MESSAGE);
    }
}
