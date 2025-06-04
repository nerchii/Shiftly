import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private ViewPanel viewPanel;
    private FormPanel formPanel;
    private AppMenuBar appMenuBar;
    private JPanel currentScreen;
    private ArrayList<Worker> workers;

    public MainFrame() {
        super("Shiftly");
        workers = AUX_CLS.readFromJSONFile("./DATA/data.json");
        initMainFrame();
        initComponents();
        initLayout();
        activateFrame();
        setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());

    }

    private void initMainFrame() {
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        viewPanel = new ViewPanel();
        formPanel = new FormPanel();
        appMenuBar = new AppMenuBar(this);
        setJMenuBar(appMenuBar);
        currentScreen = viewPanel;
        viewPanel.setWorkers(workers);
    }

    private void initLayout() {
        setLayout(new BorderLayout());
        add(currentScreen, BorderLayout.CENTER);
    }

    private void activateFrame() {
        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formPanelEventOccurred(Worker worker) {
                boolean addedSuccessfully = addWorker(worker);
                if (addedSuccessfully) showViewPanel();
            }
        });
        appMenuBar.setMenuBarListener(new MenuBarListener() {
            @Override
            public void menuBarEventOccurred(String actionCommand) {
                if (actionCommand.equals("exit")) {
                    System.exit(0);
                } else if (actionCommand.equals("viewPanel")) {
                    showViewPanel();
                } else if (actionCommand.equals("formPanel")) {
                    showFormPanel();
                } else if (actionCommand.equals("saveToJsonFile")) {
                    AUX_CLS.writeToJSONFile(workers,"./DATA/data.json" );

                } else if (actionCommand.equals("readFromJsonFile")) {
                    workers = AUX_CLS.readFromJSONFile("./DATA/data.json" );
                    showViewPanel();

//                }else if (actionCommand.equals("saveToBinFile")) {
//                    AUX_CLS.writeToBinFile(workers,"./DATA/data.bin" );
//                }
//                else if (actionCommand.equals("readFromBinFile")) {
//                    workers = AUX_CLS.readFromBinFile("./DATA/data.bin" );
//                    showViewPanel();
                }
            }
        });
    }

    private void showViewPanel() {
        getContentPane().remove(currentScreen);
        currentScreen = viewPanel;
        viewPanel.setWorkers(workers);
        add(currentScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showFormPanel() {
        getContentPane().remove(currentScreen);
        currentScreen = formPanel;
        add(currentScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Returns true if shift is in conflict
    private boolean checkConflicts(Worker w1, Worker w2) {
        for (Shift shift1 : w1.getShifts()) {
            for (Shift shift2 : w2.getShifts()) {
                if (shift2.getDay() == shift1.getDay() && shift2.getTime() == shift1.getTime()) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Shift> getConflictingShifts(Worker w1, Worker w2) {
        List<Shift> conflicts = new ArrayList<>();
        for (Shift shift1 : w1.getShifts()) {
            for (Shift shift2 : w2.getShifts()) {
                if (shift1.getDay() == shift2.getDay() && shift1.getTime() == shift2.getTime()) {
                    conflicts.add(shift1);
                }
            }
        }
        return conflicts;
    }

    private boolean addWorker(Worker worker) {
        for (Worker w : workers) {
            List<Shift> conflicts = getConflictingShifts(w, worker);
            if (!conflicts.isEmpty()) {
                StringBuilder conflictMsg = new StringBuilder("Conflict with worker: " + w.getName() + "\n");
                for (Shift s : conflicts) {
                    conflictMsg.append("- ").append(s.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(this, conflictMsg.toString(), "Shift Conflict", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (w.getName().equals(worker.getName())) {
                w.addShifts(worker.getShifts());
                w.setReminders(worker.getReminders());
                JOptionPane.showMessageDialog(this, "Worker updated", null, JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        workers.add(worker);
        JOptionPane.showMessageDialog(this, "New worker added", null, JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}


