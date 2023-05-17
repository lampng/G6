package Personal_development.g6.model;

public class order_model {
    private int id_order;
    private String customer_ID;
    private Double price_total;
    private String date_order;
    private String order_status;

    public order_model() {
        this.id_order = id_order;
        this.customer_ID = customer_ID;
        this.price_total = price_total;
        this.date_order = date_order;
        this.order_status = order_status;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public Double getPrice_total() {
        return price_total;
    }

    public void setPrice_total(Double price_total) {
        this.price_total = price_total;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
