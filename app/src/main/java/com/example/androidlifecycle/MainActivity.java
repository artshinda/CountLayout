package com.example.androidlifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String nameShared;
    public static String value;
    public static final String resultText = "resultText";
    int count;

    EditText editName ;
    Button btnNext, btnCount, btnLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
//        editName.setText(count);
        btnCount = findViewById(R.id.btnCount);
        btnNext  = findViewById(R.id.btnNext);
        btnLayout = findViewById(R.id.btnLayout);

        //Menyimpan Value editEmail
        sharedPreferences = getSharedPreferences(nameShared, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;//increment the count
                editName.setText("No.of Clicks "+ count);// view in the text
            }
        });

        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(MainActivity.this, linearLayout.class);
                startActivity(nextPage);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editName.getText().toString();
                Intent nextPage = new Intent(MainActivity.this, intent.class);
                nextPage.putExtra("message_key", str);
                String getIntentValue = nextPage.getStringExtra("nilaiEditText");
                startActivity(nextPage);
            }
        });

        // Untuk unfocus keyboard ketika touch ke hal lain
        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = MainActivity.this.getCurrentFocus();

                if (focusedView != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                return true;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            count = savedInstanceState.getInt("count");
            Log.d("nilai", savedInstanceState.getString("nilai"));
            Log.d("count", String.valueOf(savedInstanceState.getInt("count")));

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putString("nilai","hai");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart Invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        editName.setText(nameShared);
        Log.d("lifecycle", "onResume Invoked");
    }


    @Override
    protected void onPause() {
        super.onPause();
        nameShared = editName.getText().toString();
        editor      = sharedPreferences.edit();
        editor.putString(resultText, nameShared);
        editor.commit();
        value       = sharedPreferences.getString(resultText,"");
        Log.d("lifecycle", "onPause Invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop Invoked");
    }

}