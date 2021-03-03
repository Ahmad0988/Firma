
package entity;

import firma.Tools;
import javax.swing.JTable;

public class Angestellte implements mainData{
    
    private int AngetellteId;
    private String Name;
    private String Adresse;
    private double Gehalt;
    private String EinstellungsDate;
    private String Geburtstag;
    private int AbteilungsId;

    public int getAngetellteId() {
        return AngetellteId;
    }

    public void setAngetellteId(int AngetellteId) {
        this.AngetellteId = AngetellteId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public double getGehalt() {
        return Gehalt;
    }

    public void setGehalt(double Gehalt) {
        this.Gehalt = Gehalt;
    }

    public String getEinstellungsDate() {
        return EinstellungsDate;
    }

    public void setEinstellungsDate(String EinstellungsDate) {
        this.EinstellungsDate = EinstellungsDate;
    }

    public String getGeburtstag() {
        return Geburtstag;
    }

    public void setGeburtstag(String Geburtstag) {
        this.Geburtstag = Geburtstag;
    }

    public int getAbteilungsId() {
        return AbteilungsId;
    }

    public void setAbteilungsId(int AbteilungsId) {
        this.AbteilungsId = AbteilungsId;
    }

    @Override
    public void add() {
        String insert = String.format("INSERT INTO angestellte values(%s,'%s','%s',%s"
        + ",'%s','%s',%s)", AngetellteId,Name,Adresse,Gehalt,EinstellungsDate,Geburtstag,AbteilungsId);
        boolean isAdd = datenbank.go.runNonQuery(insert);
        if(isAdd){
            Tools.messageBox("Der Angestellte wurde hinzugefügt.");
        }    
    }

    @Override
    public void update() {
        String strUpdate = String.format("update angestellte set angestellteid = %s,angestelltename ='%s',adresse = '%s',"
                + "gehalt = %s,einstellungsdate = '%s',Geburtstag = '%s',abteilungsid = %s where angestellteid = %s"
                , AngetellteId,Name,Adresse,Gehalt,EinstellungsDate,Geburtstag,AbteilungsId,AngetellteId);
        boolean isUpdate = datenbank.go.runNonQuery(strUpdate);
        if(isUpdate){
            Tools.messageBox("Den Datensatz wurde geändert.");
        } 
    }

    @Override
    public void delete() {
         String strDelete = String.format("delete from angestellte "
                                    + "where angestellteid =%s",AngetellteId);
        boolean isDelete = datenbank.go.runNonQuery(strDelete);
        if(isDelete){
            Tools.messageBox("Den Datensatz wurde gelöscht.");
        }
    }

    @Override
    public String getAutoNumber() {
        return datenbank.go.getAutoNumber("angestellte","angestellteid");   
    }

    @Override
    public void getAllRows(JTable table) {
        datenbank.go.fillToJTable("angestellte_data", table);
    }

    @Override
    public void getOneRow(JTable table) {
        String strSelect = String.format("select * from angestellte_data where angestellte_Nummer =%s", AngetellteId);
        datenbank.go.fillToJTable(strSelect, table);
    }

    @Override
    public void getCustomRows(String statement, JTable table) {
        datenbank.go.fillToJTable(statement, table);
    }

    @Override
    public String getValueByName(String name) {
        String strSelect = String.format("select angestellteid from angestellte where angestelltename ='%s'", name);
        String strVal = (String)datenbank.go.getTableData(strSelect).items[0][0];
        return strVal;
    }

    @Override
    public String getNameByValue(String value) {
        String strSelect = String.format("select angestelltename from angestellte where angestellteid =%s", value);
        String strName = (String)datenbank.go.getTableData(strSelect).items[0][0];
        return strName; 
    }
    
    
}
