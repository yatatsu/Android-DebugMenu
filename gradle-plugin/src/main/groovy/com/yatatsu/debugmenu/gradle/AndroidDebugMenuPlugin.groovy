package com.yatatsu.debugmenu.gradle

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

public class AndroidDebugMenuPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    def isAndroidApp = project.plugins.withType(AppPlugin)
    def isAndroidLib = project.plugins.withType(LibraryPlugin)
    if (!isAndroidApp && !isAndroidLib) {
      throw new GradleException("'com.android.application' or 'com.android.library' plugin required.")
    }

    final def log = project.logger
    final def variants
    if (hasApp) {
      variants = project.android.applicationVariants
    } else {
      variants = project.android.libraryVariants
    }
  }
}