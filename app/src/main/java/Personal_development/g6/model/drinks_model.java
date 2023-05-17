package Personal_development.g6.model;

public class drinks_model {
    private int id_drinks;
    private int cat_ID;
    private String name_drinks;
    private byte[] image_drinks;
    private String des_drinks;
    private Double price_drinks;

    private int sumQuantity;

    public drinks_model(int id_drinks, int cat_ID, String name_drinks, byte[] image_drinks, String des_drinks, Double price_drinks, int sumQuantity) {
        this.id_drinks = id_drinks;
        this.cat_ID = cat_ID;
        this.name_drinks = name_drinks;
        this.image_drinks = image_drinks;
        this.des_drinks = des_drinks;
        this.price_drinks = price_drinks;
        this.sumQuantity = sumQuantity;
    }

    public int getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(int sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public drinks_model(int id_drinks, int cat_ID, String name_drinks, byte[] image_drinks, String des_drinks, Double price_drinks) {
        this.id_drinks = id_drinks;
        this.cat_ID = cat_ID;
        this.name_drinks = name_drinks;
        this.image_drinks = image_drinks;
        this.des_drinks = des_drinks;
        this.price_drinks = price_drinks;
    }

    public drinks_model() {

    }

    public int getId_drinks() {
        return id_drinks;
    }

    public void setId_drinks(int id_drinks) {
        this.id_drinks = id_drinks;
    }

    public int getCat_ID() {
        return cat_ID;
    }

    public void setCat_ID(int cat_ID) {
        this.cat_ID = cat_ID;
    }

    public String getName_drinks() {
        return name_drinks;
    }

    public void setName_drinks(String name_drinks) {
        this.name_drinks = name_drinks;
    }

    public byte[] getImage_drinks() {
        return image_drinks;
    }

    public void setImage_drinks(byte[] image_drinks) {
        this.image_drinks = image_drinks;
    }

    public String getDes_drinks() {
        return des_drinks;
    }

    public void setDes_drinks(String des_drinks) {
        this.des_drinks = des_drinks;
    }

    public Double getPrice_drinks() {
        return price_drinks;
    }

    public void setPrice_drinks(Double price_drinks) {
        this.price_drinks = price_drinks;
    }
}
