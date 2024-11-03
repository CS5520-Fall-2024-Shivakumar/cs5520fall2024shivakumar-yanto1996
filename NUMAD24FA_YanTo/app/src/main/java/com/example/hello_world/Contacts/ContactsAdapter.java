package com.example.hello_world.Contacts;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder>{
    private Context context;
    private List<ContactsModel> list;

    public ContactsAdapter(Context context, List<ContactsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_display,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.phone.setText(String.valueOf(list.get(position).getPhoneNumber()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
