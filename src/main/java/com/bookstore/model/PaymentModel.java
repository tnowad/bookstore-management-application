package com.bookstore.model;

import java.sql.Date;

import com.google.protobuf.Timestamp;

public class PaymentModel {
      private int id;
    private int orderId;
    private int userId;
    private int amount;
    private PaymentMethod paymentMethod;
    private int paymentMethodId;
    private PaymentStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public  PaymentModel() {
    }

    

    public PaymentModel(int id, int orderId, int userId, int amount, PaymentMethod paymentMethod, int paymentMethodId,
            PaymentStatus status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentMethodId = paymentMethodId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public enum PaymentStatus {
        PENDING
    }
}
