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
                addWorker(worker);
                showViewPanel();
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

    private void addWorker(Worker worker) {
        for (Worker w : workers) {
            if (w.getName().equals(worker.getName())) {
                w.setShifts(worker.getShifts());
                w.setReminders(worker.getReminders());
                JOptionPane.showMessageDialog(this, "Worker updated", null, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        workers.add(worker);
        JOptionPane.showMessageDialog(this, "New worker added", null, JOptionPane.INFORMATION_MESSAGE);
    }


}


