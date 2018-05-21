package com.example.mina.contact;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;

import java.io.Serializable;

public class listObject implements Serializable {

    String Phone, Name, Email;

    public listObject(String Phone, String Name, String Email) {
        this.Phone = Phone;
        this.Name = Name;
        this.Email = Email;
    }

}
