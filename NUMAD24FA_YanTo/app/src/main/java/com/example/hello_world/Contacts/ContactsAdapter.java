package com.example.hello_world.Contacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private Context context;
    private List<ContactsModel> list;

    public ContactsAdapter(Context context, List<ContactsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_display, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        ContactsModel currentContact = list.get(position);

        holder.name.setText(currentContact.getName());
        holder.phone.setText(String.valueOf(currentContact.getPhoneNumber()));

        holder.itemView.setOnClickListener(v -> {
            Intent callPerson = new Intent(Intent.ACTION_DIAL);
            callPerson.setData(Uri.parse("tel:" + currentContact.getPhoneNumber()));
            context.startActivity(callPerson);
        });

        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Contact")
                    .setMessage("Are you sure you want to delete this contact?")
                    // if yes on the dialog remove
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        list.remove(position);
                        notifyItemRemoved(position);
                        // update list size of recycler view
                        notifyItemRangeChanged(position, list.size());
                    })
                    // do nothing
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
