package com.bookstore.gui.form.admin.controller;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.JPanel;

import com.bookstore.gui.form.admin.RunForm;
import com.bookstore.gui.form.admin.component.bookListComponent.BrowseProductPanel;
import com.bookstore.gui.form.admin.component.dashboardComponent.DashboardPanel;
import com.bookstore.gui.form.admin.component.orderListComponent.OrderListPanel;
import com.bookstore.gui.form.admin.component.userListComponent.UserListPanel;
import com.bookstore.gui.form.admin.menu.HeaderForm;
import com.bookstore.gui.form.admin.menu.MenuForm;

public class ActionMenu implements ActionListener {

    private MenuForm menuForm;
    private JPanel panel = new JPanel();

    public ActionMenu(MenuForm menuForm) {
        this.menuForm = menuForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setLayout(new BorderLayout());
        switch (e.getActionCommand().toString()) {
            case "Dashboard":
                HeaderForm.getInstance().setPanelNow("DashBoard");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderForm.getInstance(), BorderLayout.NORTH);
                panel.add(DashboardPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List Book":
                panel.setLayout(new BorderLayout());
                HeaderForm.getInstance().setPanelNow("BookList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderForm.getInstance(), BorderLayout.NORTH);
                panel.add(BrowseProductPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List User":
                HeaderForm.getInstance().setPanelNow("UserList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderForm.getInstance(), BorderLayout.NORTH);
                panel.add(UserListPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List Order":
                HeaderForm.getInstance().setPanelNow("OrderList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderForm.getInstance(), BorderLayout.NORTH);
                panel.add(OrderListPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

        }
    }

}
