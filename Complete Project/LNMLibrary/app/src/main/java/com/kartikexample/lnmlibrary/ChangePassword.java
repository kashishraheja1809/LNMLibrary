package com.kartikexample.lnmlibrary;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ChangePassword extends AppCompatActivity {

    EditText old_pass,new_pass,conf_pass;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        old_pass = (EditText)findViewById(R.id.reqpass_currenttext);
        new_pass = (EditText)findViewById(R.id.reqpass_newtext);
        conf_pass = (EditText)findViewById(R.id.reqpass_confirmtext);
        confirm = (Button)findViewById(R.id.confirmbutton);
        final SQLiteDatabase mydatabase = openOrCreateDatabase("Library", MODE_PRIVATE, null);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Kartik";

                String old_pass_txt = old_pass.getText().toString();
                final String new_pass_txt = new_pass.getText().toString();
                final String con_new_pass_txt = conf_pass.getText().toString();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("libUsers");
                query.whereEqualTo("userPassword", old_pass_txt);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (object == null) {

                            Context context = getApplicationContext();
                            CharSequence text = "Invalid old password";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            if(new_pass_txt.equals(con_new_pass_txt)) {
                                object.put("userPassword", new_pass_txt);

                                Context context = getApplicationContext();
                                CharSequence text = "Password changed success";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            } else {

                                Context context = getApplicationContext();
                                CharSequence text = "Passwords do not match";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
