package com.example.assignment05;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView age;
    TextView country;
    TextView dob;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.name_display_profile);
        email = findViewById(R.id.email_display_profile);
        age = findViewById(R.id.age_display_profile);
        country = findViewById(R.id.country_display_profile);
        dob = findViewById(R.id.dob_display_profile);

        if ( getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("USER") ) {
            user = (User) getIntent().getSerializableExtra("USER");

            name.setText( user.name );
            email.setText( user.email );
            age.setText( String.valueOf(user.age ));
            country.setText( user.country );
            dob.setText( user.dob );

        }


    }
}