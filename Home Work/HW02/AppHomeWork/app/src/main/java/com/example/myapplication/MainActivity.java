package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    private Button btnExit;
    private EditText txtMsg;
    private TextView txtSpy;
    private LinearLayout mainScreen;
    private String PREFNAME = "myPrefFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(this,"On Create", Toast.LENGTH_SHORT).show();

        btnExit = (Button)findViewById(R.id.btnExit);
        txtMsg  = (EditText)findViewById(R.id.txtMsg);
        txtSpy = (TextView)findViewById(R.id.txtSpy);
        mainScreen = (LinearLayout)findViewById(R.id.mainScreen);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String Color = s.toString().toLowerCase(Locale.US);
                txtSpy.setText(Color);
                setBackgroundColor(Color,mainScreen);
            }
        });
    }
    private void setBackgroundColor(String Color, LinearLayout myScreen) {
        if (Color.equals("red"))
            myScreen.setBackgroundColor(0xffff0000);
        if (Color.equals("green"))
            myScreen.setBackgroundColor(0xff00ff00);
        if (Color.equals("blue"))
            myScreen.setBackgroundColor(0xff0000ff);
        if (Color.equals("white"))
            myScreen.setBackgroundColor(0xffffffff);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMeUsingSavedStateData();
        Toast.makeText(this,"On Start", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"On Resume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveStateData(txtSpy.getText().toString());
        Toast.makeText(this,"On Pause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"On Stop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"On Destroy", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void  onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(this,"On Restore Instance State", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(this,"On Save Instance State", Toast.LENGTH_SHORT).show();
    }
    private void saveStateData(String chosenColor) {
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosenBackgroundColor", value = txtSpy.getText().toString();
        myPrefEditor.putString(key, value);
        myPrefEditor.commit();
    }
    private void updateMeUsingSavedStateData() {

        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String key = "chosenBackgroundColor";
        String defaultValue = "black";
        if (( myPrefContainer != null ) && myPrefContainer.contains(key)){
            String color = myPrefContainer.getString(key, defaultValue);
            Toast.makeText(this,color, Toast.LENGTH_SHORT).show();
            setBackgroundColor(color, mainScreen);
        }
    }
}