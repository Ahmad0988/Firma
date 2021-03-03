
package datenbank;

import controls.JMyCombo;
import firma.Tools;
import firma.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class go {
    private static String url = "";
    private static String dbName = "firma";
    private static java.sql.Connection con;
    
    private static void setURL(){
        url = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncodingUTF-8";
    }
    
    private static void setConnection(){
        try {
            setURL();
            con = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
            Tools.messageBox(ex.getMessage());
        }
    }
    public static boolean checkUserAndPass(String username,String password){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strCheck = String.format("SELECT * FROM users WHERE username = '%s'"+
                    "AND password = '%s'", username,password);
            stmt.executeQuery(strCheck);
            while(stmt.getResultSet().next()){
                con.close();
                return true;
            }
            con.close();
        }
        catch(SQLException ex){
            Tools.messageBox(ex.getMessage());
        }
        return false;
    }
    
    
   //Die Funktion dieser Methode dient nur zum Eingeben, Ändern und Löschen 
    public static boolean runNonQuery(String sqlstatement){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            stmt.execute(sqlstatement);
            con.close();
            return true;
        }
        catch(SQLException ex){
            Tools.messageBox(ex.getMessage());
            return false;
        }
    }
    
    //Diese Methode sucht nach der größten Zahl in der Tabelle (sie muss PRIMARY KEY sein) und erhöht sie um eins
    public static String getAutoNumber(String tableName, String columnName){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            String strAutoNumber = String.format("SELECT max(%s)+1 AS autonumber FROM %s", columnName,tableName);
            stmt.executeQuery(strAutoNumber);
            String num = "";
            while(stmt.getResultSet().next()){
                num = stmt.getResultSet().getString("autonumber");
            }
            con.close();
            if(num == null || "".equals(num) ){
                return "1";
            }
            else
                return num;
        }
        catch(SQLException ex){
            Tools.messageBox(ex.getMessage());
            return "0";
        }
    }
    
    public static Table getTableData(String sqlstatement){
        Tools t = new Tools();
        try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sqlstatement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
            
            Table table = t.new Table(col);
            while(rs.next()){
                Object row[] = new Object[col];
                for(int i = 0;i < col; i++){
                    row[i] = rs.getString(i + 1);
                }
                table.addNewRow(row);
            }
            con.close();
            return table;
        }
        catch(SQLException ex){
            Tools.messageBox(ex.getMessage());
            return t.new Table(0);
        }
    }
    
    public static void fillCompo(String tableName, String columnName, JComboBox combo ){
        try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            
            String strSelect = String.format("SELECT %s FROM %s", columnName,tableName);
            rs = stmt.executeQuery(strSelect);
            rs.last();
            int CountRows = rs.getRow();
            rs.beforeFirst();
            
            String values[] = new String[CountRows];
            int i = 0;
            while(rs.next()){
                values[i] = rs.getString(1);
                i++;
            }
            con.close();
            
            combo.setModel(new DefaultComboBoxModel(values));
            
        }
        catch(SQLException ex){
            Tools.messageBox(ex.getMessage());
            
        }
    }
    
            
        public static void fillToJTable(String tableOrSelectStatement, JTable table){
            try{
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;
            String strSelect;
            if("select ".equals(tableOrSelectStatement.substring(0,7).toLowerCase())){
                strSelect = tableOrSelectStatement;
            }
            else{
                strSelect = String.format("select * from %s", tableOrSelectStatement);
            }
            rs = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd = rs.getMetaData();
            int col = rsmd.getColumnCount();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Vector row = new Vector();
            model.setRowCount(0);
            while(rs.next()){
                row = new Vector(col);
                for(int i = 1; i <= col; i++){
                    row.add(rs.getString(i));
                }
                model.addRow(row);
            }
            if(table.getColumnCount() != col){
                Tools.messageBox("JTable Columns Count not Equal To Query Columns Count\n"
                        + "JTable Columns Count is: " + table.getColumnCount() + "\n"
                                + "Query Columns Count is: "+col);
            }
            con.close();
            }
            catch(SQLException ex){
                Tools.messageBox(ex.getMessage());

            }
        }
}
