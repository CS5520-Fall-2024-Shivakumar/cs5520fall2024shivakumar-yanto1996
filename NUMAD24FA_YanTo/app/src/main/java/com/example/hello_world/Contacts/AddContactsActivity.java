package com.example.hello_world.Contacts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.R;

public class AddContactsActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText phoneEdit;
    private Button doneButton;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        nameEdit = findViewById(R.id.editTextName);
        phoneEdit = findViewById(R.id.editTextPhone);
        doneButton = findViewById(R.id.doneButton);

        // Retrieve the position passed from the intent
        position = getIntent().getIntExtra("contact_position", -1);

        // populate current info sent by adapter when updating
        if (position != -1) {
            nameEdit.setText(getIntent().getStringExtra("contact_name"));
            phoneEdit.setText(getIntent().getStringExtra("contact_phone"));
        }

        // Disable the button until fields are filled
        setButtonEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateState();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        nameEdit.addTextChangedListener(textWatcher);
        phoneEdit.addTextChangedListener(textWatcher);

        doneButton.setOnClickListener(v -> {
            String name = nameEdit.getText().toString();
            String phone = phoneEdit.getText().toString();
            Log.d("AddContactsActivity", "Name: " + name + ", Phone: " + phone + ", Position: " + position);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("contact_name", name);
            resultIntent.putExtra("contact_phone", phone);
            resultIntent.putExtra("contact_position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void setButtonEnabled(boolean enabled) {
        doneButton.setEnabled(enabled);
        if (enabled) {
            doneButton.setTextColor(Color.BLUE); // Blue for enabled
        } else {
            doneButton.setTextColor(Color.LTGRAY); // Gray for disabled
        }
    }

    // Method to check if both fields are filled
    private void updateState() {
        String name = nameEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();
        setButtonEnabled(!name.isEmpty() && !phone.isEmpty());
    }
}
