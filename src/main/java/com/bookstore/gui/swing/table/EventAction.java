package com.bookstore.gui.swing.table;

import com.bookstore.gui.model.ModelStudent;

public interface EventAction {

  public void delete(ModelStudent student);

  public void update(ModelStudent student);
}
