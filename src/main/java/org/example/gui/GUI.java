package org.example.gui;

import com.fazecast.jSerialComm.SerialPort;
import org.example.LogOutput;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class GUI implements LogOutput {


    private JPanel panel1;
    private JComboBox<String> comboBox1;
    private JButton 開啟關閉Button;
    private JButton port設定Button;
    private JButton 自動回傳Button;
    private JTextArea log;
    private JTextArea textArea1;
    private JButton 傳送檔案Button;
    private JButton 重置計數Button;
    String portName;


    public JPanel getPanel() {
        return panel1;
    }

    public void openGUI(){
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(this.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.pack();
        frame.setVisible(true);
        loadPorts();
        portName = (String) comboBox1.getSelectedItem();





    }




    @Override
    public void log(String msg) {
        SwingUtilities.invokeLater(() -> {
            log.append(msg + "\n");
        });
    }

    public void loadPorts() {
        comboBox1.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                comboBox1.removeAllItems();
                for (SerialPort p : SerialPort.getCommPorts()) {
                    comboBox1.addItem(p.getSystemPortName());
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }
}
