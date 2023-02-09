//[C] 2002 Sun Microsystems, Inc.---

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RunSingletonPattern {
  public static void main(String[] arguments) {
    System.out.println("Example for Singleton pattern");
    System.out.println();
    System.out.println("This example will demonstrate the use of");
    System.out.println(" the Singleton pattern by creating two GUI");
    System.out.println(" editors, both of which will reference the");
    System.out.println(" same underlying history list.");

    System.out.println("Creating the first editor");
    System.out.println();
    SingletonGui editor1 = new SingletonGui();
    editor1.createGui();

    System.out.println("Creating the second editor");
    System.out.println();
    SingletonGui editor2 = new SingletonGui();
    editor2.createGui();
  }
}

class SingletonGui implements ActionListener {
  private JFrame mainFrame;

  private JTextArea display;

  private JButton newContact, newAppointment, undo, refresh, exit;

  private JPanel controlPanel, displayPanel;

  private static int historyCount;

  public void createGui() {
    mainFrame = new JFrame("Singleton Pattern Example");
    Container content = mainFrame.getContentPane();
    content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

    displayPanel = new JPanel();
    display = new JTextArea(20, 60);
    display.setEditable(false);
    displayPanel.add(display);
    content.add(displayPanel);

    controlPanel = new JPanel();
    newContact = new JButton("Create contact");
    newAppointment = new JButton("Create appointment");
    undo = new JButton("Undo");
    refresh = new JButton("Refresh");
    exit = new JButton("Exit");
    controlPanel.add(newContact);
    controlPanel.add(newAppointment);
    controlPanel.add(undo);
    controlPanel.add(refresh);
    controlPanel.add(exit);
    content.add(controlPanel);

    newContact.addActionListener(this);
    newAppointment.addActionListener(this);
    undo.addActionListener(this);
    refresh.addActionListener(this);
    exit.addActionListener(this);

    mainFrame.addWindowListener(new WindowCloseManager());
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

  public void refreshDisplay(String actionMessage) {
    display.setText(actionMessage + "\nCOMMAND HISTORY:\n"
        + HistoryList.getInstance().toString());
  }

  public void actionPerformed(ActionEvent evt) {
    Object originator = evt.getSource();
    if (originator == newContact) {
      addCommand(" New Contact");
    } else if (originator == newAppointment) {
      addCommand(" New Appointment");
    } else if (originator == undo) {
      undoCommand();
    } else if (originator == refresh) {
      refreshDisplay("");
    } else if (originator == exit) {
      exitApplication();
    }
  }

  private class WindowCloseManager extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
      exitApplication();
    }
  }

  private void addCommand(String message) {
    HistoryList.getInstance().addCommand((++historyCount) + message);
    refreshDisplay("Add Command: " + message);
  }

  private void undoCommand() {
    Object result = HistoryList.getInstance().undoCommand();
    historyCount--;
    refreshDisplay("Undo Command: " + result);
  }

  private void exitApplication() {
    System.exit(0);
  }
}

class HistoryList {
  private List history = Collections.synchronizedList(new ArrayList());

  private static HistoryList instance = new HistoryList();

  private HistoryList() {
  }

  public static HistoryList getInstance() {
    return instance;
  }

  public void addCommand(String command) {
    history.add(command);
  }

  public Object undoCommand() {
    return history.remove(history.size() - 1);
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < history.size(); i++) {
      result.append("  ");
      result.append(history.get(i));
      result.append("\n");
    }
    return result.toString();
  }
}
