import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewPanel extends JPanel {
    private JTextArea listOfWorkers;    
    private JTable shiftScheduleTable;
    private JScrollPane scrollPane; //scrollbar
    private JButton formPage;


    public ViewPanel() {
        initComponents();
        initLayout();
    }

    private void initComponents() {
        String[] times = {"Day", "8:00", "9:00", "12:00", "16:00", "22:00"};
        Object[][] days = {
                {"Monday", "", "", "", "", ""},
                {"Tuesday", "", "", "", "", ""},
                {"Wednesday", "", "", "", "", ""},
                {"Thursday", "", "", "", "", ""},
                {"Friday", "", "", "", "", ""}
        };
        DefaultTableModel tableModel = new DefaultTableModel(days, times){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells except day names editable
                return column != 0;
            }
        };

        shiftScheduleTable = new JTable(tableModel);
        listOfWorkers = new JTextArea();
        listOfWorkers.setEditable(false);
        scrollPane = new JScrollPane(listOfWorkers,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(new JLabel("Shifts:"), gbc);
        gbc.gridy++;

        topPanel.add(shiftScheduleTable, gbc);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

}
