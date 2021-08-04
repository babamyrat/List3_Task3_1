package com.example.list3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.list3.db.AppDatabase;
import com.example.list3.db.User;

public class NewAddDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_database);
    final EditText txtIdCategory = findViewById(R.id.editTextId);
    final EditText txtStrCategory = findViewById(R.id.editTextName);
    final EditText txtStrCategoryThumb = findViewById(R.id.editTextImage);
    final EditText txtStrCategoryDescription = findViewById(R.id.editTextTextFull);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewUser(txtIdCategory.getText().toString(), txtStrCategory.getText().toString(), txtStrCategoryThumb.getText().toString(), txtStrCategoryDescription.getText().toString());
            }
        });

    }

    private void saveNewUser(String txtIdCategory, String txtStrCategory, String txtStrCategoryThumb, String txtStrCategoryDescription) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
    user.idCategory = txtIdCategory;
    user.strCategory = txtStrCategory;
    user.strCategoryThumb = txtStrCategoryThumb;
    user.strCategoryDescription = txtStrCategoryDescription;
    db.userDao().insertUser(user);
    finish();

    }
}