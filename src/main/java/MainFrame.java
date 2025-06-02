import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private ViewPanel viewPanel;
    private FormPanel formPanel;  //postavlja se view panel na njega  i mrnu bar
    private AppMenuBar appMenuBar;
    private JPanel currentScreen;
    private ArrayList<Worker> workers;


    public MainFrame() {
        super("Shiftly");
        workers = new ArrayList<>();
        initMainFrame();
        initComponents();
        initLayout();
        activateFrame();
    }

    private void initMainFrame() {
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  //da je u centru zaslona
        setResizable(false); //da je nemos resajzat
        setVisible(true);  //da je window visible
    }

    private void initComponents() {
        viewPanel = new ViewPanel();
        formPanel = new FormPanel();
        appMenuBar = new AppMenuBar(this);
        setJMenuBar(appMenuBar);
        currentScreen = formPanel;

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
                } else if (actionCommand.equals("saveToFile")) {
                    AUX_CLS.writeToFile(workers,"./DATA/data.json" );
                } else if (actionCommand.equals("readFromFile")) {
                    workers = AUX_CLS.readFromFile("./DATA/data.json" );
                    showViewPanel();
                }
            }
        });
    }

    private void showViewPanel() {
        getContentPane().remove(currentScreen);
        currentScreen = viewPanel;
        viewPanel.updateData(workers);
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

    private boolean addWorker(Worker worker) {
        for (Worker w : workers) {
            if (checkConflicts(w, worker)) {
                JOptionPane.showMessageDialog(this, "CONFLICTS!");
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


