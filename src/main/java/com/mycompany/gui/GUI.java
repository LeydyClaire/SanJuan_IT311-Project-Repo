/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gui;

/**
 *
 * @author leydyclaire
 */
    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
        

class GUI implements ActionListener {

    JFrame frame;
    JMenuBar mb;
    JMenu m1, m2;
    JMenuItem m11, m22;
    JPanel panel;
    JLabel label;
    JTextField tf;
    JButton send, reset;
    JTextArea ta;

    public GUI() {

        frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        mb = new JMenuBar();
        m1 = new JMenu("FILE");
        m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);

        m11 = new JMenuItem("Open");
        m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        panel = new JPanel();
        label = new JLabel("Enter Text");
        tf = new JTextField(10);
        send = new JButton("Send");
        reset = new JButton("Reset");
        panel.add(label);
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        ta = new JTextArea();

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);

        send.addActionListener(this);
        reset.addActionListener(this);
        m11.addActionListener(this);
        m22.addActionListener(this);

        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == send) {
            ta.append(tf.getText() + "\n");
            tf.setText("");
        } else if (ae.getSource() == reset) {
            tf.setText("");
            ta.setText("");
        } else if (ae.getSource() == m11) {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    String content = new String(Files.readAllBytes(file.toPath()));
                    ta.setText(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == m22) {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(ta.getText());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
    

