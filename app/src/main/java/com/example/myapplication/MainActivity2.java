package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Button showAccountListButton, addAccountButton, deleteAccountButton, updateAccountButton;
    private EditText editTextId, editTextNickname, editTextPassword, editTextRank;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        showAccountListButton = findViewById(R.id.button_account_list);
        addAccountButton = findViewById(R.id.button_add);
        deleteAccountButton = findViewById(R.id.button_account_delete);
        updateAccountButton = findViewById(R.id.button_list_update);
        editTextId = findViewById(R.id.editText_id);
        editTextNickname = findViewById(R.id.editText_nickname);
        editTextPassword = findViewById(R.id.editText_password);
        editTextRank = findViewById(R.id.editText_rank);


        dbHelper = new DatabaseHelper(this);


        showAccountListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AccountListActivity.class);
                startActivity(intent);
            }
        });


        addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString().trim();
                String nickname = editTextNickname.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String rank = editTextRank.getText().toString().trim();


                if (!id.isEmpty() && !nickname.isEmpty() && !password.isEmpty() && !rank.isEmpty()) {

                    long result = dbHelper.addAccount(id, nickname, password, rank);

                    if (result != -1) {

                        Toast.makeText(MainActivity2.this, "Hesap başarıyla eklendi!", Toast.LENGTH_SHORT).show();


                        editTextId.setText("");
                        editTextNickname.setText("");
                        editTextPassword.setText("");
                        editTextRank.setText("");
                    } else {

                        Toast.makeText(MainActivity2.this, "Hesap eklenemedi. Bu ID zaten mevcut olabilir.", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(MainActivity2.this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString().trim();

                if (!id.isEmpty()) {

                    int result = dbHelper.deleteAccount(id);

                    if (result > 0) {

                        Toast.makeText(MainActivity2.this, "Hesap başarıyla silindi!", Toast.LENGTH_SHORT).show();


                        editTextId.setText("");
                        editTextNickname.setText("");
                        editTextPassword.setText("");
                        editTextRank.setText("");
                    } else {

                        Toast.makeText(MainActivity2.this, "Hesap silinemedi. Bu ID mevcut değil.", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(MainActivity2.this, "Lütfen bir ID girin.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        updateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editTextId.getText().toString().trim();
                String nickname = editTextNickname.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String rank = editTextRank.getText().toString().trim();

                if (!id.isEmpty()) {
                    int result = dbHelper.updateAccount(id, nickname, password, rank);

                    if (result > 0) {
                        Toast.makeText(MainActivity2.this, "Hesap başarıyla güncellendi!", Toast.LENGTH_SHORT).show();

                        editTextId.setText("");
                        editTextNickname.setText("");
                        editTextPassword.setText("");
                        editTextRank.setText("");
                    } else {
                        Toast.makeText(MainActivity2.this, "Hesap güncellenemedi. Bu ID mevcut değil.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Lütfen bir ID girin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
