package org.example.gui;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static org.example.Main.manager;

public class SettingFrame {
    private JComboBox portName;
    private JComboBox baudRate;
    private JRadioButton 十六進位RadioButton1;
    private JRadioButton 字元形式RadioButton;
    private JCheckBox allowAutoReply;
    private JTextField textField1;
    private JCheckBox 允許自定義CheckBox;
    private JTextPane 速率TextPane;
    private JPanel panel1;
    private JRadioButton 十六進位RadioButton;
    private JRadioButton 字元形式RadioButton1;
    private JButton save;
    private JComboBox parity;
    private JComboBox dataBits;
    private JComboBox stopBits;
    private JComboBox bufferAllowCate;
    JFrame frame;
    String port;
    int baud;
    String par;
    int db;
    float sb;
    int allowCate;

    public SettingFrame() {
        frame = new JFrame("參數設定");
        frame.setContentPane(panel1);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // ← 重要：不要清掉 UI
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = Objects.requireNonNull(portName.getSelectedItem()).toString();
                System.out.println(port);
                baud = Integer.parseInt((String) Objects.requireNonNull(baudRate.getSelectedItem()));
                System.out.println(baud);
                par = Objects.requireNonNull(parity.getSelectedItem()).toString();
                System.out.println(par);
                db = Integer.parseInt(String.valueOf(dataBits.getSelectedItem()));
                System.out.println(db);
                sb = Integer.parseInt((String) Objects.requireNonNull(stopBits.getSelectedItem()));
                System.out.println(sb);
                allowCate  = Integer.parseInt((String) Objects.requireNonNull(bufferAllowCate.getSelectedItem()));
                System.out.println(allowCate);
            }
        });
    }

    public void setVisible(boolean b){
        System.out.println("inv");
        this.frame.setVisible(b);
    }

    public void openPort(){
        manager.open(this.port);
        manager.setOpen(true);

    }

    public void closePort(){
        manager.close(this.port);
        manager.setOpen(false);
    }

}
