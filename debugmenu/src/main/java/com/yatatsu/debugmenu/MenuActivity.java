package com.yatatsu.debugmenu;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
      listView.setOnItemClickListener((parent, view, position, id) -> {
        DebugMenuItem item = AndroidDebugMenu.getInstance().getDebugMenuItems().get(position);
        item.invoke(MenuActivity.this);
      });
    }
  }

  private static class MenuAdapter extends ArrayAdapter<DebugMenuItem> {

    MenuAdapter(Context context, List<DebugMenuItem> debugMenuItems) {
      super(context, android.R.layout.simple_list_item_1);
      addAll(debugMenuItems);
    }

    @NonNull
    @Override public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view = super.getView(position, convertView, parent);
      DebugMenuItem item = getItem(position);
      if (item != null) {
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(item.getName());
      }
      return view;
    }
  }
}
