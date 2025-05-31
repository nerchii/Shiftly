import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private ViewPanel viewPanel;
    private FormPanel formPanel;  //postavlja se view panel na njega  i mrnu bar
    private AppMenuBar appMenuBar;
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
        setSize(800,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  //da je u centru zaslona
        setResizable(false); //da je nemos resajzat
        setVisible(true);  //da je window visible
    }

    private void initComponents() {
        viewPanel = new ViewPanel();
        formPanel = new FormPanel(this);
        appMenuBar = new AppMenuBar();

    }

    private void initLayout() {
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);
        //add(formPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void activateFrame() {
    }

    protected void showViewPanel(){
        getContentPane().removeAll();
        add(viewPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void addWorker(Worker worker){
        if (!workers.contains(worker)){
            workers.add(worker);
            JOptionPane.showMessageDialog(this, "Worker added to the list", null, JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Worker already exists", "info", JOptionPane.WARNING_MESSAGE);
        }
    }


}


