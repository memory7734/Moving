package me.memory7734.moving.bean;

/**
 * Created by Elijah on 16/11/24.
 */

public class UserBean {
    private int userkey;
    private String phone;
    private String username;
    private String password;
    private int birthdate;
    private String gender;
    private String city;
    private String wheelchair;
    private String blood_type;
    private String fitzpatrick_skin_type;

    public UserBean(int userkey, String phone, String username, String password, int birthdate, String gender, String city, String wheelchair, String blood_type, String fitzpatrick_skin_type) {
        this.userkey = userkey;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.wheelchair = wheelchair;
        this.blood_type = blood_type;
        this.fitzpatrick_skin_type = fitzpatrick_skin_type;
    }

    public int getUserkey() {
        return userkey;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public String getFitzpatrick_skin_type() {
        return fitzpatrick_skin_type;
    }
}
