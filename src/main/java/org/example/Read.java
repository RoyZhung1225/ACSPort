package org.example;

import com.chenyo.tool.SerialPortToolkit;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Read {

    public StringBuilder readBuffer = new StringBuilder();


    public void readBuffer(PortConfig config){
        Future<Integer> futureRead = config.getFutureRead();
        SerialPortToolkit kit = config.getToolkit();
        ByteBuffer buffer = ByteBuffer.allocate(config.getBufferAllowCate());
        if(futureRead == null){
            kit.onClearReadBuffer();
            config.setFutureStart(System.currentTimeMillis());
            config.setFutureRead(kit.read(buffer));
            return;
        }
        if(futureRead.isDone()){
            try {
                config.setFutureEnd(System.currentTimeMillis());
                long readDuration = config.getFutureEnd() - config.getFutureStart();
                int available = futureRead.get();
                if(available<=0){
                    kit.onClearReadBuffer();
                    config.setFutureStart(System.currentTimeMillis());
                    config.setFutureRead(kit.read(buffer));
                    return;
                }
                kit.setReadTimeout((int) readDuration);
                this.readData(available, buffer);
                kit.onClearReadBuffer();
                buffer = ByteBuffer.allocate(config.getBufferAllowCate());
                config.setFutureStart(System.currentTimeMillis());
                config.setFutureRead(kit.read(buffer));

            } catch (ExecutionException e) {
                System.err.println("EXE");
            } catch (InterruptedException e) {
                System.err.println("INT");
            } catch (IOException e) {
                System.err.println("IO");
            }
        }

    }







    public void readData(int available, ByteBuffer buffer) throws ExecutionException, InterruptedException, IOException {

        if(available <= 0)
            return;
        byte[] data = new byte[available];

        buffer.get(data);

        for (byte datum : data) {

            char c = (char) datum;

            readBuffer.append(c);

            if (c == '\n') {
                String message = readBuffer.toString().trim(); // 去掉 \n

                System.out.println(message);

                // 清空緩衝器（準備下個訊息）
                readBuffer.setLength(0);
            }

        }


    }

}
