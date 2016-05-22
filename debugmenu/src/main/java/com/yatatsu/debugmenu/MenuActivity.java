package com.yatatsu.debugmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    ListView listView = (ListView) findViewById(R.id.list_view);
    if (listView != null) {
      ArrayAdapter<DebugMenuItem> adapter =
          new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
      adapter.addAll(AndroidDebugMenu.getInstance().getDebugMenuItems());
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          DebugMenuItem item = AndroidDebugMenu.getInstance().getDebugMenuItems().get(position);
          item.invoke(MenuActivity.this);
        }
      });
    }
  }
}
