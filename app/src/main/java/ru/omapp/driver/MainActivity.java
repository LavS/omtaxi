package ru.omapp.driver;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import ru.omapp.driver.fragment.FragmentConditions;
import ru.omapp.driver.fragment.FragmentContacts;
import ru.omapp.driver.fragment.FragmentInstructions;
import ru.omapp.driver.fragment.FragmentMain;
import ru.omapp.driver.fragment.FragmentRegistration;
import ru.omapp.driver.fragment.FragmentSuccess;
import ru.omapp.driver.mail.ExtendedMail;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mainContext;

    FragmentMain fMain;
    FragmentConditions fConditions;
    FragmentContacts fContacts;
    FragmentInstructions fInstructions;
    FragmentRegistration fRegistration;
    FragmentSuccess fSuccess;
    FragmentTransaction fTrans;

    Date date;
    int myYear, myMonth, myDay;
    EditText etDate;
    int dateId;
    int DIALOG_DATE = 1;

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

        fMain = new FragmentMain();
        fConditions = new FragmentConditions();
        fContacts = new FragmentContacts();
        fInstructions = new FragmentInstructions();
        fRegistration = new FragmentRegistration();
        fSuccess = new FragmentSuccess();

        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.inc_fragment, fMain);
        fTrans.commit();

        date = new Date();
        myYear = date.getYear() + 1900;
        myMonth = date.getMonth() + 1;
        myDay = date.getDay();

        setTitle(R.string.menu);

        mainContext = this;

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
        fTrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_registration) {
            fTrans.replace(R.id.inc_fragment, fRegistration);

        } else if (id == R.id.nav_instructions) {
            fTrans.replace(R.id.inc_fragment, fInstructions);

        } else if (id == R.id.nav_conditions) {
            fTrans.replace(R.id.inc_fragment, fConditions);

        } else if (id == R.id.nav_contacts) {
            fTrans.replace(R.id.inc_fragment, fContacts);

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getText(R.string.omtaxi_share));
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));
        }

        fTrans.addToBackStack(null);
        fTrans.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void OnClick(View item)
    {
        int id = item.getId();
        fTrans = getFragmentManager().beginTransaction();

        if (id == R.id.btn_registration) {
            fTrans.replace(R.id.inc_fragment, fRegistration);

        } else if (id == R.id.btn_instructions) {
        fTrans.replace(R.id.inc_fragment, fInstructions);

        } else if (id == R.id.btn_conditions) {
            fTrans.replace(R.id.inc_fragment, fConditions);

        } else if (id == R.id.btn_contacts) {
            fTrans.replace(R.id.inc_fragment, fContacts);

        // Instructions
        } else if (id == R.id.btn_install) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_install)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_photocontrol) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_photocontrol)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_firsttrip) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_firsttrip)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_noorders) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_noorders)));
            startActivity(browserIntent);

        } else if (id == R.id.btn_faq) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url_faq)));
            startActivity(browserIntent);

        // Registration
        } else if (id == R.id.btn_begin) {

            String address = getResources().getString(R.string.omtaxi_email);
            String subject = getResources().getString(R.string.email_registration);
            String emailtext = Registration();
            ExtendedMail mail = new ExtendedMail(this, address, subject, emailtext);
            fTrans.replace(R.id.inc_fragment, fSuccess);


        // Contacts

        } else if (id == R.id.btn_whatsapp) {
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse("whatsapp://send?phone="+getResources().getString(R.string.omtaxi_phone)));
            try {
                startActivity(whatsappIntent);
            }
            catch (Exception e) {
                Toast.makeText(this, getResources().getString(R.string.install_whatsapp), Toast.LENGTH_SHORT).show();
                Uri marketUri = Uri.parse(getResources().getString(R.string.market_whatsapp));
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }

        } else if (id == R.id.btn_viber) {

            String sphone = getResources().getString(R.string.omtaxi_phone);
            Uri uri = Uri.parse("tel:" + Uri.encode(sphone));
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.viber.voip", "com.viber.voip.WelcomeActivity");
            intent.setData(uri);
            startActivity(intent);
            try {
                startActivity(intent);
            }
            catch (Exception e) {
                Toast.makeText(this, getResources().getString(R.string.install_viber), Toast.LENGTH_SHORT).show();
                Uri marketUri = Uri.parse(getResources().getString(R.string.market_viber));
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }

         } else if (id == R.id.btn_telegram) {
            Intent telegramIntent = new Intent(Intent.ACTION_VIEW);
            telegramIntent.setData(Uri.parse("tg://resolve?domain="+getResources().getString(R.string.omtaxi_name)));
            try {
                startActivity(telegramIntent);
            }
            catch (Exception e) {
                Toast.makeText(this, getResources().getString(R.string.install_telegram), Toast.LENGTH_SHORT).show();
                Uri marketUri = Uri.parse(getResources().getString(R.string.market_telegram));
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }

        } else if (id == R.id.btn_phone) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            phoneIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.omtaxi_phone)));
            startActivity(phoneIntent);

        } else if (id == R.id.btn_email) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                    new String[] {getResources().getString(R.string.omtaxi_email)});
            startActivity(Intent.createChooser(emailIntent,getResources().getText(R.string.send)));
        }

        fTrans.addToBackStack(null);
        fTrans.commit();

    }

    public String Registration(){
        String send;
        send = getResources().getString(R.string.town) + ": "
                + ((EditText) findViewById(R.id.edt_town)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.fio) + ": "
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

        send = send + "\n" + getResources().getString(R.string.car_date) + ": "
                + ((EditText) findViewById(R.id.edt_car_date)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_color) + ": "
                + ((EditText) findViewById(R.id.edt_car_color)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.car_number) + ": "
                + ((EditText) findViewById(R.id.edt_car_num)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.license) + ": "
                + ((EditText) findViewById(R.id.edt_license)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.promo_code) + ": "
                + ((EditText) findViewById(R.id.edt_promo_code)).getText().toString();

        send = send + "\n" + getResources().getString(R.string.processing);

        return send;
    }

    public void OnSetDate(View view) {
        dateId = view.getId();
        if (dateId == R.id.btn_begin_date) {
            dateId = R.id.edt_begin_date;
        }
        else if (dateId == R.id.btn_end_date)
        {
            dateId = R.id.edt_end_date;
        }
        showDialog(DIALOG_DATE);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int myYear, int myMonth, int myDay) {
            etDate = (EditText) findViewById(dateId);
            etDate.setText((CharSequence) (myDay + "." + (myMonth + 1) + "." + myYear));
        }
    };

    public void OnChangeText(View item){
        boolean isEmpty = ((EditText) findViewById(R.id.edt_fio)).getText().toString().trim().equals("")
                || ((EditText) findViewById(R.id.edt_phone)).getText().toString().trim().equals("")
                || ((EditText) findViewById(R.id.edt_rights)).getText().toString().trim().equals("")
                || ((EditText) findViewById(R.id.edt_begin_date)).getText().toString().trim().equals("")
                || ((EditText) findViewById(R.id.edt_end_date)).getText().toString().trim().equals("");
        ((Button) findViewById(R.id.btn_begin)).setEnabled(!isEmpty);
    }

}

