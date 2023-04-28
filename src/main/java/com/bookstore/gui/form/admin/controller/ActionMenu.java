package com.bookstore.gui.form.admin.controller;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.JPanel;

import com.bookstore.gui.component.book.BrowseProductPanel;
import com.bookstore.gui.component.header.HeaderAdmin;
import com.bookstore.gui.component.menu.MenuAdmin;
import com.bookstore.gui.form.admin.RunForm;
import com.bookstore.gui.form.admin.component.dashboard.DashboardPanel;
import com.bookstore.gui.form.admin.component.order.OrderListPanel;
import com.bookstore.gui.form.admin.component.user.UserListPanel;

public class ActionMenu implements ActionListener {

    private MenuAdmin menuForm;
    private JPanel panel = new JPanel();

    public ActionMenu(MenuAdmin menuForm) {
        this.menuForm = menuForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setLayout(new BorderLayout());
        switch (e.getActionCommand().toString()) {
            case "Dashboard":
                HeaderAdmin.getInstance().setPanelNow("DashBoard");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
                panel.add(DashboardPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List Book":
                panel.setLayout(new BorderLayout());
                HeaderAdmin.getInstance().setPanelNow("BookList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
                panel.add(BrowseProductPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List User":
                HeaderAdmin.getInstance().setPanelNow("UserList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
                panel.add(UserListPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

            case "List Order":
                HeaderAdmin.getInstance().setPanelNow("OrderList");
                RunForm.getConstruct().removeAll();
                panel.removeAll();
                panel.add(HeaderAdmin.getInstance(), BorderLayout.NORTH);
                panel.add(OrderListPanel.getInstance(), BorderLayout.CENTER);
                RunForm.getConstruct().add(panel);
                RunForm.getConstruct().revalidate();
                RunForm.getConstruct().repaint();
                break;

        }
    }

}
