package com.example.androidlifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class intent extends AppCompatActivity {

    TextView textView;
    Button btnLinearLayout;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        textView = findViewById(R.id.editText);
        final Intent intent = getIntent();
        str = intent.getStringExtra("message_key");
        textView.setText(str);

        btnLinearLayout = findViewById(R.id.btnLinearLayout);
        btnLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(intent.this, linearLayout.class);
                startActivity(nextPage);
            }
        });
    }
}