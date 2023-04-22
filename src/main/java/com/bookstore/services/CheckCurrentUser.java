package com.bookstore.services;

import java.util.List;

import javax.swing.JOptionPane;

import com.bookstore.bus.CurrentUserBUS;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.models.CurrentUserModel;

public class CheckCurrentUser {
    private CurrentUserBUS currentUserBus;

    public CheckCurrentUser() {
        currentUserBus = CurrentUserBUS.getInstance();
    }

    public int getCurrentUserId() {
        List<CurrentUserModel> currentUser = currentUserBus.getAllModels();
        int idCurrent = currentUser.get(0).getCurrentUserId();
        return idCurrent;
    }

    public void setCurrentUserId(int currentUserId) {
        CurrentUserModel currentUserModel = new CurrentUserModel(currentUserId);
        currentUserBus.updateModel(currentUserModel);
    }

    public boolean checkStatus() {
        if (getCurrentUserId() == 0) {
            JOptionPane.showMessageDialog(null,
                    "You are not logged in, please login!");
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
            return false;
        } else {
            return true;
        }
    }
}
