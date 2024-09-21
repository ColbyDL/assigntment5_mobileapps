package com.example.assignment05;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserInfo extends AppCompatActivity {

    EditText name_edit_userinfo;
    EditText email_edit_userinfo;
    EditText age_edit_userinfo;

    TextView country_display_userinfo;
    TextView dob_display_userinfo;

    User user = new User();

    ActivityResultLauncher<Intent> startDOBActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            dob_display_userinfo = findViewById(R.id.dob_display_userinfo);

            if ( result.getResultCode() == RESULT_OK && result.getData() != null ) {
                Log.d("DOB", result.getData().getStringExtra("DOB"));
                user.dob = result.getData().getStringExtra("DOB");
                dob_display_userinfo.setText(user.dob);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name_edit_userinfo = findViewById(R.id.name_edit_userinfo);
        email_edit_userinfo = findViewById(R.id.email_edit_userinfo);
        age_edit_userinfo = findViewById(R.id.age_edit_userinfo);

        country_display_userinfo = findViewById(R.id.country_display_userinfo);

        final int[] checkedItem = {-1};
        final String[] userCountry = new String[1];

        findViewById(R.id.selectCountry_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserInfo.this);
                alertDialog.setTitle("Select a Country");

                alertDialog.setSingleChoiceItems(Data.countries, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkedItem[0] = i;

                    }
                });

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userCountry[0] = Data.countries[checkedItem[0]];
                        Log.d("onListSelect", userCountry[0]);
                        country_display_userinfo.setText(userCountry[0]);
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog countrySelectorDialog = alertDialog.create();
                countrySelectorDialog.show();
            }
        });

        findViewById(R.id.selectDoB_userinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfo.this, DateOfBirth.class);

                startDOBActivity.launch(intent);
            }
        });

        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( String.valueOf(name_edit_userinfo.getText()).isEmpty() ) {
                    Toast.makeText(UserInfo.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                } else if ( String.valueOf(email_edit_userinfo.getText()).isEmpty() ) {
                    Toast.makeText(UserInfo.this, "Please enter an email address", Toast.LENGTH_SHORT).show();
                } else if ( String.valueOf(age_edit_userinfo.getText()).isEmpty() ) {
                    Toast.makeText(UserInfo.this, "Please enter an age", Toast.LENGTH_SHORT).show();
                } else if ( userCountry[0] == null ) {
                    Toast.makeText(UserInfo.this, "Please select a country", Toast.LENGTH_SHORT).show();
                } else if ( user.dob.isEmpty() ) {
                    Toast.makeText(UserInfo.this, "Please select your Date of Birth", Toast.LENGTH_SHORT).show();
                } else {
                    user.name = String.valueOf(name_edit_userinfo.getText());
                    user.email = String.valueOf(email_edit_userinfo.getText());
                    user.age = Integer.parseInt(String.valueOf(age_edit_userinfo.getText()));
                    user.country = userCountry[0];

                    Log.d("SUBMIT", "User Created: " + user.name + ", " + user.email + ", " + String.valueOf(user.age) + ", " + user.country + ", " + user.dob);

                    Intent toProfile = new Intent(UserInfo.this, Profile.class);

                    toProfile.putExtra("USER", user);

                    startActivity(toProfile);
                }
            }
        });
    }
}