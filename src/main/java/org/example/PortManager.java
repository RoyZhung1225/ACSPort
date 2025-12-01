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

        SerialPortToolkit toolkit = config.getToolkit();
        toolkit.setSerialPort(portName);

        if(toolkit.findPort(portName) == null){
            System.out.println("PortConfig does not exist!");
            isOpen = false;
            return;
        }
        if(config.getToolkit().isConnecting()){
            System.out.println("Port is already open!");
            isOpen = false;
            return;
        }
        boolean opened = toolkit.onConnect();
        if(!opened){
            System.out.println("Failed to open port! (未必是 already open)");
            isOpen = false;
            return;
        }
        System.out.println(portName + "is open");
        isOpen = true;
    }

    public void close(String portName) {

        if(config == null){
            System.out.println("PortConfig does not exist!");
            return;
        }

        SerialPortToolkit toolkit = config.getToolkit();

        boolean exist = toolkit.findPort(portName) != null;

        boolean opened = toolkit.isConnecting();

        if(!exist){
            System.out.println("Port not found (可能被拔掉)");
            isOpen = false;  // sync 狀態
            return;
        }
        if (!opened) {
            System.out.println("Port is already closed!");
            isOpen = false;
            return;
        }
        if (toolkit.onDisconnect()) {
            System.out.println(portName + " is closed");
            isOpen = false;
            config = null;
            return;
        }
        System.out.println("Failed to close port! (裝置存在但關閉失敗)");
    }


}
