import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ViewPanel extends JPanel {
    private JTextArea listOfWorkers;
    private JTable shiftScheduleTable;
    private JScrollPane scrollPane;
    private List<Worker> workers;

    public ViewPanel() {
        initComponents();
        initLayout();
        activateFrame();
        workers = new ArrayList<>();
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
        redrawUI();
    }

    private void redrawUI() {
        String workersStr = "";
        for (Worker w : workers) {
            if (w.getShifts().size()>=1){
                workersStr += "Name: " + w.getName() + "\n"
                        + "Reminders: " + w.getReminders() + "\n"
                        + "Task: " + w.getTask() + "\n"
                        + "Shifts:\n";
                for (Shift shift : w.getShifts()) {
                    workersStr += "  - " + shift.toString() + "\n";
                }
                workersStr += "\n";
            }

        }

        listOfWorkers.setText(workersStr);

        for (int row = 0; row < shiftScheduleTable.getRowCount(); row++) {
            for (int col = 1; col < shiftScheduleTable.getColumnCount(); col++) {
                shiftScheduleTable.setValueAt("", row, col);
            }
        }

        for (Worker worker : workers) {
            for (Shift shift : worker.getShifts()) {
                shiftScheduleTable.setValueAt(worker.getName(), shift.getDay(), shift.getTime() + 1);
            }
        }
    }

    private void initComponents() {
        String[] times = {"Day", "8:00 - 12:30", "12:30 - 17:00", "17:00 - 22:00"};
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
                return false;
            }
        };

        shiftScheduleTable = new JTable(tableModel);

        listOfWorkers = new JTextArea();
        listOfWorkers.setEditable(false);
        scrollPane = new JScrollPane(listOfWorkers, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void initLayout() {
        setLayout(new BorderLayout(5, 5));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(new JLabel("Shifts:"), BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(new JScrollPane(shiftScheduleTable));
        splitPane.setBottomComponent(scrollPane);
        splitPane.setResizeWeight(0.3);
        splitPane.setDividerLocation(0.3);

        add(splitPane, BorderLayout.CENTER);
    }

    private void activateFrame() {
        shiftScheduleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = shiftScheduleTable.getSelectedRow();
                int col = shiftScheduleTable.getSelectedColumn();

                String workerName = (String) shiftScheduleTable.getValueAt(row, col);
                for (Worker worker: workers) {
                    if (workerName.equals(worker.getName())) {
                        Shift shiftToRemove = null;
                        for (Shift shift : worker.getShifts()) {
                            if (shift.getDay() == row && shift.getTime() == (col - 1)) {
                                shiftToRemove = shift;
                                break;
                            }
                        }
                        worker.getShifts().remove(shiftToRemove);
                    }
                }
                redrawUI();
            }
        });
    }
}
