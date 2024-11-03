package com.example.hello_world.Contacts;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.R;

public class ContactsViewHolder extends RecyclerView.ViewHolder{
    public TextView name, phone;
    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.editTextName);
        phone = itemView.findViewById(R.id.editTextPhone);
    }
}
