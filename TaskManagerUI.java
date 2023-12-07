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
            JButton logoutButton = new JButton("Log Out");
            headerPanel.add(logoutButton);
            // Add more buttons or labels for the icons here
            // ...
            frame.add(headerPanel, BorderLayout.NORTH);

            logoutButton.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // You can add any cleanup logic here before exiting
                    System.exit(0);
                }
            });

            // Main task list panel
            JPanel taskListPanel = new JPanel(new GridLayout(0, 1));
            taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
            mgr.addTaskToPanel(taskListPanel, "Demo", "2024-01-01", "High", frame);
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

    public void addTaskToPanel(JPanel panel, String taskName, String dueDate, String priority, JFrame frame) {
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
            EditTaskDialog dialog = new EditTaskDialog(frame, taskName, dueDate, priority);
            dialog.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this task?", "Delete Task", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Implement your logic to remove the task from the panel or any data structure
                panel.remove(taskPanel);
                panel.revalidate();
                panel.repaint();
                // You might want to add additional logic to update your data model or perform other cleanup
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.ipady = 50;

        panel.add(taskPanel);
        panel.revalidate();
        panel.repaint();
    }
}

class EditTaskDialog extends JDialog {
    public EditTaskDialog(JFrame parent, String taskName, String dueDate, String priority) {
        super(parent, "Edit Task", true);
        setLayout(new BorderLayout());
        setSize(300, 200);

        // Form fields
        JTextField taskField = new JTextField(taskName);
        JTextField dueDateField = new JTextField(dueDate);
        JComboBox<String> priorityDropdown = new JComboBox<>(new String[]{"High", "Medium", "Low"});
        priorityDropdown.setSelectedItem(priority);

        JButton saveButton = new JButton("Save");

        // Panel for form fields
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Task:"));
        panel.add(taskField);
        panel.add(new JLabel("Due Date:"));
        panel.add(dueDateField);
        panel.add(new JLabel("Priority:"));
        panel.add(priorityDropdown);
        add(panel, BorderLayout.CENTER);

        // Save button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Save button action
        saveButton.addActionListener(e -> {
            // Get edited property values
            String editedTaskName = taskField.getText();
            String editedDueDate = dueDateField.getText();
            String editedPriority = (String) priorityDropdown.getSelectedItem();

            // Add logic here to update task properties
            // ...

            dispose(); // Close the dialog
        });
    }
}
