package com.virb3.numberfactor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle("Result");
        ((TextView) findViewById(R.id.txtResult)).setText(getIntent().getExtras().getString("Result"));
    }
}
