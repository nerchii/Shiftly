import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTextArea listOfWorkers;
    private JTable shiftScheduleTable;
    private JScrollPane scrollPane; //scrollbar
    private JButton formPage;


    public ViewPanel() {
        initComponents();
        initLayout();
        activateFrame();
    }

    public void updateData(List<Worker> workers) {
        String workersStr = "";
        for (Worker w : workers) {
            workersStr += w.getName() + "\n";
        }

        listOfWorkers.setText(workersStr);

        for (Worker worker : workers) {
            for (Shift shift : worker.getShifts()) {
                shiftScheduleTable.setValueAt(worker.getName(), shift.getDay(), shift.getTime() + 1);
            }
        }
    }

    private void initComponents() {
        String[] times = {"Day", "8:00 - 9:00", "12:00 - 16:00", "21:00 - 22:00"};
        Object[][] days = {
                {"Monday", "", "", ""},
                {"Tuesday", "", "", ""},
                {"Wednesday", "", "", ""},
                {"Thursday", "", "", ""},
                {"Friday", "", "", ""},
                {"Saturday", "", "", ""}
        };
        DefaultTableModel tableModel = new DefaultTableModel(days, times) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        shiftScheduleTable = new JTable(tableModel);

        listOfWorkers = new JTextArea();
        listOfWorkers.setEditable(false);
        scrollPane = new JScrollPane(listOfWorkers, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        formPage = new JButton("Shifts");
    }

    private void initLayout() {
        setLayout(new BorderLayout(5, 5)); // Adds small gaps between components

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Shifts:"), BorderLayout.WEST);
        headerPanel.add(formPage, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(new JScrollPane(shiftScheduleTable));
        splitPane.setBottomComponent(scrollPane);
        splitPane.setResizeWeight(0.8);
        splitPane.setDividerLocation(0.8);

        add(splitPane, BorderLayout.CENTER);
    }

    private void activateFrame() {

    }
}
