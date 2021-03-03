
package entity;

import firma.Tools;
import javax.swing.JTable;

public class AngestelltPhones implements mainData{
    private int AngestellteId;
    private String Phone;

    public int getAngestellteId() {
        return AngestellteId;
    }

    public void setAngestellteId(int AngestellteId) {
        this.AngestellteId = AngestellteId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public void add() {
        String insert = String.format("INSERT INTO angestellte_telefonnummer values(%s,'%s')",
                AngestellteId,Phone);
        boolean isAdd = datenbank.go.runNonQuery(insert);
        if(isAdd){
            //Tools.messageBox("Die Telefonnummer wurde hinzugefügt.");
        } 
    }

    @Override
    public void update() {
        Tools.messageBox("Ändern methode in der Angestellte-_telefonnummer ist nicht verfügbar.");
    }

    @Override
    public void delete() {
        String strDelete = String.format("delete from angestellte_telefonnummer "
                                    + "where angestellteid =%s and telefonnuumer = '%s'",AngestellteId,Phone);
        boolean isDelete = datenbank.go.runNonQuery(strDelete);
        if(isDelete){
            //Tools.messageBox("Die Telfonnummer wurde gelöscht.");
        }
    }
    public void deleteByAngestellte(){
        String strDelete = String.format("delete from angestellte_telefonnummer "
                                    + "where angestellteid =%s",AngestellteId);
        boolean isDelete = datenbank.go.runNonQuery(strDelete);
        if(isDelete){
           // Tools.messageBox("Die Telfonnummern wurden gelöscht.");
        }
    }

    @Override
    public String getAutoNumber() {
        Tools.messageBox("getAutoNumber() methode in der Angestellte-_telefonnummer ist nicht verfügbar.");
        return "";
    }

    @Override
    public void getAllRows(JTable table) {
        String strSelect = String.format("Select telefonnuumer from angestellte_telefonnummer "
                                    + "where angestellteid =%s",AngestellteId);
        datenbank.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getOneRow(JTable table) {
        Tools.messageBox("getOneRow() methode in der Angestellte-_telefonnummer ist nicht verfügbar.");
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
                    Tools.messageBox("getCustomRows() methode in der Angestellte-_telefonnummer ist nicht verfügbar.");

    }
    @Override
    public String getValueByName(String name) {
            Tools.messageBox("getValueByName() methode in der Angestellte-_telefonnummer ist nicht verfügbar.");
            return "";
    }

    @Override
    public String getNameByValue(String value) {
            Tools.messageBox("getNameByValue() methode in der Angestellte-_telefonnummer ist nicht verfügbar.");
            return "";    }
    
}
