package org.example.gui;

import javax.swing.*;

public class Settings {
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


    public void open() {
        JFrame frame = new JFrame("Settings");
        frame.setContentPane(new Settings().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
