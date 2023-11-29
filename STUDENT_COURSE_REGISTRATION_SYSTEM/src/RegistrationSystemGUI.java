import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;



public class RegistrationSystemGUI extends JFrame {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final String name;
    private final String stud_id;
    
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTable table;
    private JTable table_1;

    public RegistrationSystemGUI(String name , String stud_id) {
    	this.name = name ;
    	this.stud_id = stud_id ;
    	setTitle("Student : " + name );
    
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        
        setSize(809, 622);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        tabbedPane.setBounds(0, 0, 793, 583);
        getContentPane().add(tabbedPane);
        
        JPanel studentspanel = new JPanel();
        studentspanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        studentspanel.setForeground(new Color(0, 0, 0));
        tabbedPane.addTab("Student Informations", null, studentspanel, null);
        studentspanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Welcome : " + name);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        lblNewLabel.setForeground(new Color(64, 128, 128));
        lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel.setBounds(295, 86, 236, 47);
        studentspanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Last Name :");
        lblNewLabel_1.setForeground(new Color(64, 128, 128));
        lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 14));
        lblNewLabel_1.setBounds(240, 267, 96, 14);
        studentspanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("First Name:");
        lblNewLabel_1_1.setForeground(new Color(64, 128, 128));
        lblNewLabel_1_1.setFont(new Font("Poppins", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(240, 317, 96, 14);
        studentspanel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Date of birth :");
        lblNewLabel_1_1_1.setForeground(new Color(64, 128, 128));
        lblNewLabel_1_1_1.setFont(new Font("Poppins", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(240, 369, 134, 14);
        studentspanel.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Email :");
        lblNewLabel_1_1_2.setForeground(new Color(64, 128, 128));
        lblNewLabel_1_1_2.setFont(new Font("Poppins", Font.BOLD, 14));
        lblNewLabel_1_1_2.setBounds(240, 418, 96, 14);
        studentspanel.add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_1_1_4 = new JLabel("Your ID :");
        lblNewLabel_1_1_4.setForeground(new Color(64, 128, 128));
        lblNewLabel_1_1_4.setFont(new Font("Poppins", Font.BOLD, 16));
        lblNewLabel_1_1_4.setBounds(340, 198, 79, 14);
        studentspanel.add(lblNewLabel_1_1_4);
        
        textField = new JTextField();
        textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Poppins", Font.BOLD, 12));
        textField.setBounds(416, 196, 86, 20);
        studentspanel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
        textField_1.setEditable(false);
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setFont(new Font("Poppins", Font.BOLD, 13));
        textField_1.setForeground(new Color(0, 0, 0));
        textField_1.setColumns(10);
        textField_1.setBounds(380, 264, 195, 28);
        studentspanel.add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
        textField_2.setEditable(false);
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2.setFont(new Font("Poppins", Font.BOLD, 13));
        textField_2.setForeground(new Color(0, 0, 0));
        textField_2.setColumns(10);
        textField_2.setBounds(380, 314, 195, 28);
        studentspanel.add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
        textField_3.setEditable(false);
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setFont(new Font("Poppins", Font.BOLD, 13));
        textField_3.setForeground(new Color(0, 0, 0));
        textField_3.setColumns(10);
        textField_3.setBounds(380, 366, 195, 28);
        studentspanel.add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), null));
        textField_4.setEditable(false);
        textField_4.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4.setFont(new Font("Poppins", Font.BOLD, 13));
        textField_4.setForeground(new Color(0, 0, 0));
        textField_4.setColumns(10);
        textField_4.setBounds(380, 415, 195, 28);
        studentspanel.add(textField_4);

        remplirInfoStudent();
        
        JPanel registrepanel = new JPanel();
        registrepanel.setFont(new Font("Poppins", Font.BOLD, 14));
        registrepanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        tabbedPane.addTab("View Registrations", null, registrepanel, null);
        registrepanel.setLayout(null);
        
        JLabel lblListeOfCourses = new JLabel("Courses Enrolled by '" + name + "' :");
        lblListeOfCourses.setBounds(208, 71, 363, 46);
        lblListeOfCourses.setHorizontalAlignment(SwingConstants.CENTER);
        lblListeOfCourses.setForeground(new Color(64, 128, 128));
        lblListeOfCourses.setFont(new Font("Poppins", Font.BOLD, 20));
        lblListeOfCourses.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        registrepanel.add(lblListeOfCourses);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setEnabled(false);
        scrollPane.setForeground(new Color(0, 0, 0));
        scrollPane.setFont(new Font("Poppins", Font.BOLD, 13));
        scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        scrollPane.setBounds(170, 145, 442, 318);
        registrepanel.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JButton DeletethisCourse = new JButton("Delete this Course");
        DeletethisCourse.setFont(new Font("Poppins", Font.BOLD, 13));
        DeletethisCourse.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), null));
        DeletethisCourse.setBackground(new Color(64, 128, 128));
        DeletethisCourse.setBounds(301, 487, 167, 38);
        registrepanel.add(DeletethisCourse);
        
        DeletethisCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerEnregistrement();
            }
        });
        
        afficherCourseStudentDansTable();

        
        JPanel coursespanel = new JPanel();
        coursespanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        tabbedPane.addTab("Availabale Courses", null, coursespanel, null);
        coursespanel.setLayout(null);
        
        JLabel lblAvailabaleCourses = new JLabel("Availabale Courses :");
        lblAvailabaleCourses.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvailabaleCourses.setForeground(new Color(64, 128, 128));
        lblAvailabaleCourses.setFont(new Font("Poppins", Font.BOLD, 20));
        lblAvailabaleCourses.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        lblAvailabaleCourses.setBounds(264, 74, 231, 46);
        coursespanel.add(lblAvailabaleCourses);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), new Color(64, 128, 128)));
        scrollPane_1.setBounds(172, 151, 433, 317);
        coursespanel.add(scrollPane_1);
        
        table_1 = new JTable();
        scrollPane_1.setViewportView(table_1);
        
        JButton btnAddThiscourse = new JButton("Add this Course");
        btnAddThiscourse.setFont(new Font("Poppins", Font.BOLD, 13));
        btnAddThiscourse.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 128, 128), null));
        btnAddThiscourse.setBackground(new Color(64, 128, 128));
        btnAddThiscourse.setBounds(311, 490, 149, 38);
        coursespanel.add(btnAddThiscourse);
        setVisible(true);
        
        btnAddThiscourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	enregistrerEtudiantAuCours();
            }
        });
        afficherCourseStudentAvailabale();

        
        seConnecterBD();
      
    }
    
    
    public void enregistrerEtudiantAuCours() {
        try {
            int selectedRow = table_1.getSelectedRow();
            
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a course", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String course_id = (String) table_1.getValueAt(selectedRow, 0);
            
            String student_id = stud_id;
            
            statement.executeUpdate("INSERT INTO registrations (student_id, course_id, registration_date) VALUES ('" + student_id + "', '" + course_id + "', CURRENT_DATE)");
            
            JOptionPane.showMessageDialog(null, "Enrollment successful for the student in the course.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();
            model.setRowCount(0);
            DefaultTableModel model2 = (DefaultTableModel) table.getModel();
            model2.setRowCount(0);
            
            afficherCourseStudentAvailabale();
            afficherCourseStudentDansTable();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


 public void supprimerEnregistrement() {
     try {
         int selectedRow = table.getSelectedRow();
         
         if (selectedRow == -1) {
             JOptionPane.showMessageDialog(null, "Please select a course", "Error", JOptionPane.ERROR_MESSAGE);
             return;
         }
         
         String course_id = (String) table.getValueAt(selectedRow, 0);
         
         String student_id = stud_id;
         
         statement.executeUpdate("DELETE FROM registrations WHERE student_id = '" + student_id + "' AND course_id = '" + course_id + "'");
         
         JOptionPane.showMessageDialog(null, "Deletion successful for the student in the course.", "Success", JOptionPane.INFORMATION_MESSAGE);
         
         DefaultTableModel model = (DefaultTableModel) table_1.getModel();
         model.setRowCount(0);
         DefaultTableModel model2 = (DefaultTableModel) table.getModel();
         model2.setRowCount(0);
         
         afficherCourseStudentAvailabale();
         afficherCourseStudentDansTable();
    
         
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

    

    
    
    
    
    public void afficherCourseStudentDansTable() {
        try {
            seConnecterBD();
            resultSet = statement.executeQuery("SELECT courses.course_id, courses.course_name, courses.instructor\r\n"
            		+ "FROM courses\r\n"
            		+ "INNER JOIN registrations ON courses.course_id = registrations.course_id\r\n"
            		+ "INNER JOIN students ON registrations.student_id = students.student_id\r\n"
            		+ "WHERE students.student_id = '" + stud_id + "' ;\r\n"
            		+ "" );
            
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            
            int numberOfColumns = metaData.getColumnCount(); 
            DefaultTableModel model = (DefaultTableModel)table.getModel();

            
            String[] columnNames = new String[numberOfColumns];
            
            for (int i = 0; i < numberOfColumns; i++) {
                columnNames[i] = metaData.getColumnName(i+1);
            }
            model.setColumnIdentifiers(columnNames);
            
            String course_id,course_name,instructor_name;
            
            while(resultSet.next()) {
            	course_id = resultSet.getString(1);
            	course_name = resultSet.getString(2);
            	instructor_name = resultSet.getString(3);
            	String[] row = {course_id ,course_name,instructor_name };
            	model.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void afficherCourseStudentAvailabale() {
        try {
            seConnecterBD();
            resultSet = statement.executeQuery("SELECT courses.course_id, courses.course_name, courses.instructor\r\n"
            		+ "FROM courses\r\n"
            		+ "WHERE NOT EXISTS (\r\n"
            		+ "    SELECT 1\r\n"
            		+ "    FROM registrations\r\n"
            		+ "    WHERE registrations.student_id = '" + stud_id + "' \r\n"
            		+ "    AND registrations.course_id = courses.course_id\r\n"
            		+ ");\r\n"
            		+ "");
            
            ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            
            int numberOfColumns = metaData.getColumnCount(); 
            DefaultTableModel model = (DefaultTableModel)table_1.getModel();

            
            String[] columnNames = new String[numberOfColumns];
            
            for (int i = 0; i < numberOfColumns; i++) {
                columnNames[i] = metaData.getColumnName(i+1);
            }
            model.setColumnIdentifiers(columnNames);
            
            String course_id,course_name,instructor_name;
            
            while(resultSet.next()) {
            	course_id = resultSet.getString(1);
            	course_name = resultSet.getString(2);
            	instructor_name = resultSet.getString(3);
            	String[] row = {course_id ,course_name,instructor_name };
            	model.addRow(row);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void remplirInfoStudent() {
        try {
            seConnecterBD();
            resultSet = statement.executeQuery("SELECT student_id,last_name,first_name,email,date_of_birth FROM students WHERE last_name = '" + name + "'");
            if (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                String dateOfBirth = resultSet.getString("date_of_birth");
                                
                textField.setText(String.valueOf(studentId));
                textField_1.setText(lastName);
                textField_2.setText(firstName);
                textField_3.setText(dateOfBirth);
                textField_4.setText(email);
     
            }
   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean seConnecterBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "");
            statement = connection.createStatement();
            return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void fermerConnexionBD() {
        try {
        	resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fermerConnexionServeur() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
