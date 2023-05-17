package Personal_development.g6.model;

public class account_model {
    private String id_ac;
    private int role_ID;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String phone_ac;
    private String password_ac;
    private String address_ac;
    private byte[] avatar_ac;

    public byte[] getAvatar_ac() {
        return avatar_ac;
    }

    public void setAvatar_ac(byte[] avatar_ac) {
        this.avatar_ac = avatar_ac;
    }

    private String role_NAME;
    //String int string tring string string string int string string string string
    public account_model(String id_ac, int role_ID, String first_name, String middle_name, String last_name, String phone_ac, String password_ac, String address_ac, String role_NAME) {
        this.id_ac = id_ac;
        this.role_ID = role_ID;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.phone_ac = phone_ac;
        this.password_ac = password_ac;
        this.address_ac = address_ac;
        this.role_NAME = role_NAME;
    }
    public account_model() {}
    public account_model(String id_ac, int role_ID, String first_name, String middle_name, String last_name, String phone_ac, String password_ac, String address_ac) {
        this.id_ac = id_ac;
        this.role_ID = role_ID;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.phone_ac = phone_ac;
        this.password_ac = password_ac;
        this.address_ac = address_ac;
    }
    public String getRole_NAME() {
        return role_NAME;
    }
    public void setRole_NAME(String role_NAME) {
        this.role_NAME = role_NAME;
    }
    public String getId_ac() {
        return id_ac;
    }
    public void setId_ac(String id_ac) {
        this.id_ac = id_ac;
    }
    public int getRole_ID() {
        return role_ID;
    }
    public void setRole_ID(int role_ID) {
        this.role_ID = role_ID;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getMiddle_name() {
        return middle_name;
    }
    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getPhone_ac() {
        return phone_ac;
    }
    public void setPhone_ac(String phone_ac) {
        this.phone_ac = phone_ac;
    }
    public String getPassword_ac() {
        return password_ac;
    }
    public void setPassword_ac(String password_ac) {
        this.password_ac = password_ac;
    }
    public String getAddress_ac() {
        return address_ac;
    }
    public void setAddress_ac(String address_ac) {
        this.address_ac = address_ac;
    }
}
