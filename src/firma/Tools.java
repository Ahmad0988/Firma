package firma;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Tools{
    public static void messageBox( String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static Object inputBox(String title){
        Object myObj = JOptionPane.showInputDialog(title);
        return  myObj;
    }
    
    public static boolean confirmMesseg(String message){
        int msgConf = JOptionPane.showConfirmDialog(null, message);
        if(msgConf == JOptionPane.YES_OPTION){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void createFolder(String folderNane, String path){
        File f =new File(path + "/" + folderNane);
        f.mkdir();
    }
    
    public static void createFolder(String folderNane){
        File f =new File(folderNane);
        f.mkdir();
    }
    
    public static void createEmptyFile(String fileName ){
        File f = new File(fileName + ".txt");
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createMultiEmptyFiles(String filesName[]){
        for(String fileName: filesName){
            createEmptyFile( fileName );
        }
    }
    
    public static void createFile(String fileName, Object data[]){
        try {
            PrintWriter p = new PrintWriter(fileName + ".txt");
            for(Object obj : data){
                p.println(obj);
            }
            p.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createFiles(String filesNames[], Object AllData[][]){
        for(int i = 0 ; i < filesNames.length ; i++){
            createFile(filesNames[i], AllData[i]);
        }
    }
    
    public static void openForm(JFrame form) throws IOException{
        form.setLocationRelativeTo(null);
        InputStream resourceBuff = Tools.class.getResourceAsStream("emp.png");
        BufferedImage img = ImageIO.read(resourceBuff);
        form.setIconImage(img);
        form.setDefaultCloseOperation(2);
        form.getContentPane().setBackground(Color.WHITE);
        form.setVisible(true);
        
    }
    public static void clearText(Container form){
        for(Component feld : form.getComponents()){
            if(feld instanceof JTextField){
                JTextField txt = (JTextField) feld;
                txt.setText("");
           }
            else if (feld instanceof Container){
                clearText((Container) feld);
            }
        }
    }
    
    public static String getNumber(String text){
        String val="";
        for(char c : text.toCharArray()){
            if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || 
                    c == '5' || c == '6' || c == '7' || c == '8' || c == '9'){
                val += c;
            }
        }
        return val;
    }
    
    public static int getNumberToInteger(String text){
        String val=getNumber(text);
        return Integer.parseInt(val);
    }
    
    public static String removeNumber(String text){
        String val="";
        for(char c : text.toCharArray()){
            if( !(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || 
                    c == '5' || c == '6' || c == '7' || c == '8' || c == '9') ){
                val += c;
            }
        }
        return val;
    }
    
    public static void printScreen(String imageName, JFrame form){
        try {
            form.setState(1);
            Robot r = new Robot();
            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage img = r.createScreenCapture(rec);
            ImageIO.write(img, "jpg",new File(imageName + ".jpg") );
            form.setState(0);
        } catch (Exception ex) {
            messageBox(ex.getMessage());
        }
    }
    public static class StringTools{
        private static String inverseText;
        public static void printCharByChar(String text){
            for(char c : text.toCharArray()){
            System.out.println(c);
            }
        }
        public static void printCharByCharIbvers(String text){
            StringBuilder sb = new StringBuilder(text);
            inverseText = sb.reverse().toString();
            for(char c : inverseText.toCharArray()){
                System.out.println(c);
            }
        }
    }
    public class Table{
        public int columns;
        public Object[][] items;
        public Table(int columns){
            this.columns = columns;
            items = new Object[0][columns];
        }
        public void addNewRow(Object row[]){
            // Alte Elemente in einer temporären Variablen  behalten
            Object tempItems[][] = items;
            // Die Basisvariable um ein zusätzliches Element erhöhen
            items = new Object[items.length +1 ][columns];
            // Die alten Elemente im Basiselement ausfüllen 
            for(int i = 0; i< tempItems.length; i++){
                items[i] = tempItems[i];
            }
            // Die neue Zeile im Hauptelement hinzufügen 
            items[items.length - 1] = row;
        }
        public void printItems(){
            for(Object[] itemArray : items){
                for(Object item : itemArray){
                    System.out.print(item + " " );
                }
                System.out.println();
            }
        }
        public void editRow(int rowIndex, int columnIndex, Object newData){
            items[rowIndex][columnIndex] = newData;
        }
        public void deleteRow(int rowIndex){
            // Alte Elemente in einer temporären Variablen  behalten
            Object tempItems[][] = items;
            items = new Object[items.length -1 ][columns];
            int j = 0;
            for(int i = 0; i< tempItems.length; i++){
                if(i != rowIndex){
                    items[j] = tempItems[i];
                    j++;
                }
            }
        }
        public Object getValue(int rowIndex,int columnIndex){
            return items[rowIndex][columnIndex];
        }

        public Object[] getRow(int rowIndex){
            Object[] row = new Object[columns];
            for(int i = 0; i < columns ; i++)
                    row[i] = items[rowIndex][i];
            return  row;
        }
    }
    public class MergeArray{

        private Object[] array1;
        private Object[] array2;
        public MergeArray(Object[] array1,Object[] array2){
            this.array1 = array1;
            this.array2 = array2;
        }
        public Object[] mergeTowArrays(){
            int lengthArray1 = array1.length;
            int lengthArray2 = array1.length;
            Object[] arrayResult = new Object[lengthArray1 + lengthArray2];
            System.arraycopy(array1, 0, arrayResult, 0, lengthArray1);  
            System.arraycopy(array2, 0, arrayResult, lengthArray1, lengthArray2); 
            return arrayResult; 
        }
    }
}