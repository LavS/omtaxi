package ru.omtaxi.omtaxi;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if (id == R.id.nav_online) {
            Toast.makeText(drawer.getContext(), "@string/online", Toast.LENGTH_LONG);

        } else if (id == R.id.nav_instructions) {
            Toast.makeText(drawer.getContext(), "@string/instructions", Toast.LENGTH_LONG);

        } else if (id == R.id.nav_conditions) {
            Toast.makeText(drawer.getContext(), "@string/conditions", Toast.LENGTH_LONG);

        } else if (id == R.id.nav_contacts) {
            Toast.makeText(drawer.getContext(), "@string/contacts", Toast.LENGTH_LONG);

        } else if (id == R.id.nav_share) {
            Toast.makeText(drawer.getContext(), "@string/share", Toast.LENGTH_LONG);

        } else if (id == R.id.nav_send) {
            Toast.makeText(drawer.getContext(), "@string/send", Toast.LENGTH_LONG);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}