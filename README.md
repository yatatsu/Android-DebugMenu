# Android-DebugMenu
[![Build Status](https://travis-ci.org/yatatsu/Android-DebugMenu.svg?branch=master)](https://travis-ci.org/yatatsu/Android-DebugMenu)
[![](https://jitpack.io/v/yatatsu/android-debugmenu.svg)](https://jitpack.io/#yatatsu/android-debugmenu)

You can create shortcut menu easily, and access through notification.

## Usage

In your application's `onCreate()`,

```java
public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();

    AndroidDebugMenu.initialize(
        new AndroidDebugMenu.ConfigurationBuilder(this)
            // Add menu
            .addDebugMenuItem(new DebugMenuItem() {
              @Override public void invoke(Context context) {
                Log.d("AndroidDebugMenu", "debug");
              }

              @Override public String getName() {
                return "JUST LOGGING";
              }
            })
            // Add menu
            .addDebugMenuItem(new FireNotificationMenu())
            // Specify notification id for avoiding collision
            .notificationId(99).build());
  }
}
```

In your

For more, see example project.

## Download

Download from [JitPack](https://jitpack.io).

```groovy
dependencies {
  debugCompile 'com.github.yatatsu.android-debugmenu:debugmenu:{latest-version}'
  releaseCompile 'com.github.yatatsu.android-debugmenu:debugmenu-noop:{latest-version}'
}
```

## License

```
Copyright 2016 KITAGAWA, Tatsuya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```