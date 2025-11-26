package org.example;

import com.chenyo.tool.SerialPortToolkit;
import com.fazecast.jSerialComm.SerialPort;
import org.example.gui.Settings;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static PortManager manager = new PortManager();

    public static void main(String[] args) {

        Settings settings = new Settings();
        settings.open();

        while (true) {

            if (manager.config == null) {
                continue;
            }
            Read read = new Read();
            read.readBuffer(manager.config);
        }
    }
}
