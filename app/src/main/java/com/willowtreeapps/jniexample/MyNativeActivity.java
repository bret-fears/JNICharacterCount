package com.willowtreeapps.jniexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MyNativeActivity extends AppCompatActivity {

    static {
        System.loadLibrary("mylib");
    }

    protected EditText inputTextEdit = null;
    protected TextView charCountTv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_native);

        inputTextEdit = (EditText) findViewById(R.id.input_text);
        charCountTv = (TextView) findViewById(R.id.sample_text);

        inputTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateWordCounter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // one off call to generate a count initially
        updateWordCounter("");
    }

    private native void updateWordCounter(String s);

}
