package dataEOra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.temporal.ChronoUnit;
import java.time.Period;

public class DataEasy {
    
    private String  data;
    
    private Integer giorno;
    private Integer mese;
    private Integer anno;

    public DataEasy() {        
        LocalDate dataOdierna = LocalDate.now();
                
        giorno = dataOdierna.getDayOfMonth();
        mese   = dataOdierna.getMonthValue();
        anno   = dataOdierna.getYear();
        
        data   = dataOdierna.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));        
    }

    public DataEasy(String data) throws Exception {
        setData(data);
    }

    public DataEasy(Integer giorno, Integer mese, Integer anno) throws Exception {
        String dataFormatoTesto;
        
        try {
            dataFormatoTesto = (giorno <= 9 ? "0" + giorno : giorno) + "/" + (mese <= 9 ? "0" + mese : mese) + "/" + anno;
            
            setData(dataFormatoTesto); 
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }

    public DataEasy(DataEasy data) {
        this.data   = data.data;
        this.giorno = data.giorno;
        this.mese   = data.mese;
        this.anno   = data.anno;        
    }
    
    public String getData() {
        return data;
    }

    final public void setData(String data) throws Exception {
        try{
            LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));       
        
            this.data = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            giorno    = localDate.getDayOfMonth();
            mese      = localDate.getMonthValue();
            anno      = localDate.getYear();            
            
        } catch (NullPointerException e) {
            throw new Exception("La data non può essere nulla!");
        } catch (DateTimeException e) {
            throw new Exception("Data errata!");
        }
    }

    public Integer getGiorno() {
        return giorno;
    }

    final public void setGiorno(Integer giorno) throws Exception {        
        try {
            LocalDate localDate = LocalDate.of(this.anno, this.mese, giorno);
            
            this.giorno = localDate.getDayOfMonth();
            
            data        = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
        } catch (NullPointerException e) {
            throw new Exception("Il giorno non può essere nullo!");
        } catch (DateTimeException e) {
            throw new Exception("Giorno errato!");
        }
    }

    public Integer getMese() {
        return mese;
    }

    final public void setMese(Integer mese) throws Exception {
        try {
            LocalDate localDate = LocalDate.of(this.anno, mese, this.giorno);
            
            this.mese = localDate.getMonthValue();     
            
            data      = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
        } catch (NullPointerException e) {
            throw new Exception("Il mese non può essere nullo!");
        } catch (DateTimeException e) {
            throw new Exception("Mese errato!");
        }
    }

    public Integer getAnno() {
        return anno;
    }

    final public void setAnno(Integer anno) throws Exception {
        try {
            LocalDate localDate = LocalDate.of(anno, this.mese, this.giorno);
            
            this.anno = localDate.getYear();        
            
            data      = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
        } catch (NullPointerException e) {
            throw new Exception("Il giorno non può essere nullo!");
        } catch (DateTimeException e) {
            throw new Exception("Giorno errato!");
        }
    }

    public String giornoDellaSettimana() {        
        String giornoSettimana;

        LocalDate localDate = LocalDate.of(anno, mese, giorno);         
        
        giornoSettimana = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY);
                
        return giornoSettimana;
    }  
    
    public static Integer differenzaInGiorni(DataEasy dataAntecedente, DataEasy dataSuccessiva) throws Exception {
        Long giorni;
                
        try {
            LocalDate localDate1 = LocalDate.of(dataAntecedente.anno, dataAntecedente.mese, dataAntecedente.giorno);
            LocalDate localDate2 = LocalDate.of(dataSuccessiva.anno, dataSuccessiva.mese, dataSuccessiva.giorno);
            
            giorni = ChronoUnit.DAYS.between(localDate1, localDate2);                       
            
            return giorni.intValue();
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }

    public static Integer differenzaInSettimane(DataEasy dataAntecedente, DataEasy dataSuccessiva) throws Exception {
        Long settimane;
                
        try {
            LocalDate localDate1 = LocalDate.of(dataAntecedente.anno, dataAntecedente.mese, dataAntecedente.giorno);
            LocalDate localDate2 = LocalDate.of(dataSuccessiva.anno, dataSuccessiva.mese, dataSuccessiva.giorno);
                        
            settimane = ChronoUnit.WEEKS.between(localDate1, localDate2);
            
            return settimane.intValue();
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }

    public static Integer differenzaInMesi(DataEasy dataAntecedente, DataEasy dataSuccessiva) throws Exception {
        Long mesi;
        
        try {
            LocalDate localDate1 = LocalDate.of(dataAntecedente.anno, dataAntecedente.mese, dataAntecedente.giorno);
            LocalDate localDate2 = LocalDate.of(dataSuccessiva.anno, dataSuccessiva.mese, dataSuccessiva.giorno);
            
            mesi = ChronoUnit.MONTHS.between(localDate1, localDate2);            
            
            return mesi.intValue();            
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }

    public static Integer differenzaInAnni(DataEasy dataAntecedente, DataEasy dataSuccessiva) throws Exception {
        Long anni;
        
        try {
            LocalDate localDate1 = LocalDate.of(dataAntecedente.anno, dataAntecedente.mese, dataAntecedente.giorno);
            LocalDate localDate2 = LocalDate.of(dataSuccessiva.anno, dataSuccessiva.mese, dataSuccessiva.giorno);
            
            anni = ChronoUnit.YEARS.between(localDate1, localDate2);            
            
            return anni.intValue(); 
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }

    public static String differenzaInGiorniMesiAnni(DataEasy dataAntecedente, DataEasy dataSuccessiva) throws Exception {
        Integer giorni, mesi, anni;
        
        try {
            LocalDate localDate1 = LocalDate.of(dataAntecedente.anno, dataAntecedente.mese, dataAntecedente.giorno);
            LocalDate localDate2 = LocalDate.of(dataSuccessiva.anno, dataSuccessiva.mese, dataSuccessiva.giorno);                        
            
            anni   = Period.between(localDate1, localDate2).getYears();
            mesi   = Period.between(localDate1, localDate2).getMonths();
            giorni = Period.between(localDate1, localDate2).getDays();
            
            return giorni + "gg " + mesi + "mm " + anni + "aaaa";
        } catch (NullPointerException e) {
            throw new Exception("Uno o più parametri sono nulli!");
        }
    }
    
    @Override
    public String toString() {
        String giornoSettimana;

        LocalDate localDate = LocalDate.of(anno, mese, giorno);         
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");  
        
        giornoSettimana = localDate.format(formatter);
                
        return giornoSettimana;       
    }        
}
