package view;

import java.util.EventListener;

public interface BookingListener extends EventListener {

    void bookButtonClicked(String button);
}
