package com.example.hello_world;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuicCalcActivity extends AppCompatActivity {

    private TextView calcDisplay;
    private String curr;

    // Track if an operator has been added
    private boolean operator_insert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quic_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calcDisplay = findViewById(R.id.calc_display);
        curr = "";
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnadd = findViewById(R.id.btnadd);
        Button btnminus = findViewById(R.id.btnsubtract);
        Button btndel = findViewById(R.id.btnx);
        Button btnequals = findViewById(R.id.btnequals);

        btn0.setOnClickListener(v -> {
            curr += "0";
            display();
        });

        btn1.setOnClickListener(v -> {
            curr += "1";
            display();
        });

        btn2.setOnClickListener(v -> {
            curr += "2";
            display();
        });

        btn3.setOnClickListener(v -> {
            curr += "3";
            display();
        });

        btn4.setOnClickListener(v -> {
            curr += "4";
            display();
        });

        btn5.setOnClickListener(v -> {
            curr += "5";
            display();
        });

        btn6.setOnClickListener(v -> {
            curr += "6";
            display();
        });

        btn7.setOnClickListener(v -> {
            curr += "7";
            display();
        });

        btn8.setOnClickListener(v -> {
            curr += "8";
            display();
        });

        btn9.setOnClickListener(v -> {
            curr += "9";
            display();
        });

        btndel.setOnClickListener(v -> {
            delete();
            display();
        });

        btnadd.setOnClickListener(v -> {
            if (!curr.isEmpty()) {
                if (!operator_insert) {
                    curr += " + ";
                    display();
                    operator_insert = true;
                }
            }
        });

        btnminus.setOnClickListener(v -> {
            if (!curr.isEmpty()) {
                if (!operator_insert) {
                    curr += " - ";
                    display();
                    operator_insert = true;
                }
            }
        });

        btnequals.setOnClickListener(v -> {
            // checks if operator flag is raised and if last char is not empty
            if (operator_insert && !curr.substring(curr.length() - 1).isEmpty()) {
                // splits the curr into 3 buffers starting at index 0
                String[] buffer = curr.split(" ");
                // constraint so that there can only be a number, operator, and another number
                if (buffer.length == 3) {
                    // parse the int from the buffer into an int
                    int num1 = Integer.parseInt(buffer[0]);
                    int num2 = Integer.parseInt(buffer[2]);
                    int result = 0;
                    // switch statement to calc result based on operator
                    switch (buffer[1].charAt(0)) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                    }
                    // convert the int back to string
                    curr = Integer.toString(result);
                    operator_insert = false;
                    display();
                }
            }
        });
    }

    public void display() {
        calcDisplay.setText(curr);
    }

    public void delete() {
        // check if the current calc string is not empty
        if (!curr.isEmpty()) {
            // check if the last char is an operator
            // delete operator and the 2 spaces and reset flag
            if (curr.endsWith(" + ") || curr.endsWith(" - ")) {
                curr = curr.substring(0, curr.length() - 3);
                operator_insert = false;
            }
            // else delete last char
            else {
                curr = curr.substring(0, curr.length() - 1);
            }
            display();
        }
    }
}