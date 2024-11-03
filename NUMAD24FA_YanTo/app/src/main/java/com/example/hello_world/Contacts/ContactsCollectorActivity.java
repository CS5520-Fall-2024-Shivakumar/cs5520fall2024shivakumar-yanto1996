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
import java.util.ArrayList;
import java.util.List;

public class ContactsCollectorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private List<ContactsModel> contactsModelList;

    private final ActivityResultLauncher<Intent> addContactLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Retrieve contact data from the intent
                    String name = result.getData().getStringExtra("contact_name");
                    String phone = result.getData().getStringExtra("contact_phone");

                    // Create a new contact and add it to the list
                    ContactsModel contact = new ContactsModel(name, phone);
                    contactsModelList.add(contact);

                    // Notify the adapter to update the RecyclerView
                    contactsAdapter.notifyItemInserted(contactsModelList.size() - 1);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_collector);

        recyclerView = findViewById(R.id.recyclerView);
        contactsModelList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(this, contactsModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> {
            // Launch AddContactsActivity without request code
            Intent addContactIntent = new Intent(ContactsCollectorActivity.this, AddContactsActivity.class);
            addContactLauncher.launch(addContactIntent);
        });
    }
}
