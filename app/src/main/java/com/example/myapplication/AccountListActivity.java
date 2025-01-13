package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AccountListActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ListView listViewAccounts;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);


        databaseHelper = new DatabaseHelper(this);


        listViewAccounts = findViewById(R.id.listView_accounts);
        closeButton = findViewById(R.id.button_close);


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Bu aktiviteyi kapatır
            }
        });


        ArrayList<String> accountsList = databaseHelper.getAllAccounts();

        if (accountsList.isEmpty()) {
            Toast.makeText(AccountListActivity.this, "No accounts found", Toast.LENGTH_SHORT).show();
        } else {

            AccountAdapter adapter = new AccountAdapter(this, accountsList);
            listViewAccounts.setAdapter(adapter);
        }
    }
}
