package view;

import model.DocFilterAge;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JTextField age;

    private JButton registerButton;

    private PlainDocument plainDocument;

    private RegisterFrameListener registerFrameListener;

    public RegisterPanel() {
        super();
        initComps();
        layoutComps();
        activatePanel();
    }

    private void initComps() {
        username = new JTextField();
        username.setPreferredSize(new Dimension(200, 30));
        username.setToolTipText("Unesite korinsičko ime");

        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 30));
        password.setToolTipText("Zbog sigurnosti, ne dijelite lozinku");

        age = new JTextField();
        age.setPreferredSize(new Dimension(35, 30));
        age.setToolTipText("Dob mora biti broj veci od 18, manji od 100");
        age.setHorizontalAlignment(JTextField.CENTER);

        plainDocument = (PlainDocument) age.getDocument();
        plainDocument.setDocumentFilter(new DocFilterAge());

        registerButton = new JButton("Register");
        registerButton.setActionCommand("register");
    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;

        add(new JLabel("Username:"), gbc);
        gbc.gridy++;

        add(username, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(password, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(new JLabel("Age:"), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        add(age, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(15, 0, 0, 0);

        add(registerButton, gbc);
    }

    private void activatePanel() {
        registerButton.addActionListener(this);
    }

    public void setRegisterFrameListener(RegisterFrameListener registerFrameListener) {
        this.registerFrameListener = registerFrameListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (registerFrameListener != null) {
            registerFrameListener.registerButtonClicked(e.getActionCommand());
        }
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public int getAge() {
        return Integer.parseInt(age.getText());
    }
}
