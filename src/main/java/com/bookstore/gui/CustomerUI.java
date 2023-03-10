package com.bookstore.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CustomerUI extends JFrame implements MouseListener {
  private JPanel groupHeader, groupContent, iconMenuBar, buttonMenuBar;

  private JButton jButtonHome, jButtonCart, jButtonHistory, jButtonProfile, jButtonNotification, jButtonHelp,
      jButtonLogout; // cac button cua menu

  private JLabel jLabelHomeIcon, jLabelCartIcon, jLabelNotifiIcon, jLabelHistoryIcon, jLabelProfileIcon, jLabelHelpIcon,
      jLabelLogoutIcon;

  private JPanel contentHome, contentCart, contentHistory, contentNotifi, contentProfile;

  public CustomerUI() {
    setTitle("Bookstore Management App");
    setPreferredSize(new Dimension(1200, 650));
    setBackground(Color.WHITE);
    initFrame();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    pack();
  }

  public void initHeader() {
    groupHeader = new JPanel();
    groupHeader.setPreferredSize(new Dimension(1200, 100));
    groupHeader.setBackground(Color.BLUE);
  }

  public void initIconMenuBar() {
    iconMenuBar = new JPanel();
    iconMenuBar.addMouseListener(this);
    iconMenuBar.setPreferredSize(new Dimension(40, 200));
    iconMenuBar.setBackground(Color.WHITE);

    jLabelHomeIcon = new JLabel();
    jLabelHomeIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelNotifiIcon = new JLabel();
    jLabelNotifiIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelCartIcon = new JLabel();
    jLabelCartIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelHistoryIcon = new JLabel();
    jLabelHistoryIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelProfileIcon = new JLabel();
    jLabelProfileIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelHelpIcon = new JLabel();
    jLabelHelpIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    jLabelLogoutIcon = new JLabel();
    jLabelLogoutIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    jLabelHomeIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/home.png")));
    jLabelNotifiIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/notif.png")));
    jLabelCartIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/cart.png")));
    jLabelHistoryIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/history.png")));
    jLabelProfileIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/profile.png")));
    jLabelHelpIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/help.png")));
    jLabelLogoutIcon.setIcon(new ImageIcon(getClass().getResource("/resources/image/logout.png")));

    // iconMenuBar.setLayout(new BorderLayout());
    iconMenuBar.add(jLabelHomeIcon);
    iconMenuBar.add(jLabelNotifiIcon);
    iconMenuBar.add(jLabelCartIcon);
    iconMenuBar.add(jLabelHistoryIcon);
    iconMenuBar.add(jLabelProfileIcon);
    iconMenuBar.add(jLabelHelpIcon);
    iconMenuBar.add(jLabelLogoutIcon);

    iconMenuBar.setLayout(new GridLayout(7, 1, 0, 0));
    pack();
  }

  public void initButtonMenuBar() {
    buttonMenuBar = new JPanel();
    buttonMenuBar.setBackground(Color.WHITE);

    Font font = new Font("sansserif", Font.BOLD, 12);

    jButtonHome = new JButton("Trang Ch???");
    jButtonHome.setFont(font);
    jButtonHome.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonHome);
    jButtonHome.addActionListener(e -> {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardhome");
    });

    jButtonNotification = new JButton("Th??ng B??o");
    jButtonNotification.setFont(font);
    jButtonNotification.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonNotification);
    jButtonNotification.addActionListener(e -> {
      CardLayout c2Layout = (CardLayout) groupContent.getLayout();
      c2Layout.show(groupContent, "cardnotifi");
    });

    jButtonCart = new JButton("Gi??? H??ng");
    jButtonCart.setFont(font);
    jButtonCart.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonCart);
    jButtonCart.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        CartUI cart = new CartUI();
        groupContent.add(cart, "cartcart");
        CardLayout c1Layout = (CardLayout) groupContent.getLayout();
        c1Layout.show(groupContent, "cardcart");
      }
    });

    jButtonHistory = new JButton("L???ch S???");
    jButtonHistory.setFont(font);
    jButtonHistory.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonHistory);

    jButtonProfile = new JButton("T??i Kho???n");
    jButtonProfile.setFont(font);
    jButtonProfile.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonProfile);

    jButtonHelp = new JButton("Tr??? Gi??p");
    jButtonHelp.setFont(font);
    jButtonHelp.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonHelp);

    jButtonLogout = new JButton("????ng Xu???t");
    jButtonLogout.setFont(font);
    jButtonLogout.setBackground(Color.WHITE);
    buttonMenuBar.add(jButtonLogout);

    buttonMenuBar.setLayout(new GridLayout(7, 1));

  }

  public void initContent() {
    groupContent = new JPanel();
    groupContent.setBackground(Color.gray);
    groupContent.setPreferredSize(new Dimension(1160, 550));
    groupContent.setLayout(new CardLayout());
    Font font = new Font("sansserif", Font.BOLD, 12);

    contentHome = new JPanel();
    contentHome.setLayout(new BorderLayout());
    JTextField jTextFieldHome = new JTextField("Home");
    jTextFieldHome.setFont(font);
    contentHome.add(jTextFieldHome, BorderLayout.CENTER);

    contentNotifi = new JPanel();
    contentNotifi.setLayout(new BorderLayout());
    JTextField jTextFielNotifi = new JTextField("thong bao");
    jTextFielNotifi.setFont(font);
    contentNotifi.add(jTextFielNotifi, BorderLayout.CENTER);

    groupContent.add(contentHome, "cardhome");
    groupContent.add(contentNotifi, "cardnotifi");

  }

  public void initFrame() {
    initButtonMenuBar();
    initHeader();
    initIconMenuBar();
    initContent();
    setLayout(new BorderLayout());
    add(groupHeader, BorderLayout.NORTH);
    add(iconMenuBar, BorderLayout.WEST);
    add(groupContent, BorderLayout.EAST);
    buttonMenuBar.setVisible(false);
  }

  public static void main(String[] args) {
    new CustomerUI().setLocationRelativeTo(null);
  }

  private MouseAdapter outsideClickListener;

  private void openMenuBar() {
    new Thread(() -> {
      for (int i = 0; i < getParent().getWidth(); i++) {
        buttonMenuBar.setSize(i, 550);
        try {
          Thread.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      outsideClickListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (!buttonMenuBar.getBounds().contains(e.getPoint())) {
            closeMenuBar();
            removeMouseListener(outsideClickListener);
          }
        }
      };
      addMouseListener(outsideClickListener);
    }).start();
  }

  public void closeMenuBar() {
    new Thread(() -> {
      for (int i = getParent().getWidth(); i > 0; i--) {
        buttonMenuBar.setSize(i, 550);
        try {
          Thread.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (!buttonMenuBar.isVisible()) {
      initButtonMenuBar();
      groupContent.setPreferredSize(new Dimension(1030, 550));
      this.add(buttonMenuBar, BorderLayout.CENTER);
      this.setVisible(true);
      openMenuBar();
    } else {
      closeMenuBar();
      groupContent.setPreferredSize(new Dimension(1160, 550));
      buttonMenuBar.setVisible(false);
      this.removeMouseListener(outsideClickListener); // Remove listener after closing menu bar
      this.setVisible(true);
    }

  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getSource() == jButtonHome) {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardhome");
    }

    if (e.getSource() == jButtonCart) {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardcart");
    }

    if (e.getSource() == jButtonHistory) {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardhistory");
    }

    if (e.getSource() == jButtonProfile) {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardprofile");
    }

    if (e.getSource() == jButtonNotification) {
      CardLayout c1Layout = (CardLayout) groupContent.getLayout();
      c1Layout.show(groupContent, "cardnotification");
    }
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
  }

}
