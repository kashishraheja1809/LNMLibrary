package com.kartikexample.lnmlibrary;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by kartik on 11/8/2015.
 */
public class AdminActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    static AdminActivity adminActivity1; // = new AdminActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, AdminActivity.PlaceholderFragment.newInstance(position + 1))
                .commit();
    }
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    public void onSectionAttached(int number) {
        switch (number) {
            case 1:

                mTitle = getString(R.string.home);
                //LoginActivity newfrag=new LoginActivity();
                //transaction.replace(R.id.action_bar_container, );
                break;
            case 2:
                mTitle = getString(R.string.prof);
                break;
            case 3:
                mTitle = getString(R.string.notifications);
                break;
            case 4:
                mTitle = getString(R.string.book_data);
                break;
            case 5:
                mTitle = getString(R.string.enrol_user);
                break;
            case 6:
                mTitle = getString(R.string.book_pur);
                break;
            case 7:
                mTitle = getString(R.string.ch_pass);
                break;
            default:
                mTitle = getString(R.string.home);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.user, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The frContextagment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final int ARG_NAV_POSITION = 4;
       // private  adminActivity1;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        public PlaceholderFragment() {
        }
        Admin_NewBookActivity admin_newBook;
        Bundle savedInstance;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.activity_login, container, false);
            View rootView = new View(getActivity());
            int i = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (i)
            {
                case 1:
                    Intent intent1 = new Intent(adminActivity1, SearchActivity.class);
                    startActivity(intent1);
                    break;
                case 2: //rootView = (RelativeLayout)inflater.inflate(R.layout.fragment_user,container,false);
                    break;
                case 3: //rootView = (LinearLayout)inflater.inflate(R.layout.activity_login,container,false);
                    break;
                case 4: Intent intent4 = new Intent(adminActivity1, Admin_NewBookActivity.class);
                    startActivity(intent4);
                    break;
                case 5: //rootView = (LinearLayout)inflater.inflate(R.layout.activity_admin__user_enroll,container,false);
                    break;
                case 6: //rootView = (LinearLayout)inflater.inflate(R.layout.activity_admin__purchase,container,false);
                    break;
                case 7: //rootView = (LinearLayout)inflater.inflate(R.layout.activity_change_password,container,false);
                    break;
                default: //rootView=(LinearLayout)inflater.inflate(R.layout.activity_change_password,container,false);
                    break;
            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((AdminActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
