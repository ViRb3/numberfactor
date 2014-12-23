package com.virb3.numberfactor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, View.OnLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("NumberFactor ~ViR");

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnDel).setOnClickListener(this);
        findViewById(R.id.btnDel).setOnLongClickListener(this);
        findViewById(R.id.btnFactor).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        TextView textView = (TextView) findViewById(R.id.textView);

        if (button.getText().toString().toUpperCase().equals("DEL")) {
            if (textView.getText().toString().equals(" "))
                return;

            if (textView.getText().toString().trim().length() == 1)
                textView.setText(" ");
            else {
                textView.setText(textView.getText().toString().trim());
                textView.setText(textView.getText().toString().substring(0, textView.getText().length() - 1));
            }
        }

        else if (button.getText().toString().toUpperCase().equals("FAC")) {
            if (textView.getText().toString().equals(" "))
                return;

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Result", FactorNumber(Long.parseLong(textView.getText().toString())));
            startActivity(intent);
        }
        else {
            textView.setText(textView.getText().toString().trim() + button.getText().toString());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        TextView textView = (TextView) findViewById(R.id.textView);

        if (!textView.getText().toString().equals(" "))
            textView.setText(" ");

        return true;
    }

    private String FactorNumber(long number) {
        List<String> result = new ArrayList<>();

        result.add(String.format("%s =", number));

        int numberCase = 3;

        if (Long.toString(number).charAt(Long.toString(number).length() - 1) % 2 == 0)
            numberCase--;

        for(int i = numberCase; i <= number; i += 2) {
            int repeats = 0;

            while (number % i == 0) {
                repeats++;
                number /= i;
            }

            if (repeats > 0) {
                String line = String.format("%s^%s", i, repeats);
                line = String.format("%5s", line);
                line = String.format("%s %25s", line, "*");
                result.add(line);
            }
        }

        if (number != 1)
        {
            String line = String.format("%s^%s", number, 1);
            line = String.format("%5s", line);
            line = String.format("%s %25s", line, "*");
            result.add(line);
        }

        result.set(result.size() - 1, result.get(result.size() - 1).substring(0, result.get(result.size() - 1).length() - 4));

        for (int i = 0; i < result.size() - 1; i++)
            result.set(i, result.get(i) + String.format("%n"));

        String output = "";

        for (String line : result)
            output += line;

        return output;
    }
}
