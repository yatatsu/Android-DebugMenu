package com.yatatsu.debugmenu;

import android.content.Context;

public interface DebugMenuItem {

  /**
   * Invoke debug menu
   *
   * @param context context
   */
  void invoke(Context context);

  /**
   * menu name
   *
   * @return name for menu
   */
  String getName();
}
