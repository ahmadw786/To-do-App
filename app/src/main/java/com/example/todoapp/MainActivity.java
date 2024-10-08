package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskInputFragment.OnTaskAddedListener {
    private ListView taskListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListView = findViewById(R.id.task_list);
        tasks = new ArrayList<>();

        // Use the built-in layout for the ArrayAdapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);

        FloatingActionButton addButton = findViewById(R.id.add_task_button);
        addButton.setOnClickListener(v -> {
            TaskInputFragment fragment = new TaskInputFragment();
            fragment.show(getSupportFragmentManager(), "TaskInputFragment");
        });
    }

    @Override
    public void onTaskAdded(String task, String description) {
        String taskWithDescription = task + " - " + description; // Combine task and description
        tasks.add(taskWithDescription); // Add to tasks list
        adapter.notifyDataSetChanged(); // Notify adapter of data change
    }
}
