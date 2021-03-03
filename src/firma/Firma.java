
package firma;

import forms.frmAngetstellte;
import forms.frmLogin;
import forms.frmMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Firma {

    public static void main(String[] args) {
        try {
           Tools.openForm(new frmLogin());
           //Tools.openForm(new frmTest());
           //Tools.openForm(new frmAngetstellte());
        } catch (IOException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
