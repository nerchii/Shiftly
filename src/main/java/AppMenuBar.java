import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMenuBar extends JMenuBar implements ActionListener {
    private JMenu fileMenu;  //za saveanje u bin i json
    private JMenu jsonMenu;
    private JMenu binMenu;

    private JMenu viewMenu;

    private JMenuItem openFromJSONFile;
    private JMenuItem saveToJSONFile;
    private JMenuItem openFromBinFile;
    private JMenuItem saveToBinFile;

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
        jsonMenu = new JMenu("JSON");
        binMenu = new JMenu("BIN");

        openFromJSONFile = new JMenuItem("Open from JSON");
        saveToJSONFile = new JMenuItem("Save to JSON");
        openFromBinFile = new JMenuItem("Open from Bin");
        saveToBinFile = new JMenuItem("Save to Bin");

        viewMenu = new JMenu("View");
        mainPage = new JMenuItem("Main page");
        scheduleView = new JMenuItem("Add shift");

        add(fileMenu);
        add(viewMenu);

    }

    private void activateMenuBar() {
        openFromJSONFile.addActionListener(this);
        openFromJSONFile.setActionCommand("readFromJsonFile");
        saveToJSONFile.addActionListener(this);
        saveToJSONFile.setActionCommand("saveToJsonFile");

        openFromBinFile.addActionListener(this);
        openFromBinFile.setActionCommand("readFromBinFile");
        saveToBinFile.addActionListener(this);
        saveToBinFile.setActionCommand("saveToBinFile");


        mainPage.addActionListener(this);
        mainPage.setActionCommand("viewPanel");
        scheduleView.addActionListener(this);
        scheduleView.setActionCommand("formPanel");
    }

    private void layoutComponents() {
        fileMenu.add(jsonMenu);
        fileMenu.add(binMenu);

        jsonMenu.add(openFromJSONFile);
        jsonMenu.add(saveToJSONFile);
        binMenu.add(openFromBinFile);
        binMenu.add(saveToBinFile);

        //fileMenu.addSeparator();

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
