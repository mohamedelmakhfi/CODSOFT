import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class LoginWindow extends JFrame implements ActionListener {

    private JLabel labelUsername, labelPassword;
    private JTextField textFieldUsername , txtLogin;
    private JPasswordField passwordField;
    private JButton buttonLogin , buttonCancel;
    private JPanel panel;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public LoginWindow() {
    	setTitle("Student Authentication");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        txtLogin = new JTextField();
        txtLogin.setBorder(null);
        labelUsername = new JLabel("Nom d'utilisateur");
        labelUsername.setForeground(new Color(64, 128, 128));
        labelPassword = new JLabel("Mot de passe");
        labelPassword.setForeground(new Color(64, 128, 128));
        textFieldUsername = new JTextField(20);
        textFieldUsername.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField = new JPasswordField(20);
        passwordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLogin = new JButton("Se connecter");
        buttonLogin.setBackground(new Color(64, 128, 128));
        buttonLogin.setBorder(new LineBorder(new Color(64, 128, 128)));
        buttonLogin.setBounds(161, 358, 131, 31);
        buttonCancel = new JButton("Cancel");
        buttonCancel.setBackground(new Color(255, 0, 128));
        buttonCancel.setBorder(new LineBorder(new Color(64, 128, 128)));
        buttonCancel.setBounds(302, 358, 130, 31);
        txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
        txtLogin.setText("Student Authentication");
        txtLogin.setEditable(false);
        txtLogin.setForeground(new Color(0, 0, 0));
        txtLogin.setColumns(10);
     
        panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        panel.setFont(new Font("Poppins", Font.PLAIN, 11));
        panel.setForeground(new Color(255, 0, 128));
        Font font = new Font("Poppins", Font.PLAIN, 14); 

        labelUsername.setFont(new Font("Poppins", Font.BOLD, 16));
        labelPassword.setFont(new Font("Poppins", Font.BOLD, 16));
        txtLogin.setFont(new Font("Poppins", Font.BOLD, 25));
        textFieldUsername.setFont(font);
        passwordField.setFont(font);
        buttonLogin.setFont(font);
        buttonCancel.setFont(font);

       
        panel.setLayout(null);

        txtLogin.setBounds(126, 96, 332, 31);
        labelUsername.setBounds(217, 161, 147, 25); 
        textFieldUsername.setBounds(197, 199, 190, 37);
        labelPassword.setBounds(234, 257, 118, 25);
        passwordField.setBounds(197, 293, 190, 37);
       

        panel.add(txtLogin);
        panel.add(labelUsername);
        panel.add(textFieldUsername);
        panel.add(labelPassword);
        panel.add(passwordField);
        panel.add(buttonLogin);
        panel.add(buttonCancel);


        getContentPane().add(panel);
            
        buttonLogin.addActionListener(this);
        buttonCancel.addActionListener(this);

        
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);



        connection = null;
        statement = null;
        resultSet = null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            String username = textFieldUsername.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir votre nom d'utilisateur et votre mot de passe.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "");

                    statement = connection.prepareStatement("SELECT last_name,first_name,student_id  FROM students WHERE last_name = ? AND password = ?");

                    statement.setString(1, username);
                    statement.setString(2, password);

                    resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                    	String name = resultSet.getString("last_name");
                        String student_id = resultSet.getString("student_id");
                        
                     
                        JOptionPane.showMessageDialog(this, "Vous êtes connecté.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        new RegistrationSystemGUI(name , student_id);

                    } else {
                        JOptionPane.showMessageDialog(this, "Le nom d'utilisateur ou le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    connection.close();

                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Le pilote JDBC n'est pas trouvé." , "Erreur" , JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Impossible de cennecter a la base de donnees" , "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == buttonCancel) {
            dispose();
        }
    }



    public static void main(String[] args) {
        new LoginWindow();
    }
}
