package frms.localsqlapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sm.datab.DataBase.DataBaseHandler;

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
    private List<Map<String, String>> getAllContacts(){
  // instancie à la base de donnée
        DataBaseHandler db= new DataBaseHandler(this);

        // Excute une requete de selection
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM contacts", null);

        //Instencie la liste
        List<Map<String, String>> contactList = new ArrayList<>();


        while (cursor.moveToNext()){
            Map<String, String> contactCole  = new HashMap<>();
            contactCole.put("name", cursor.getString(0));
            contactCole.put("Surname", cursor.getString(1));
            contactCole.put("email", cursor.getString(2));

            // ajouter du map à mon Array List
            contactList.add(contactCole);

        }

        return contactList;

    }
}
