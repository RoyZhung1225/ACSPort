package org.example;

import static org.example.Main.manager;

public class OpenPort {

    LogOutput logger;


    public OpenPort(LogOutput logger){
        this.logger = logger;
    }

    public void log(String msg){
        if (logger != null) {
            logger.log(msg);
        }
    }

    public void open(String portName) {
        manager.open(portName);
    }

}
