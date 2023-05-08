package com.bookstore.gui.forms.providers;

import com.bookstore.interfaces.IFindModelForm;
import com.bookstore.models.ProviderModel;
import javax.swing.JPanel;

public class FindProvider
  extends JPanel
  implements IFindModelForm<ProviderModel> {

  public FindProvider() {
    initComponents();
  }

  private void initComponents() {}

  @Override
  public ProviderModel find() {
    // return a ProviderModel object
    return null;
  }
}
