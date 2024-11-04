package com.example.hello_world.Contacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private Context context;
    private List<ContactsModel> list;

    private ActivityResultLauncher<Intent> contactLauncher;

    public ContactsAdapter(Context context, List<ContactsModel> list, ActivityResultLauncher<Intent> contactLauncher) {
        this.context = context;
        this.list = list;
        this.contactLauncher = contactLauncher;
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
        holder.phone.setText(currentContact.getPhoneNumber());

        holder.itemView.setOnClickListener(v -> {
            Intent callPerson = new Intent(Intent.ACTION_DIAL);
            callPerson.setData(Uri.parse("tel:" + currentContact.getPhoneNumber()));
            context.startActivity(callPerson);
        });

        holder.editButton.setOnClickListener(v -> {
            Intent edit = new Intent(context, AddContactsActivity.class);
            // send existing data to the add contact activity
            edit.putExtra("contact_name", currentContact.getName());
            edit.putExtra("contact_phone", currentContact.getPhoneNumber());
            edit.putExtra("contact_position", position);
            contactLauncher.launch(edit);
        });

        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Contact")
                    .setMessage("Are you sure you want to delete this contact?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
