package com.kartikexample.lnmlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.kartikexample.lnmlibrary.dummy.DummyContent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class TransactionActivity extends AppCompatActivity implements TransactionListFragment.OnFragmentInteractionListener {

    TextView username,userCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        username = (TextView)findViewById(R.id.th_useridtext);
        userCategory = (TextView)findViewById(R.id.th_usercategorytext);
        username.setText(LoginActivity.getUseridinput());
        userCategory.setText(LoginActivity.getInput());

        DummyContent.ITEMS_TRANSAC.clear();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("libIssued");
        query.whereContains("issUserId", LoginActivity.getUseridinput());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> object, ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                    Context context = getApplicationContext();
                    CharSequence text = "Error Occured";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Log.d("score", "Retrieved the object.");

                    for (ParseObject pq : object) {
                        String temp = "Book Title: " + pq.getString("bookTitle") + "\tAccession No.: " + pq.getString("issAccNo") + "\tIssue Date: "+pq.getDate("issWhen").toString()+"\tReturn Date: "+pq.getDate("issTill").toString();
                        DummyContent.addItem_Transaction(temp);
                    }

                    //result_book.setText(object.getString("bookTitle"));
                    //result_book.setText(object.getString("bookAuther"));


                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
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

    @Override
    public void onFragmentInteraction(String id) {

    }
}
