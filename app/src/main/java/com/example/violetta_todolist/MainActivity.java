package com.example.violetta_todolist;

import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mTaskListView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        mAdapter = new ArrayAdapter<String>(this,
                R.layout.todo_item,
                R.id.task_title,
                taskList);
        mTaskListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do, Violetta?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                addItem(task);
                                Log.d("TAG", "Task to add" + task);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTask(View view){
        TextView textView = view.getRootView().findViewById(R.id.task_title);
        String task = textView.getText().toString();
        removeItem(task);
    }

    private void addItem(String itemText){
        taskList.add(itemText);
        mAdapter.notifyDataSetChanged();
    }

    private void removeItem(String itemText){
        taskList.remove(itemText);
        mAdapter.notifyDataSetChanged();
    }



}
