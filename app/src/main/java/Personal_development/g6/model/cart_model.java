package Personal_development.g6.model;

public class cart_model {
    private String ac_id_cart;
    private int drink_id;
    private int quantity;
    private int order_ID;

    public cart_model(String ac_id_cart, int drink_id, int quantity, int order_ID) {
        this.ac_id_cart = ac_id_cart;
        this.drink_id = drink_id;
        this.quantity = quantity;
        this.order_ID = order_ID;
    }

    public cart_model(String ac_id_cart, int drink_id, int quantity) {
        this.ac_id_cart = ac_id_cart;
        this.drink_id = drink_id;
        this.quantity = quantity;
    }

    public cart_model() {

    }

    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public String getAc_id_cart() {
        return ac_id_cart;
    }

    public void setAc_id_cart(String ac_id_cart) {
        this.ac_id_cart = ac_id_cart;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
