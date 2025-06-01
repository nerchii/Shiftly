import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;


public class APP_Test {
    public static void main(String[] args) {
        //FlatLaf.setup(new FlatLightLaf());
        FlatLaf.setup(new FlatArcDarkOrangeIJTheme());
        FlatLaf.setup(new FlatDarculaLaf());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
