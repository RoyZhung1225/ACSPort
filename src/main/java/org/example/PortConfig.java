package org.example;

import com.chenyo.tool.SerialPortToolkit;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Future;


public class PortConfig {

    @Getter
    @Setter
    private SerialPortToolkit toolkit = new SerialPortToolkit(); // 每個 PortConfig 都對應一個序列埠操作工具

    @Getter
    @Setter
    private int writeTimeout = this.toolkit.getWriteTimeout(); // 寫入逾時時間，單位毫秒

    @Getter
    @Setter
    private int readTimeout = this.toolkit.getReadTimeout();  // 讀取逾時時間，單位毫秒

    @Getter
    @Setter
    private String portName; // 序列埠名稱

    @Getter
    @Setter
    private int baudRate; // 預設鮑率

    @Getter
    @Setter
    private int dataBits;   // 預設資料位元

    @Getter
    @Setter
    private float stopBits;   // 預設停止位元

    @Getter
    @Setter
    private int parity;     // 預設無奇偶校驗

    @Getter
    private final int flowControl = 0; // 預設無流量控制

    @Getter
    @Setter
    private boolean isAutoReply = false; // 預設不自動回覆

    @Getter
    @Setter
    private boolean baudRateSettable = true; // 預設鮑率可設定

    @Getter
    @Setter
    private int autoReplyDelay; // 自動回覆延遲時間，單位毫秒

    @Getter
    @Setter
    private int bufferAllowCate; // 緩衝區允許容量，單位位元組

    @Getter
    @Setter
    private long futureStart;               // 記錄每次 read 開始的時間點，用來計算耗時當作 timeout

    @Getter
    @Setter
    private long futureEnd;                 // 每次 read 結束的時間點

    @Getter
    @Setter
    private LogOutput logger;       // UI 或 Console 用的輸出介面（可記錄與顯示文字）

    @Getter
    @Setter
    private Future<Integer> futureRead;     // 非阻塞讀取任務（使用 ThreadPool Future 回傳可讀長度）

    @Getter
    @Setter
    private Future<Integer> futureWrite;    // 非阻塞寫入任務（保留日後擴充寫入使用）

    public PortConfig(){
        this.baudRate = 9600;
        this.dataBits = 8;
        this.stopBits = 1;
        this.autoReplyDelay = 1000;
        this.bufferAllowCate = 1024;
        this.parity = 0;
    }

}
