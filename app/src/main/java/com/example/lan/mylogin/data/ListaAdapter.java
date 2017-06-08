package com.example.lan.mylogin.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lan.mylogin.R;

import java.util.ArrayList;

/**
 * Created by lan on 07-06-17.
 */

public class ListaAdapter extends ArrayAdapter<Cuenta> {

    public ListaAdapter(Context context, ArrayList<Cuenta> words) {
        super(context, 0, words);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_lista, parent, false);
        }
        Cuenta currentWord = getItem(position);
        TextView cuen = (TextView) listItemView.findViewById(R.id.textoSecundarioItem);
        cuen.setText(currentWord.getNombre());
        TextView pas = (TextView) listItemView.findViewById(R.id.textotres);
        pas.setText(currentWord.getPassword());
        TextView link = (TextView) listItemView.findViewById(R.id.textoPrincipalItem);
        link.setText(currentWord.getLink());

        return listItemView;
    }
}
