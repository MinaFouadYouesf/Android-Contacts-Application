package com.example.mina.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    ArrayList<listObject> list;
    Context context;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<listObject> list) {
        super(context, resource, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_design, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        ImageView imageView = (ImageView) view.findViewById(R.id.arrow);
        listObject ob = list.get(position);
        name.setText(ob.Name);
        phone.setText(ob.Phone);

        imageView.setImageResource(R.drawable.arrow);
        return view;
    }
}
