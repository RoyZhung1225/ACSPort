package org.example.gui;

import javax.swing.*;

public class SettingFrame {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JRadioButton 十六進位RadioButton1;
    private JRadioButton 字元形式RadioButton;
    private JCheckBox 允許自動發送CheckBox;
    private JTextField textField1;
    private JCheckBox 允許自定義CheckBox;
    private JTextPane 速率TextPane;
    private JPanel panel1;
    private JRadioButton 十六進位RadioButton;
    private JRadioButton 字元形式RadioButton1;
    JFrame frame;

    public SettingFrame() {
        frame = new JFrame("參數設定");
        frame.setContentPane(panel1);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // ← 重要：不要清掉 UI
    }

    public void setVisible(boolean b){
        this.frame.setVisible(b);
    }

}
