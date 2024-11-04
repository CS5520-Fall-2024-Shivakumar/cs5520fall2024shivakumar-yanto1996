package com.example.hello_world.Contacts;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hello_world.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class ContactsCollectorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<ContactsModel> contactsModelList;

    private final ActivityResultLauncher<Intent> contactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Retrieve contact data from the intent
                    String name = result.getData().getStringExtra("contact_name");
                    String phone = result.getData().getStringExtra("contact_phone");
                    int position = result.getData().getIntExtra("contact_position", -1);

                    if (position == -1) {
                        // Create a new contact and add it to the list
                        ContactsModel newContact = new ContactsModel(name, phone);
                        contactsModelList.add(newContact);

                        // Notify the adapter to update the RecyclerView
                        contactsAdapter.notifyItemInserted(contactsModelList.size() - 1);


                        Snackbar snackbar = Snackbar.make(recyclerView, "Contact added", Snackbar.LENGTH_LONG);
                        snackbar.setAction("Undo", view -> {
                            // Action to perform on Undo
                            int index = contactsModelList.size() - 1;
                            contactsModelList.remove(index); // Remove last added contact
                            contactsAdapter.notifyItemRemoved(index);
                        });
                        snackbar.show();
                    } else {
                        // Update the existing contact in the list
                        ContactsModel updatedContact = new ContactsModel(name, phone);
                        contactsModelList.set(position, updatedContact);

                        // Notify the adapter to update the RecyclerView at the correct position
                        contactsAdapter.notifyItemChanged(position);
                        Snackbar.make(recyclerView, "Contact updated", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_collector);

        recyclerView = findViewById(R.id.recyclerView);
        contactsModelList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(this, contactsModelList, contactLauncher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
            Intent addContactIntent = new Intent(ContactsCollectorActivity.this, AddContactsActivity.class);
            contactLauncher.launch(addContactIntent);
        });
    }
}