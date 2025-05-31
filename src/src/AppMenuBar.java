import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppMenuBar extends JMenu implements ActionListener {
    private JMenu fileMenu;
    
    private JMenuItem openFromFile;
    private JMenuItem saveToFile;
    private JMenuItem exit;
    
//    private MenuBarListener menuBarListener;
    
    public AppMenuBar(){
        initCompoments();
        activateMenuBar();
    }

    private void initCompoments() {
    }

    private void activateMenuBar() {
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
