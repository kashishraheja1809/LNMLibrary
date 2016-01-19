package com.kartikexample.lnmlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Admin_NewBookActivity extends Activity {

    EditText book_title,book_author,book_publisher,book_accno,book_callno,book_isbn,book_status;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("app", "kashish raheja");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__new_book);

        book_title = (EditText)findViewById(R.id.admin_book_titletext);
        book_author = (EditText)findViewById(R.id.admin_book_authortext);
        book_publisher = (EditText)findViewById(R.id.admin_book_pubtext);
        book_accno = (EditText)findViewById(R.id.admin_book_accnotext);
        book_callno = (EditText)findViewById(R.id.admin_book_callnotext);
        book_isbn = (EditText)findViewById(R.id.admin_book_isbntext);
        book_status = (EditText)findViewById(R.id.admin_book_statustext);
        btn_submit = (Button)findViewById(R.id.add_book_btn);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View e) {

                ParseObject addBook = new ParseObject("libBookDet");
                addBook.put("bookTitle", book_title.getText().toString());
                addBook.put("bookAuther", book_author.getText().toString());
                addBook.put("bookPublisher", book_publisher.getText().toString());
                addBook.put("bookAccNo", book_accno.getText().toString());
                addBook.put("bookColNo", book_callno.getText().toString());
                addBook.put("bookISBN", book_isbn.getText().toString());
                addBook.put("bookStatus", book_status.getText().toString());
                addBook.saveInBackground();
                Context context = getApplicationContext();
                CharSequence text = "Data has been entered successfully";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        /*btn_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });*/

    }
}
