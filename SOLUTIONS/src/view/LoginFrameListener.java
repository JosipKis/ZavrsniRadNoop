package view;

import java.util.EventListener;

public interface LoginFrameListener extends EventListener {

    void loginPerformed(String username, String password);

    void registerPerformed();
}
