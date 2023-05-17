package Personal_development.g6.model;

public class cat_model {
    private int id_cat;
    private String name_cat;
    private byte[] image_cat;

    public cat_model() {
    }

    public cat_model(int id_cat, String name_cat, byte[] image_cat) {
        this.id_cat = id_cat;
        this.name_cat = name_cat;
        this.image_cat = image_cat;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public byte[] getImage_cat() {
        return image_cat;
    }

    public void setImage_cat(byte[] image_cat) {
        this.image_cat = image_cat;
    }
}
