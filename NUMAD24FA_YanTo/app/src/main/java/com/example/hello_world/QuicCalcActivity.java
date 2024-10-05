package com.example.hello_world;

import android.os.Bundle;
import android.view.View;
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
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnadd, btnminus, btnequals, btndel;

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
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnadd = findViewById(R.id.btnadd);
        btnminus = findViewById(R.id.btnsubtract);
        btndel = findViewById(R.id.btnx);
        btnequals = findViewById(R.id.btnequals);

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
    }

    public void display() {
        calcDisplay.setText(curr);
    }

    public void delete() {
        if (!curr.isEmpty()) {
            curr = curr.substring(0, curr.length() - 1);
        }
    }
}
