
package control;

public class DepencePos {

    private int ylem_id;
    private int alam_id;
    private String avaja;
    private String avatud;
    private String muutja;
    private String muudetud;
    private String sulgeja;
    private String suletud;

    public DepencePos() {
    }

    public DepencePos(int yl_id, int al_id) {
        this.ylem_id = yl_id;
        this.alam_id = al_id;
    }

    public DepencePos(int yl_id, int al_id, String avaja, String avatud, String muutja, String muudetud, String sulgeja, String suletud) {
        this.ylem_id = yl_id;
        this.alam_id = al_id;
        this.avaja = avaja;
        this.avatud = avatud;
        this.muutja = muutja;
        this.muudetud = muudetud;
        this.sulgeja = sulgeja;
        this.suletud = suletud;
    }


    public int getYlem_id() {
        return ylem_id;
    }


    public void setYlem_id(int ylem_id) {
        this.ylem_id = ylem_id;
    }

        public int getAlam_id() {
        return alam_id;
    }

 
    public void setAlam_id(int alam_id) {
        this.alam_id = alam_id;
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

    @Override
    public String toString() {

        String both = String.valueOf(ylem_id) + "," + String.valueOf(alam_id);

        return both;
    }
}
