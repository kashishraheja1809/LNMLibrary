package com.kartikexample.lnmlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kartikexample.lnmlibrary.dummy.DummyContent;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements Search_List.OnFragmentInteractionListener {

    public static  ArrayList<String> search_res;
    EditText search_text;
    Button search;
    Spinner search_type;
    String type;
    TextView result_book, result_author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        search_res = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_text = (EditText)findViewById(R.id.editText);
        search = (Button)findViewById(R.id.Searchbutton);
        search_type = (Spinner)findViewById(R.id.Search);
        type = search_type.getSelectedItem().toString();
        result_book = (TextView) findViewById(R.id.result_text_book);
        result_author = (TextView) findViewById(R.id.result_text_author);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("TITLE"))
                {
                    DummyContent.ITEMS.clear();
                    DummyContent.SELECT_ITEMS.clear();
                    /*ParseQuery<ParseObject> query = ParseQuery.getQuery("libBookDet");
                    query.whereEqualTo("booktitle", search_text.getText().toString());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> results, ParseException e) {
                        // results has the list of users with a hometown team with a winning record
                            //System.out.println("Results"+results);
                            Context context1 = getApplicationContext();
                            CharSequence text1 = "Invalid aya";
                            int duration1 = Toast.LENGTH_LONG;

                            Toast toast1 = Toast.makeText(context1, text1, duration1);
                            toast1.show();
                            if(!results.isEmpty()) {
                                Context context = getApplicationContext();
                                CharSequence text = "Invalid user or password";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                            else
                            {
                                Context context = getApplicationContext();
                                CharSequence text = "ni aya";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    });*/

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("libBookDet");
                    query.whereContains("bookTitle", search_text.getText().toString());
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> object, ParseException e) {
                            if (object == null) {
                                Log.d("score", "The getFirst request failed.");
                                Context context = getApplicationContext();
                                CharSequence text = "Book not Found";
                                int duration = Toast.LENGTH_LONG;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            } else {
                                Log.d("score", "Retrieved the object.");

                                for(ParseObject pq:object)
                                {
                                    String temp = "     "+pq.getString("bookTitle")+"               "+pq.getString("bookAuther")+"      ";
                                    DummyContent.addItem(temp, pq.getString("bookTitle"));
                                }

                                //result_book.setText(object.getString("bookTitle"));
                                //result_book.setText(object.getString("bookAuther"));

                                Context context = getApplicationContext();
                                CharSequence text = "Book Found";
                                int duration = Toast.LENGTH_LONG;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }
                    });

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
