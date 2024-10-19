import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String[] toArray() {
        return new String[]{String.valueOf(rollNumber), name, grade};
    }
}

class StudentManagementSystem {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void removeStudent(int rollNumber) {
        studentList.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return studentList;
    }
}

public class StudentManagementGUI {
    private JFrame frame;
    private JTextField nameField, rollNumberField, gradeField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentManagementSystem studentSystem = new StudentManagementSystem();

    public StudentManagementGUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(rollNumberLabel);
        panel.add(rollNumberField);
        panel.add(gradeLabel);
        panel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        panel.add(addButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });
        panel.add(displayButton);

        frame.add(panel, BorderLayout.NORTH);

        // Table to display students
        studentTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Roll Number", "Name", "Grade"});
        studentTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementGUI();
            }
        });
    }

    private void addStudent() {
        String name = nameField.getText();
        String rollNumberStr = rollNumberField.getText();
        String grade = gradeField.getText();

        // Input validation
        if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = new Student(name, rollNumber, grade);
            studentSystem.addStudent(student);

            JOptionPane.showMessageDialog(frame, "Student added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Roll Number must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAllStudents() {
        ArrayList<Student> students = studentSystem.getAllStudents();
        tableModel.setRowCount(0); // Clear table

        for (Student student : students) {
            tableModel.addRow(student.toArray());
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }
}


