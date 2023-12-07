import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JList;


public class TaskManagerUI{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskManagerUI mgr = new TaskManagerUI();
            JFrame frame = new JFrame("Main Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // Header panel with Log Out button and icons
            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            headerPanel.add(new JButton("Log Out"));
            // Add more buttons or labels for the icons here
            // ...
            frame.add(headerPanel, BorderLayout.NORTH);

            // Main task list panel
            JPanel taskListPanel = new JPanel();
            taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
            mgr.addTaskToPanel(taskListPanel, "SDE Interview", frame);
            // ... Add more tasks

            // Wrap taskListPanel in a scroll pane
            JScrollPane scrollPane = new JScrollPane(taskListPanel);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Footer panel with Add Task button
            JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton addTaskButton = new JButton("Add Task");
            footerPanel.add(addTaskButton);
            frame.add(footerPanel, BorderLayout.SOUTH);

            // Add Task button action
            addTaskButton.addActionListener(e -> {
                AddTaskDialog dialog = new AddTaskDialog(mgr, taskListPanel, frame);
                dialog.setVisible(true);
            });

            frame.setVisible(true);
        });
    }

    public void addTaskToPanel(JPanel panel, String taskName, JFrame frame) {
        JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox checkBox = new JCheckBox();
        JLabel taskLabel = new JLabel(taskName);
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        taskPanel.add(checkBox);
        taskPanel.add(taskLabel);
        taskPanel.add(editButton);
        taskPanel.add(deleteButton);
        // Add more components to taskPanel to match the UI, such as edit and delete buttons
        // ...

        // Edit button action
        editButton.addActionListener(e -> {
            EditTaskDialog dialog = new EditTaskDialog(frame, taskName);
            dialog.setVisible(true);
        });

        deleteButton.addMouseListener(new MouseAdapter() {});

        panel.add(taskPanel);
    }
}

class EditTaskDialog extends JDialog {
    public EditTaskDialog(JFrame parent, String taskName) {
        super(parent, "Edit Task", true);
        setLayout(new BorderLayout());
        setSize(300, 200);

        // Form fields
        JTextField taskField = new JTextField(taskName);
        JButton saveButton = new JButton("Save");

        // Panel for form fields
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Task:"));
        panel.add(taskField);
        add(panel, BorderLayout.CENTER);

        // Save button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Save button action
        saveButton.addActionListener(e -> {
            // addTaskToPanel(taskField.getText(), "5100 Homework", parent);
            dispose();
        });
    }
}
