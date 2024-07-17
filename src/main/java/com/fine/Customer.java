package com.fine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends JFrame {
    private ResultSet rs;
    private JTextField id;
    private JTextField fname;
    private JTextField lname;
    private JTextField phone;
    private JButton btnPrevious;
    private JButton btnNext;

    public Customer(ResultSet rs) throws SQLException {
        this.rs = rs;

        JLabel idLabel = new JLabel("ID");
        JLabel nameLabel = new JLabel("First Name");
        JLabel lnameLabel = new JLabel("Last Name");
        JLabel phoneLabel = new JLabel("Phone Number");

        id = new JTextField(10);
        fname = new JTextField(10);
        lname = new JTextField(10);
        phone = new JTextField(10);
        btnPrevious = new JButton("Previous");
        btnNext = new JButton("Next");

        setLayout(new GridLayout(5, 2, 4, 4));
        add(idLabel);
        add(id);
        add(nameLabel);
        add(fname);
        add(lnameLabel);
        add(lname);
        add(phoneLabel);
        add(phone);
        add(btnPrevious);
        add(btnNext);

        setTitle("Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);

        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.previous()) {
                        updateFields();
                    } else {
                        rs.next();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.next()) {
                        updateFields();
                    } else {
                        rs.previous();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        if (rs.next()) {
            updateFields();
        }
    }

    private class Operation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            // Handle the button click if needed
        }
    }

    private void updateFields() throws SQLException {
        id.setText(rs.getString("id"));
        fname.setText(rs.getString("fname"));
        lname.setText(rs.getString("lname"));
        phone.setText(rs.getString("phone"));
    }

    public static void main(String[] args) {
        JDBCconnection JDBCconnection = null;
        try {
            JDBCconnection = new JDBCconnection();
            ResultSet resultSet = JDBCconnection.getResultSet();
            SwingUtilities.invokeLater(() -> {
                try {
                    new Customer(resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            if (JDBCconnection != null) {
                JDBCconnection.close();
            }
        }
    }
}
