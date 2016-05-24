package com.yatatsu.debugmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    ListView listView = (ListView) findViewById(R.id.list_view);
    if (listView != null) {
      MenuAdapter adapter =
          new MenuAdapter(this, AndroidDebugMenu.getInstance().getDebugMenuItems());
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          DebugMenuItem item = AndroidDebugMenu.getInstance().getDebugMenuItems().get(position);
          item.invoke(MenuActivity.this);
        }
      });
    }
  }

  private class MenuAdapter extends ArrayAdapter<DebugMenuItem> {

    public MenuAdapter(Context context, List<DebugMenuItem> debugMenuItems) {
      super(context, android.R.layout.simple_list_item_1);
      addAll(debugMenuItems);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      View view = super.getView(position, convertView, parent);
      ((TextView) view.findViewById(android.R.id.text1)).setText(getItem(position).getName());
      return view;
    }
  }
}
