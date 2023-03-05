package com.bookstore.model;

import java.sql.Date;

public class GiftcardModel {
    private String giftcardID, email;
    private float value = 0;
    private Date remdemption_history, expiration_date;

    public GiftcardModel() {
    }

    public GiftcardModel(String giftcardID, String email, float value, Date remdemption_history, Date expiration_date) {
        this.giftcardID = giftcardID;
        this.email = email;
        this.value = value;
        this.remdemption_history = remdemption_history;
        this.expiration_date = expiration_date;
    }

    public String getGiftcardID() {
        return this.giftcardID;
    }

    public void setGiftcardID(String giftcardID) {
        this.giftcardID = giftcardID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getRemdemption_history() {
        return this.remdemption_history;
    }

    public void setRemdemption_history(Date remdemption_history) {
        this.remdemption_history = remdemption_history;
    }

    public Date getExpiration_date() {
        return this.expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public GiftcardModel giftcardID(String giftcardID) {
        setGiftcardID(giftcardID);
        return this;
    }

    public GiftcardModel email(String email) {
        setEmail(email);
        return this;
    }

    public GiftcardModel value(float value) {
        setValue(value);
        return this;
    }

    public GiftcardModel remdemption_history(Date remdemption_history) {
        setRemdemption_history(remdemption_history);
        return this;
    }

    public GiftcardModel expiration_date(Date expiration_date) {
        setExpiration_date(expiration_date);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " giftcardID='" + getGiftcardID() + "'" +
                ", email='" + getEmail() + "'" +
                ", value='" + getValue() + "'" +
                ", remdemption_history='" + getRemdemption_history() + "'" +
                ", expiration_date='" + getExpiration_date() + "'" +
                "}";
    }

}
