package com.kartikexample.lnmlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Book_PopUp extends AppCompatActivity {

    TextView text_title,text_author,text_publisher,text_isbn,text_accno,text_callno,text_status,text_sms;
    Button reserve;
    Date date;
    DateFormat dateFormat;
    String book_title, book_accno, booktitle,temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__pop_up);
        setTitle("Book Details");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        reserve = (Button)findViewById(R.id.res_button);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.75),(int)(height*0.75));

        text_title = (TextView)findViewById(R.id.textView8);
        text_publisher = (TextView)findViewById(R.id.textView9);
        text_author = (TextView)findViewById(R.id.textView10);
        text_isbn = (TextView)findViewById(R.id.textView11);
        text_accno = (TextView)findViewById(R.id.textView12);
        text_callno = (TextView)findViewById(R.id.textView13);
        text_status = (TextView)findViewById(R.id.textView14);
        text_sms = (TextView)findViewById(R.id.textView15);
        book_title = getIntent().getExtras().getString("selectedBook");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("libBookDet");
        query.whereEqualTo("bookTitle", book_title);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("score", "The getFirst request failed.");
                    Context context = getApplicationContext();
                    CharSequence text = "Book not Found";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    temp = object.getString("objectId");
                    Log.d(temp,"kashish");
                    book_accno = object.getString("bookAccNo");
                    booktitle = object.getString("bookTitle");
                    text_title.setText(object.getString("bookTitle"));
                    text_author.setText(object.getString("bookAuther"));
                    text_publisher.setText(object.getString("bookPublisher"));
                    text_isbn.setText(object.getString("bookISBN"));
                    text_accno.setText(object.getString("bookAccNo"));
                    text_callno.setText(object.getString("bookColNo"));
                    text_status.setText(object.getString("bookStatus"));
                    if(object.getString("bookStatus").equals("Reserved"))
                    {
                        reserve.setEnabled(false);
                    }
                    object.put("bookStatus" , "Reserved");
                }
            }
        });

        String status="Reserved";
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("libBookDet");
                query.whereEqualTo("bookAccNo", book_accno);
                query.getFirstInBackground(new GetCallback<ParseObject>() {
                    public void done(ParseObject object1, ParseException e) {
                        if (object1 == null) {
                            Log.d("score", "The getFirst request failed.");
                            Context context = getApplicationContext();
                            CharSequence text = "Error Occured";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else {
                            object1.put("bookStatus","Reserved");
                            Log.d(object1.getString("bookTitle"),"kumar");
                            object1.saveInBackground();
                        }
                    }
                });

                ParseUser currentUser = ParseUser.getCurrentUser();

                dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                date = new Date();
                String temp_date = dateFormat.format(date);
                try {
                    date = dateFormat.parse(temp_date);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                Date today_date = date;
                System.out.println(date);
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(dateFormat.parse(dateFormat.format(date)));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 15);

                //SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                Date return_date = (Date) c.getTime();
                Log.d("kashish", "raheja");

                ParseObject book_reserve = new ParseObject("libIssued");
                book_reserve.put("issAccNo",text_accno.getText().toString());
                book_reserve.put("bookTitle", text_title.getText().toString());
                Log.d("kartikeya", "sharma");
                book_reserve.put("issWhen", date);
                book_reserve.put("issTill", return_date);
                book_reserve.put("issUserId", LoginActivity.getUseridinput());
                book_reserve.put("issFine", (int) 0);
                book_reserve.saveInBackground();
                long diff = today_date.getTime() - return_date.getTime();
                long days = diff/(24*60*60*1000);

                text_sms.setText("Book has been reserved successfully");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book__pop_up, menu);
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
