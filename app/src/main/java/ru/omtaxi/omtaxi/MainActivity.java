package ru.omtaxi.omtaxi;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentMain f_main;
    FragmentConditions f_conditions;
    FragmentContacts f_contacts;
    FragmentInstructions f_instructions;
    FragmentRegistration f_registration;
    FragmentTransaction f_trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        f_main = new FragmentMain();
        f_conditions = new FragmentConditions();
        f_contacts = new FragmentContacts();
        f_instructions = new FragmentInstructions();
        f_registration = new FragmentRegistration();

        f_trans = getFragmentManager().beginTransaction();
        f_trans.add(R.id.inc_fragment, f_main);
        f_trans.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        f_trans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_registration) {
            f_trans.replace(R.id.inc_fragment, f_registration);

        } else if (id == R.id.nav_instructions) {
            f_trans.replace(R.id.inc_fragment, f_instructions);

        } else if (id == R.id.nav_conditions) {
            f_trans.replace(R.id.inc_fragment, f_conditions);

        } else if (id == R.id.nav_contacts) {
            f_trans.replace(R.id.inc_fragment, f_contacts);

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getText(R.string.omtaxi_share));
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));

        } else if (id == R.id.nav_send) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                    new String[] {getResources().getString(R.string.omtaxi_email)});
            startActivity(Intent.createChooser(emailIntent,getResources().getText(R.string.send)));
        }

        f_trans.addToBackStack(null);
        f_trans.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnClick(View item)
    {
        int id = item.getId();
        f_trans = getFragmentManager().beginTransaction();

        // Main activity
        if (id == R.id.btn_registration) {
            f_trans.replace(R.id.inc_fragment, f_registration);

        } else if (id == R.id.btn_instructions) {
            f_trans.replace(R.id.inc_fragment, f_instructions);

        } else if (id == R.id.btn_conditions) {
            f_trans.replace(R.id.inc_fragment, f_conditions);

        } else if (id == R.id.btn_contacts) {
            f_trans.replace(R.id.inc_fragment, f_contacts);

        // Instructions
        } else if (id == R.id.btn_install) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getResources().getString(R.string.url_install)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_photocontrol) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getResources().getString(R.string.url_photocontrol)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_firsttrip) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getResources().getString(R.string.url_firsttrip)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_noorders) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getResources().getString(R.string.url_noorders)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_faq) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getResources().getString(R.string.url_faq)));
            startActivity(browserIntent);

        // Registration
        } else if (id == R.id.btn_begin) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                    new String[] {getResources().getString(R.string.omtaxi_email)});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                    getResources().getString(R.string.email_registration));
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Registration());
            startActivity(Intent.createChooser(emailIntent,getResources().getText(R.string.send)));
        }

        f_trans.addToBackStack(null);
        f_trans.commit();
    }

    public String Registration()
    {
        String send;
        send = getResources().getString(R.string.fio) + ": "
                + ((EditText) findViewById(R.id.edt_fio)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.phone) + ": "
                + ((EditText) findViewById(R.id.edt_phone)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.rights) + ": "
                + ((EditText) findViewById(R.id.edt_rights)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.begin_date) + ": "
                + ((EditText) findViewById(R.id.edt_begin_date)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.end_date) + ": "
                + ((EditText) findViewById(R.id.edt_end_date)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_brand) + ": "
                + ((EditText) findViewById(R.id.edt_car_brand)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_model) + ": "
                + ((EditText) findViewById(R.id.edt_car_model)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_color) + ": "
                + ((EditText) findViewById(R.id.edt_car_color)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_number) + ": "
                + ((EditText) findViewById(R.id.edt_car_number)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.license) + ": "
                + ((EditText) findViewById(R.id.edt_license)).getText().toString();

        return send;
    }
}