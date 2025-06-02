import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMenuBar extends JMenuBar implements ActionListener {
    private JMenu fileMenu;
    private JMenu viewMenu;

    private JMenuItem openFromFile;
    private JMenuItem saveToFile;
    private JMenuItem exit;

    private JMenuItem mainPage;
    private JMenuItem scheduleView;
    private MainFrame mainFrame;

    private MenuBarListener menuBarListener;

    public AppMenuBar(MainFrame mainFrame) {
        initCompoments();
        activateMenuBar();
        layoutComponents();
    }


    private void initCompoments() {
        fileMenu = new JMenu("Files");
        openFromFile = new JMenuItem("Open");
        saveToFile = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        viewMenu = new JMenu("View");
        mainPage = new JMenuItem("Main page");
        scheduleView = new JMenuItem("Shift schedule");

        add(fileMenu);
        add(viewMenu);

//        // Dodaj ikone ako želiš
//        openFromFile.setIcon(new ImageIcon("icons/open.png"));
//        saveToFile.setIcon(new ImageIcon("icons/save.png"));
    }

    private void activateMenuBar() {
        openFromFile.addActionListener(this);
        openFromFile.setActionCommand("readFromFile");
        saveToFile.addActionListener(this);
        saveToFile.setActionCommand("saveToFile");
        exit.addActionListener(this);
        mainPage.addActionListener(this);
        mainPage.setActionCommand("viewPanel");
        scheduleView.addActionListener(this);
        scheduleView.setActionCommand("formPanel");
    }

    private void layoutComponents() {
        fileMenu.add(openFromFile);
        fileMenu.add(saveToFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        viewMenu.add(mainPage);
        viewMenu.add(scheduleView);

        this.add(fileMenu);
        this.add(viewMenu);
    }

    public void setMenuBarListener(MenuBarListener menuBarListener) {
        this.menuBarListener = menuBarListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (menuBarListener != null) {
            String action = actionEvent.getActionCommand();
            menuBarListener.menuBarEventOccurred(action);
        } else {
            JOptionPane.showMessageDialog(this, "FormPanelListener is null", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
