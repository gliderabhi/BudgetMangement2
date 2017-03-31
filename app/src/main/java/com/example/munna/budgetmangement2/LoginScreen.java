package com.example.munna.budgetmangement2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    ImageView img;
    Integer SPLASH_DISPLAY_LENGTH=3000;
   SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        sharedPreferences=getSharedPreferences(getString(R.string.TableName), Context.MODE_PRIVATE);
       String name= sharedPreferences.getString(getString(R.string.TableName),null);
       if(name!=null) {
           Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
       }

        img=(ImageView)findViewById(R.id.loginImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LoginScreen.this, MainMenu.class);
                LoginScreen.this.startActivity(mainIntent);
                LoginScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
