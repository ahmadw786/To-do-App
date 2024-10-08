package com.example.todoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class TaskInputFragment extends DialogFragment {
    private EditText taskEditText;
    private EditText taskDescriptionEditText;
    private OnTaskAddedListener listener;

    public interface OnTaskAddedListener {
        void onTaskAdded(String task, String description);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskAddedListener) {
            listener = (OnTaskAddedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnTaskAddedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_task_input, null);
        taskEditText = view.findViewById(R.id.task_edit_text);
        taskDescriptionEditText = view.findViewById(R.id.task_description_edit_text);

        builder.setView(view)
                .setTitle("Add New Task")
                .setPositiveButton("Save", (dialog, id) -> {
                    String task = taskEditText.getText().toString().trim();
                    String description = taskDescriptionEditText.getText().toString().trim();

                    if (!task.isEmpty() && listener != null) {
                        listener.onTaskAdded(task, description);
                    } else {
                        Toast.makeText(getActivity(), "Task name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());

        return builder.create();
    }
}
