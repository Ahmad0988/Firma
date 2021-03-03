
package entity;

import firma.Tools;
import javax.swing.JTable;

public class Abteilung implements mainData{
    private int AbteilungsId;
    private String AbteilungsName;
    private String Ort;

    public int getAbteilungsId() {
        return AbteilungsId;
    }

    public void setAbteilungsId(int AbteilungsId) {
        this.AbteilungsId = AbteilungsId;
    }

    public String getAbteilungsName() {
        return AbteilungsName;
    }

    public void setAbteilungsName(String AbteilungsName) {
        this.AbteilungsName = AbteilungsName;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String Ort) {
        this.Ort = Ort;
    }

    @Override
    public void add() {
        String insert = String.format("INSERT INTO abteilung values(%s,'%s','%s')", AbteilungsId,AbteilungsName,Ort);
        boolean isAdd = datenbank.go.runNonQuery(insert);
        if(isAdd){
            Tools.messageBox("Die Abteilung wurde hinzugefügt.");
        }
    }

    @Override
    public void update() {
        String strUpdate = String.format("update abteilung set abteilungsname='%s',ort='%s' "
                                    + "where abteilungsid =%s", AbteilungsName,Ort,AbteilungsId);
        boolean isUpdate = datenbank.go.runNonQuery(strUpdate);
        if(isUpdate){
            Tools.messageBox("Die Abteilung wurde geändert.");
        }
    }

    @Override
    public void delete() {
        String strDelete = String.format("delete from abteilung "
                                    + "where abteilungsid =%s",AbteilungsId);
        boolean isDelete = datenbank.go.runNonQuery(strDelete);
        if(isDelete){
            Tools.messageBox("Die Abteilung wurde gelöscht.");
        }
    }

    @Override
    public String getAutoNumber() {
         return datenbank.go.getAutoNumber("abteilung","abteilungsid");
    }

    @Override
    public void getAllRows(JTable table) {
        datenbank.go.fillToJTable("abteilung_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = String.format("select * from abteilung_data where abteilung_Nummer =%s", AbteilungsId);
        datenbank.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        datenbank.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = String.format("select abteilungsid from abteilung where abteilungsname ='%s'", name);
        String strVal = (String)datenbank.go.getTableData(strSelect).items[0][0];
        return strVal;
    }

    @Override
    public String getNameByValue(String value) {
        String strSelect = String.format("select abteilungsname from abteilung where abteilungsid =%s", value);
        String strName = (String)datenbank.go.getTableData(strSelect).items[0][0];
        return strName;   
    }
    
    
}
