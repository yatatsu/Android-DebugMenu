package com.yatatsu.debugmenu;

import android.content.Context;
import androidx.annotation.NonNull;

public interface DebugMenuItem {

  /**
   * Invoke debug menu
   *
   * @param context context
   */
  void invoke(@NonNull Context context);

  /**
   * menu name
   *
   * @return name for menu
   */
  @NonNull String getName();
}
