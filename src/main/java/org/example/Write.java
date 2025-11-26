package org.example;

import com.chenyo.tool.SerialPortToolkit;

import java.nio.ByteBuffer;

import static org.example.Main.manager;

public class Write {

    public void write(byte[] writeData){

        PortConfig config = manager.config;

        if (config == null){
            System.out.println("config not found");
            return;
        }

        ByteBuffer buffer = ByteBuffer.wrap(writeData);

        SerialPortToolkit toolkit = config.getToolkit();

        if((config.getFutureWrite() == null)){
            config.setFutureWrite(toolkit.write(buffer));
            return;
        }

        if(!config.getFutureWrite().isDone()){
            System.out.println("writeBusy");
            return;
        }

        if(toolkit.isWriting()){
            System.out.println("請放緩你的寫入速度");
            return;
        }

        config.setFutureWrite(toolkit.write(buffer));

    }
}
