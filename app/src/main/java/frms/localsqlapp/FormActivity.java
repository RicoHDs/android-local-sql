package frms.localsqlapp;

import android.content.ContentValues;
import android.content.Intent;
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

   private EditText nameEditText;
  private   EditText surnameEditText;
   private EditText emailEditText;
   private  String contactId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);



        android.app.ActionBar actionBar = getActionBar();
        if (actionBar != null) {
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

        Intent intention = getIntent();
        String name = intention.getStringExtra("name");
        String surname = intention.getStringExtra("surname");
        String email = intention.getStringExtra("email");
        String id = intention.getStringExtra("id");

        this.contactId = id;
        this.emailEditText = findViewById(R.id.textEmail);
        this.nameEditText = findViewById(R.id.textName);
        this.surnameEditText = findViewById(R.id.textSurname);

        this.surnameEditText.setText(surname);
        this.nameEditText.setText(name);
        this.emailEditText.setText(email);
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
        try {
            if (this.contactId != null) {

                String[] params = {contactId};
                db.getWritableDatabase().update("contacts", insertValues, "id=?", params);
                        setResult(RESULT_OK);
                        finish();

            } else {
                db.getReadableDatabase().insert("contacts", null, insertValues);
                Toast.makeText(this, "Données enregistrées", Toast.LENGTH_LONG).show();


                ((EditText) findViewById(R.id.textName)).setText("");
                ((EditText) findViewById(R.id.textSurname)).setText("");
                ((EditText) findViewById(R.id.textEmail)).setText("");
            }
        } catch (SQLException ex){
                Log.e( "SQL EXCEPTION", ex.getMessage());
            }

        }
        }



