package com.example.hello_world.Contacts;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hello_world.R;

public class AddContactsActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText phoneEdit;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        nameEdit = findViewById(R.id.editTextName);
        phoneEdit = findViewById(R.id.editTextPhone);
        doneButton = findViewById(R.id.doneButton);

        // disable the button until fields are filled
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

            Intent resultIntent = new Intent();
            resultIntent.putExtra("contact_name", name);
            resultIntent.putExtra("contact_phone", phone);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void setButtonEnabled(boolean enabled) {
        doneButton.setEnabled(enabled);
        if (enabled) {
            doneButton.setTextColor(Color.BLUE); // blue for enable
        } else {
            doneButton.setTextColor(Color.LTGRAY); // gray for disable
        }
    }

    // Method to check if both fields are filled
    private void updateState() {
        String name = nameEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();
        setButtonEnabled(!name.isEmpty() && !phone.isEmpty());
    }
}
