package com.example.hello_world.Contacts;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.R;

public class ContactsViewHolder extends RecyclerView.ViewHolder{
    public TextView name, phone;
    public ImageButton deleteButton;
    public ImageButton editButton;
    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_view_name);
        phone = itemView.findViewById(R.id.text_view_phone);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        editButton = itemView.findViewById(R.id.editButton);
    }
}
