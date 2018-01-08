package frms.localsqlapp;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.sm.datab.DataBase.DataBaseHandler;


public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        android.app.ActionBar actionBar = getActionBar();
        if (actionBar != null) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    }
    public  void onValide(View v){
        //ref du bouton
        Button clickButton =(Button) v;

        // insertion de la  id de l'utilisateur;
        String name = ((EditText) findViewById(R.id.textName)).getText().toString();
        String surname =((EditText) findViewById(R.id.textSurname)).getText().toString() ;
        String email =((EditText) findViewById(R.id.textEmail)).getText().toString() ;




              //Intentation de la la base de données
        DataBaseHandler db= new DataBaseHandler(this);


        ContentValues insertValues = new ContentValues();
        insertValues.put("surname", surname);
        insertValues.put("name", name);
        insertValues.put("email", email);
        try{
        db.getReadableDatabase().insert("contacts", null,insertValues );
            Toast.makeText(this, "Données enregistrées", Toast.LENGTH_LONG).show();


            ((EditText) findViewById(R.id.textName)).setText("");
            ((EditText) findViewById(R.id.textSurname)).setText("");
            ((EditText) findViewById(R.id.textEmail)).setText("");
        }
            catch (SQLException ex){
                Log.e( "SQL EXCEPTION", ex.getMessage());
            }

        }
        }



