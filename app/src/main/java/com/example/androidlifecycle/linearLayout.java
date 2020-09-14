package com.example.androidlifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class linearLayout extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String nameShared;
    public static String value;
    public static final String resultText = "resultText";
    int count;

    Button btnCount, btnToast;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        btnCount = findViewById(R.id.btnCount);
        btnToast = findViewById(R.id.btnToast);
        editText = findViewById(R.id.editText);

        //Menyimpan Value editEmail
        sharedPreferences = getSharedPreferences(nameShared, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(linearLayout.this, editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;//increment the count
                editText.setText(""+ count);// view in the text
            }
        });

        // Untuk unfocus keyboard ketika touch ke hal lain
        findViewById(R.id.linearLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                View focusedView = linearLayout.this.getCurrentFocus();

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
        editText.setText(nameShared);
        Log.d("lifecycle", "onResume Invoked");
    }


    @Override
    protected void onPause() {
        super.onPause();
        nameShared  = editText.getText().toString();
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