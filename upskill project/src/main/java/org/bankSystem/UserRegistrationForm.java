package org.bankSystem;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class UserRegistrationForm extends JFrame implements ActionListener {
    private JLabel nameLabel, addressLabel, contactLabel, depositLabel, genderLabel, maritalStatusLabel, formNoLabel,image;
    private JTextField nameField, addressField, contactField, depositField;
    private JRadioButton maleButton, femaleButton, marriedButton, singleButton;
    private ButtonGroup genderGroup, maritalStatusGroup;
    private JButton registerButton;

    public UserRegistrationForm() {
        // Set window properties
        setTitle("User Registration Form");
        setSize(850,800);
        setLocation(500,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Set icon image
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("upskill project/src/icons/logo.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(10,10, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//


        // Initialize components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        contactLabel = new JLabel("Contact Information:");
        contactField = new JTextField(20);
        depositLabel = new JLabel("Initial Deposit Amount:");
        depositField = new JTextField(20);
        genderLabel = new JLabel("Gender:");
        maleButton = new JRadioButton("Male");
        maleButton.setActionCommand("Male");
        femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        maritalStatusLabel = new JLabel("Marital Status:");
        marriedButton = new JRadioButton("Married");
        marriedButton.setActionCommand("Married");
        singleButton = new JRadioButton("Single");
        singleButton.setActionCommand("Single");
        maritalStatusGroup = new ButtonGroup();
        maritalStatusGroup.add(marriedButton);
        maritalStatusGroup.add(singleButton);
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        // Create gender panel
        JPanel genderPanel = new JPanel(new FlowLayout());
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        // Create marital status panel
        JPanel maritalStatusPanel = new JPanel(new FlowLayout());
        maritalStatusPanel.add(marriedButton);
        maritalStatusPanel.add(singleButton);

        // Create form number label
        formNoLabel = new JLabel("APPLICATION FORM NO:");
        formNoLabel.setFont(new Font("Raleway", Font.BOLD, 40));
        formNoLabel.setBounds(150, 30, 800, 40);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the form
//        add(image, gbc);
//        gbc.gridx++;
        add(formNoLabel, gbc);
        gbc.gridx++;
        add(new JLabel(), gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridx++;
        add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(addressLabel, gbc);
        gbc.gridx++;
        add(addressField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(contactLabel, gbc);
        gbc.gridx++;
        add(contactField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(depositLabel, gbc);
        gbc.gridx++;
        add(depositField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(genderLabel, gbc);
        gbc.gridx++;
        add(genderPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(maritalStatusLabel, gbc);
        gbc.gridx++;
        add(maritalStatusPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // Show the form
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                // Get input values
                String name = nameField.getText();
                String address = addressField.getText();
                String contactInfo = contactField.getText();
                double initialDeposit = Double.parseDouble(depositField.getText());
                String gender = genderGroup.getSelection().getActionCommand();
                String maritalStatus = maritalStatusGroup.getSelection().getActionCommand();

                // Generate unique account number
                String accountNumber = generateAccountNumber();

                // Create directory for data files
                File dataDir = new File("data");
                dataDir.mkdirs();

                // Create user data file
                File userFile = new File(dataDir, accountNumber + ".txt");
                if (!userFile.exists()) userFile.createNewFile();

                // Write user details to data file
                FileWriter fw = new FileWriter(userFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Name: " + name);
                bw.newLine();
                bw.write("Address: " + address);
                bw.newLine();
                bw.write("Contact Information: " + contactInfo);
                bw.newLine();
                bw.write("Initial Deposit Amount: " + initialDeposit);
                bw.newLine();
                bw.write("Gender: " + gender);
                bw.newLine();
                bw.write("Marital Status: " + maritalStatus);
                bw.newLine();
                bw.write("Account Number: " + accountNumber);
                bw.close();

                // Store user details in memory or File System
                User user = new User(name, address, contactInfo, initialDeposit, accountNumber, gender, maritalStatus);

                // Show confirmation message
                JOptionPane.showMessageDialog(this, "User registration successful. Your account number is " + accountNumber);

                // Clear input fields
                nameField.setText("");
                addressField.setText("");
                contactField.setText("");
                depositField.setText("");
                genderGroup.clearSelection();
                maritalStatusGroup.clearSelection();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number for initial deposit.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public String generateAccountNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000;
        String accountNumber = String.format("%06d", number);
        formNoLabel.setText("APPLICATION FORM NO:" + accountNumber);
        return accountNumber;
    }

    public static void main(String[] args) {
        new UserRegistrationForm();
    }
}