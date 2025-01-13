package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AccountAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> accounts;

    public AccountAdapter(Context context, List<String> accounts) {
        super(context, R.layout.list_item_account, accounts);
        this.context = context;
        this.accounts = accounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_account, parent, false);
        }

        String accountInfo = accounts.get(position);

        TextView accountTextView = convertView.findViewById(R.id.account_info);
        accountTextView.setText(accountInfo);

        return convertView;
    }
}
