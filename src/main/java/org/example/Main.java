package org.example;

import org.example.gui.GUI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static PortManager manager = new PortManager();

    public static void main(String[] args) {

        GUI gui = new GUI();
        gui.openGUI();

        while (true) {

            if (manager.config == null) {
                continue;
            }

            Read read = new Read();
            read.readBuffer(manager.config);

        }
    }
}
