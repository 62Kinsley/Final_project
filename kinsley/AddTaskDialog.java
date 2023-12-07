import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTaskDialog extends JDialog {
    JPanel taskListPanel;
    JFrame parentFrame;
    TaskManagerUI mgr;

    public AddTaskDialog(TaskManagerUI mgr, JPanel taskListPanel, JFrame parentFrame) {
        super(parentFrame, "New Task", true);
        this.taskListPanel = taskListPanel;
        this.parentFrame = parentFrame;
        this.mgr = mgr;
        initUI(parentFrame);
    }

    private void initUI(JFrame parentFrame) {
        setSize(450, 200);
        setLayout(new BorderLayout());

        // Header panel with a label and buttons
        JPanel headerPanel = new JPanel(new FlowLayout(3));
        JButton closeButton = new JButton("X");
        headerPanel.add(closeButton);
        JLabel titleLabel = new JLabel("New Task");
        headerPanel.add(titleLabel);
        JButton addButton = new JButton("Add");
        headerPanel.add(addButton);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JTextField titleField = new JTextField("TITLE");
        JTextArea descriptionArea = new JTextArea("DESCRIPTION");
        JComboBox<String> dueDropdown = new JComboBox<>(new String[] {"Due"});
        JComboBox<String> priorityDropdown = new JComboBox<>(new String[] {"Priority"});

        // Add components to the content panel
        contentPanel.add(titleField);
        contentPanel.add(descriptionArea);
        contentPanel.add(dueDropdown);
        contentPanel.add(priorityDropdown);
        add(contentPanel, BorderLayout.CENTER);

        // Add action listeners
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mgr.addTaskToPanel(taskListPanel, titleField.getText(), parentFrame);
                dispose(); // Close the dialog
            }
        });

        // Styling components to match the provided design
        // Note: You would need to use more advanced styling to match the exact design
        // ...

        // Set dialog location relative to parent frame
        setLocationRelativeTo(parentFrame);
    }
}

