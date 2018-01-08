package frms.localsqlapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onAddContact(View view) {
        if (view==findViewById(R.id.buttonAddContact)){
            Intent FormIntent = new Intent (this, FormActivity.class);
            startActivity(FormIntent);


        }

    }
}
