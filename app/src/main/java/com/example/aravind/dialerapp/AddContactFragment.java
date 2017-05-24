package com.example.aravind.dialerapp;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aravind.dialerapp.Database.DataStoreHelper;


public class AddContactFragment extends DialogFragment {

    EditText Id, name, contact, email;
    Button save, cancel;
    View view;
    ImageView imgclose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        Id = (EditText) view.findViewById(R.id.edId);
        name = (EditText) view.findViewById(R.id.edName);
        contact = (EditText) view.findViewById(R.id.edContact);
        email = (EditText) view.findViewById(R.id.edemail);
        save = (Button) view.findViewById(R.id.btn_save);
        cancel = (Button) view.findViewById(R.id.btn_cancel);
        imgclose = (ImageView) view.findViewById(R.id.img_close);

        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContactFragment.this.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValid()) {
                    DataStoreHelper dsh = new DataStoreHelper(getActivity());
                    dsh.open();
                    try {
                        String Idd = Id.getText().toString();
                        String namee = name.getText().toString();
                        String contactt = contact.getText().toString();
                        String emaill = email.getText().toString();
                        long data = dsh.insertingdata(Idd, namee, contactt, emaill);
                        if (data == -1) {
                            Toast.makeText(getActivity(), "Data saved Succesfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Data Not Saved", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Id.setText("");
                    name.setText("");
                    email.setText("");
                    contact.setText("");

                } else {
                    Toast.makeText(getActivity(), "Enter the Details Correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id.setText("");
                name.setText("");
                email.setText("");
                contact.setText("");
            }
        });
        return view;
    }

    private boolean isValid() {

        if (Id.getText().toString().isEmpty()) {
            Id.setError("id is Empty, Please Enter");
            return true;
        }
        if (name.getText().toString().isEmpty()) {
            name.setError("Name Field is Empty");
            return true;
        }
        if (contact.getText().toString().isEmpty()) {
            contact.setError("Contact Field is Empty");
            return true;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Email Field is Empty");
            return true;
        }
        return false;
    }

}
