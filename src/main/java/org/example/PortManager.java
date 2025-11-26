package org.example;

import com.chenyo.tool.SerialPortToolkit;
import lombok.Getter;
import lombok.Setter;


public class PortManager {

    @Getter
    @Setter
    PortConfig config = new PortConfig();

    @Getter
    @Setter
    private boolean isOpen = false;

    public PortManager() {}

    public void open(String portName) {
        PortConfig config = this.config;;
        if (config == null) {
            System.out.println("PortConfig does not exist!");
            return;
        }
        SerialPortToolkit toolkit = config.getToolkit();
        toolkit.setSerialPort(portName);
        toolkit.applyParameter();
        if (toolkit.findPort(portName) == null) {
            System.out.println("Port does not exist!");
            return;
        }

        if (!toolkit.onConnect()) {
            System.out.println("Port is already opened!");
            return;
        }

        System.out.println(portName + " is open");
        isOpen = true;

    }

    public void close(String portName) {
        PortConfig config = this.config;
        if (config == null) {
            System.out.println("PortConfig does not exist!");
            return;
        }
        SerialPortToolkit toolkit = config.getToolkit();

        // 先檢查是否曾開啟過
        if (toolkit.findPort(portName) == null) {
            System.out.println("Port does not exist!");
            return;
        }
        if (config.getFutureRead() == null | config.getFutureWrite() == null) {
            System.out.println("Port was not opened before!");
            return;
        }
        config.getFutureWrite().cancel(true);
        config.getFutureRead().cancel(true);
        if (!toolkit.onDisconnect()) {
            System.out.println("Port is already closed!");
            return;
        }
        System.out.println(portName + " is closed");
        isOpen = false;

    }

}
