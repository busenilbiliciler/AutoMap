package com.example.automap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.automap.ui.DatabaseHelperMovies;

public class LoginActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.lmail);
        e2 = (EditText)findViewById(R.id.lpass);
        b1 = (Button)findViewById(R.id.loginbutton);
        b1.setOnClickListener(new View.OnClickListener(){
                                  @Override
                                  public void onClick(View v) {
                                      String email = e1.getText().toString();
                                      String password = e2.getText().toString();
                                      Boolean checkEmailPass= db.emailpassword(email,password);
                                      if(checkEmailPass==true){
                                          // Toast.makeText(getApplicationContext(),"Successfully LoginActivity", Toast.LENGTH_SHORT).show();

                                          Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                                          startActivity(i);


                                      }
                                      else{
                                          Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();}
                                  }
                              }

        );
    }


}
