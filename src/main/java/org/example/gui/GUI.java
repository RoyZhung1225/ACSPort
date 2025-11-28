package org.example.gui;

import com.fazecast.jSerialComm.SerialPort;
import org.example.LogOutput;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class GUI implements LogOutput {


    private JPanel panel1;
    private JButton set;
    private JButton switchBut;
    private JButton fileDeli;
    private JButton colsoleButton;
    private JButton hexButton;
    private JButton charButton;
    private JButton autoReply;
    private JButton clearButton;
    private JButton addCRLFButton;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JRadioButton 十六進位RadioButton;
    private JRadioButton 字元RadioButton;
    private JCheckBox 自動發送CheckBox;
    private JButton 清除數據Button;
    private JButton 傳送資料Button;
    private JTable table1;
    private JComboBox<String> comboBox1;
    private JTextArea log;
    String portName;
    JFrame frame;

    private SettingFrame settingFrame;

    private SettingFrame getSettingFrame(){
        if(settingFrame == null){
            settingFrame = new SettingFrame();
        }
        return settingFrame;
    }



    public GUI() {
        set.addActionListener(e -> getSettingFrame().setVisible(true));
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void openGUI(){
        JFrame frame = new JFrame("SerialPort Manager");
        frame.setContentPane(getPanel());   // 使用 Designer 的主 Panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);  // 視窗置中
        frame.setVisible(true);
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
