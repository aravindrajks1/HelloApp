package com.example.aravind.dialerapp;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.calllog:
                    Intent i = new Intent(getActivity(), CallLogs.class);
                    getActivity().startActivity(i);
                    return true;

                case R.id.navigation_search:
                    FragmentManager fms = getFragmentManager();
                    FragmentTransaction fts = fms.beginTransaction();
                    SearchFragment sf = new SearchFragment();
                    fts.replace(R.id.mainFragment, sf);
                    fts.commit();
                    fts.addToBackStack("acf");
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 1);
                    return true;

                case R.id.navigation_add_to_contacts:
                   /* Intent intent1 = new Intent(getActivity(), AddtoContacts.class);
                    startActivity(intent1);*/
//Dialog
                    FragmentManager fmadd = getFragmentManager();
                    FragmentTransaction ftadd = fmadd.beginTransaction();
                    AddtoContacts acfa = new AddtoContacts();
                    acfa.show(ftadd, "Add New Contact");
                    acfa.setStyle(AddtoContacts.STYLE_NO_TITLE, 0);

                    //acfa.setCancelable(false);
                    return true;

                case R.id.navigation_Displaycontacts:
                    Intent contacts = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                    startActivity(contacts);
                    return true;

                //Dialog Fragment
                /*    FragmentManager fmd = getFragmentManager();
                    FragmentTransaction ftd = fmd.beginTransaction();
                    Intent dcf = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                    dcf.show(ftd, "Sample Fragment");
                    dcf.setStyle(DisplayFragment.STYLE_NO_TITLE, 0);
                    return true;*/


                case R.id.navigation_addcontact:
                    //Dialog Fragment
                    FragmentManager fma = getFragmentManager();
                    FragmentTransaction ft = fma.beginTransaction();
                    AddContactFragment acf = new AddContactFragment();
                    acf.show(ft, "Sample Fragment");
                    acf.setStyle(GoProFragment.STYLE_NO_TITLE, 0);
                    acf.setCancelable(false);
                    return true;
            }
            return false;
        }

    };

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;

    }

}
