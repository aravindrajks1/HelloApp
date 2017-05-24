package com.example.aravind.dialerapp;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class AddtoContacts extends DialogFragment {

    View view;
    Button btn;
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addto_contacts, container, false);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = getActivity();

        initViews();

        btn = (Button) view.findViewById(R.id.btn_add);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    // Getting reference to Name EditText
                    EditText etName = (EditText) view.findViewById(R.id.et_name);

                    // Getting reference to Mobile EditText
                    EditText etMobile = (EditText) view.findViewById(R.id.et_mobile_phone);

                    // Getting reference to WorkEmail EditText
                    EditText etWorkEmail = (EditText) view.findViewById(R.id.et_work_email);


                    ArrayList<ContentProviderOperation> ops =
                            new ArrayList<ContentProviderOperation>();

                    int rawContactID = ops.size();

                    // Adding insert operation to operations list
                    // to insert a new raw contact in the table ContactsContract.RawContacts
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(RawContacts.ACCOUNT_NAME, null)
                            .build());

                    // Adding insert operation to operations list
                    // to insert display name in the table ContactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
                            .build());

                    // Adding insert operation to operations list
                    // to insert Mobile Number in the table ContactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                            .withValue(Phone.NUMBER, etMobile.getText().toString())
                            .withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
                            .build());


                    // Adding insert operation to operations list
                    // to insert Work Email in the table ContactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
                            .withValue(Email.ADDRESS, etWorkEmail.getText().toString())
                            .withValue(Email.TYPE, Email.TYPE_WORK)
                            .build());

                    try {
                        // Executing all the insert operations as a single database transaction
                        activity.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                        Toast.makeText(getActivity(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException | OperationApplicationException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void initViews() {

        view = getView();
    }
}