package org.example;

import static org.example.Main.manager;

public class ClosePort {


    public ClosePort(){}

    public void close(String portName) {
        manager.close(portName);
    }
}
