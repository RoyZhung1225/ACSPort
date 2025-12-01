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

        // --- 1. PortConfig 是否存在 ---
        PortConfig config = this.config;
        if (config == null) {
            System.out.println("PortConfig does not exist!");
            return;
        }

        SerialPortToolkit toolkit = config.getToolkit();

        // --- 2. 設定 SerialPort 物件 ---
        toolkit.setSerialPort(portName);
        toolkit.applyParameter();

        // --- 3. Port 是否存在於系統 ---
        if (toolkit.findPort(portName) == null) {
            System.out.println("Port does not exist! (可能拔掉了)");
            isOpen = false;
            return;
        }

        // --- 4. 是否已經開啟 ---
        if (toolkit.findPort(portName).isOpen()) {
            System.out.println("Port is already open!");
            isOpen = true;
            return;
        }

        // --- 5. 嘗試開啟 ---
        boolean opened = toolkit.onConnect();
        if (!opened) {
            System.out.println("Failed to open port! (未必是 already open)");
            isOpen = false;
            return;
        }

        // --- 6. 開啟成功 ---
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

        // 1. Port 是否存在於系統（拔掉時 findPort = null）
        boolean exist = toolkit.findPort(portName) != null;

        // 2. port 是否已開啟
        boolean opened = (toolkit.findPort(portName) != null && toolkit.findPort(portName).isOpen());

        // ----- Case A: 裝置不存在（被拔掉） -----
        if (!exist) {
            System.out.println("Port not found (可能被拔掉)");
            isOpen = false;  // sync 狀態
            return;
        }

        // ----- Case B: port 已經關閉 -----
        if (!opened) {
            System.out.println("Port is already closed!");
            isOpen = false;
            return;
        }

        // ----- Case C: 嘗試關閉 -----
        if (toolkit.onDisconnect()) {
            System.out.println(portName + " is closed");
            isOpen = false;
            return;
        }

        // ----- Case D: closePort() 失敗，但 port 還存在 -----
        System.out.println("Failed to close port! (裝置存在但關閉失敗)");
    }


}
