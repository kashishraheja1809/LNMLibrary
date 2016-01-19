package com.kartikexample.lnmlibrary;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Navi_draw_user extends AppCompatActivity {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_draw);

        setTitle("User");

        mPlanetTitles = getResources().getStringArray(R.array.navigation_drawer_items_array2);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Navi_draw_user.this, mPlanetTitles[position] + " is selected", Toast.LENGTH_LONG).show();
                //Intent intent_home = new Intent (this, Navi_draw.class);
                if (position == 0) {
                    Intent intent_home = new Intent(Navi_draw_user.this, Navi_draw.class);
                    startActivity(intent_home);
                }
                if (position == 1) {
                    Intent intent_search = new Intent(Navi_draw_user.this, SearchActivity.class);
                    startActivity(intent_search);
                } else if (position == 2) {
                    Intent intent_pro = new Intent(Navi_draw_user.this, SearchActivity.class);
                    startActivity(intent_pro);
                } else if (position == 3) {
                    Intent intent_noti = new Intent(Navi_draw_user.this, TransactionActivity.class);
                    startActivity(intent_noti);
                } else if (position == 4) {
                    Intent intent_book_data = new Intent(Navi_draw_user.this, ChangePassword.class);
                    startActivity(intent_book_data);
                }
                else if(position == 5)
                {
                    Intent intent_logout = new Intent(Navi_draw_user.this, LoginActivity.class);
                    startActivity(intent_logout);
                }
            }
        });
    }




    /*public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(this, mPlanetTitles[position] + " is selected", Toast.LENGTH_LONG).show();
        //Intent intent_home = new Intent (this, Navi_draw.class);
        if(position==0)
        {
            Intent intent_home = new Intent (this, Navi_draw.class);
            startActivity(intent_home);
        }
        if(position == 1)
        {
            Intent intent_search = new Intent(this, SearchActivity.class);
            startActivity(intent_search);
        }
        else if(position == 2)
        {
            Intent intent_pro = new Intent(this, SearchActivity.class);
            startActivity(intent_pro);
        }
        else if(position == 3)
        {
            Intent intent_noti = new Intent(this, SearchActivity.class);
            startActivity(intent_noti);
        }
        else if(position == 4)
        {
            Intent intent_book_data = new Intent(this, Admin_NewBookActivity.class);
            startActivity(intent_book_data);
        }
        else if(position == 5)
        {
            Intent intent_en_user = new Intent(this, Admin_UserEnrollActivity.class);
            startActivity(intent_en_user);
        }
        else if(position == 6)
        {
            Intent intent_pur = new Intent(this, SearchActivity.class);
            startActivity(intent_pur);
        }
        else if(position == 7)
        {
            Intent intent_ch_pass = new Intent(this, ChangePassword.class);
            startActivity(intent_ch_pass);
        }

} */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navi_draw, menu);
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
