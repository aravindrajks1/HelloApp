package com.example.aravind.dialerapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aravind.dialerapp.Database.DataStoreHelper;
import com.example.aravind.dialerapp.Model.ShareFragment;

public class Navigation extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    EditText edtPhoneNo;
    TextView lblinfo;
    private DataStoreHelper dsh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainFragment mf = new MainFragment();
        ft.replace(R.id.contentmain, mf);
        ft.addToBackStack("mf");
        ft.commit();


        dsh = new DataStoreHelper(this);
        dsh.open();
        edtPhoneNo = (EditText) findViewById(R.id.edtPhoneNumber);
        lblinfo = (TextView) findViewById(R.id.lblinfo);
    }

    public void buttonClickEvent(View v) {
        String phoneNo = edtPhoneNo.getText().toString();
        try {
            switch (v.getId()) {
                case R.id.btnAterisk:
                    lblinfo.setText("");
                    phoneNo += "*";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnHash:
                    lblinfo.setText("");
                    phoneNo += "#";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnZero:
                    lblinfo.setText("");
                    phoneNo += "0";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnOne:
                    lblinfo.setText("");
                    phoneNo += "1";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnTwo:
                    lblinfo.setText("");
                    phoneNo += "2";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnThree:
                    lblinfo.setText("");
                    phoneNo += "3";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnFour:
                    lblinfo.setText("");
                    phoneNo += "4";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnFive:
                    lblinfo.setText("");
                    phoneNo += "5";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnSix:
                    lblinfo.setText("");
                    phoneNo += "6";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnSeven:
                    lblinfo.setText("");
                    phoneNo += "7";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnEight:
                    lblinfo.setText("");
                    phoneNo += "8";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnNine:
                    lblinfo.setText("");
                    phoneNo += "9";
                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btndel:
                    lblinfo.setText("");
                    if (phoneNo != null && phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                    }

                    edtPhoneNo.setText(phoneNo);
                    break;
                case R.id.btnClearAll:
                    lblinfo.setText("");
                    edtPhoneNo.setText("");
                    break;
                case R.id.btnCall:
                    if (phoneNo.trim().equals("")) {
                        lblinfo.setText("Please enter number to call");
                    } else if (phoneNo.length() >= 12) {
                        lblinfo.setText("Please Enter Valid Number");
                    } else if (phoneNo.length() < 3) {
                        lblinfo.setText("Please Enter a Valid Number");
                    } else {
                        Boolean isHash = false;
                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(callIntent);
                        } else {
                            String phone = edtPhoneNo.getText().toString();

                            dsh = new DataStoreHelper(getApplicationContext());

                            long statues = dsh.insertingdata(null, null, null, phone);
                            if (statues == -1) {
                                // Toast.makeText(getApplicationContext(), "Saved Successfully" + statues, Toast.LENGTH_LONG).show();
                            } else {
                                //Toast.makeText(this, "Data IS nOT saved", Toast.LENGTH_SHORT).show();
                            }
                            //  long data = dsh.insertingdata(null ,null,null,phone);
                            // String query = "INSERT INTO persons (name,address) VALUES('"+phone+"');";
                            //Toast.makeText(getApplicationContext(),"Saved Successfully" +query, Toast.LENGTH_LONG).show();


                            String callInfo = "tel:" + phoneNo;
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }
                    }
                    break;
            }

        } catch (Exception ex) {

        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.go_pro) {
            /*Frafment
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            GoProFragment gp = new GoProFragment();
            ft.replace(R.id.contentmain,gp);
            ft.commit();
            ft.addToBackStack("gp");*/
            //Dialog
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            GoProFragment gpf = new GoProFragment();
            gpf.setStyle(GoProFragment.STYLE_NO_TITLE, 0);
            //gpf.setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Dialog_NoActionBar);
            //  gpf.setStyle(STYLE_NORMAL,0);
            gpf.show(ft, "Sample Fragment");


        } else if (id == R.id.Help) {

            Toast.makeText(getApplication(), "No Network", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_share) {
            FragmentManager fms = getSupportFragmentManager();
            FragmentTransaction fts = fms.beginTransaction();
            ShareFragment mfs = new ShareFragment();
            fts.replace(R.id.contentmain, mfs);
            fts.addToBackStack("mf");
            fts.commit();

        } else if (id == R.id.invite_friends) {
            Toast.makeText(getApplication(), "Tell Your Friends to Download this App", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.call_log) {

            Intent i = new Intent(Navigation.this, CallLogs.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
