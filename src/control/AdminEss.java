package control;
public class AdminEss {

    private int id;
    private String kood;
    private String nimetus;
    private String kommentaar;
    private String avaja;
    private String avatud;
    private String muutja;
    private String muudetud;
    private String sulgeja;
    private String suletud;
    private int fk;

    public AdminEss() {
    }

    public AdminEss(String kood, String nimetus, String kommentaar) {
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
    }

    public AdminEss(int id, String kood, String nimetus, String kommentaar, int fk) {
        this.id = id;
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
        this.fk = fk;
    }

    public AdminEss(int id, String kood, String nimetus, String kommentaar, String avaja, String avatud, String muutja, String muudetud, String sulgeja, String suletud, int fk) {
        this.id = id;
        this.kood = kood;
        this.nimetus = nimetus;
        this.kommentaar = kommentaar;
        this.avaja = avaja;
        this.avatud = avatud;
        this.muutja = muutja;
        this.muudetud = muudetud;
        this.sulgeja = sulgeja;
        this.suletud = suletud;
        this.fk = fk;
    }


    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }


    public String getKood() {
        return kood;
    }

    public void setKood(String kood) {
        this.kood = kood;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

 
    public String getKommentaar() {
        return kommentaar;
    }


    public void setKommentaar(String kommentaar) {
        this.kommentaar = kommentaar;
    }

 
    public String getAvaja() {
        return avaja;
    }

 
    public void setAvaja(String avaja) {
        this.avaja = avaja;
    }


    public String getAvatud() {
        return avatud;
    }

    public void setAvatud(String avatud) {
        this.avatud = avatud;
    }

   
    public String getMuutja() {
        return muutja;
    }

 
    public void setMuutja(String muutja) {
        this.muutja = muutja;
    }

 
    public String getMuudetud() {
        return muudetud;
    }

    public void setMuudetud(String muudetud) {
        this.muudetud = muudetud;
    }

   
    public String getSulgeja() {
        return sulgeja;
    }

 
    public void setSulgeja(String sulgeja) {
        this.sulgeja = sulgeja;
    }

  
    public String getSuletud() {
        return suletud;
    }

    public void setSuletud(String suletud) {
        this.suletud = suletud;
    }

   
    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.fk = fk;
    }
    
      @Override
	public String toString() {
	return  nimetus ;
	}  
}
