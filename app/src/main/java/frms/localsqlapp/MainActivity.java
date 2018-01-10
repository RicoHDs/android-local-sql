package frms.localsqlapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sm.datab.DataBase.DataBaseHandler;
import frms.localsqlapp.model.Contact;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView contactListView;
    private List<Contact> contactList;
    private Integer selectedIndex;
    private Contact selectedPerson;
    private final String LIFE_CYCLE="cycle de vie";
    private DataBaseHandler db;
    private ContactDAO dao;
    //private contactAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LIFE_CYCLE, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DataBaseHandler(this);

        this.dao = new ContactDAO(this.db);

        contactListView = findViewById(R.id.contactListView);
        contactListInit();
// récuperation des données dans le bundle
        //Button bt = findViewById(R.id.buttonBdon);
        //getMenuInflater().inflate(R.menu.main_option_menu, bt);
   if(savedInstanceState != null){
       this.selectedIndex = savedInstanceState.getInt("selectedIndex");
       if (this.selectedIndex != null){
           this.selectedPerson = this.contactList.get(this.selectedIndex);

         //  contactListView.requestFocus();
           contactListView.setSelection(this.selectedIndex);

           Log.i(LIFE_CYCLE, "selection :" +  contactListView.getSelectedItemId());
       }
   }

    }

    private void contactListInit() {
        contactList = this.getAllContacts();

        ContactArrayAdapter contactAdapter = new ContactArrayAdapter(this, contactList);

        contactListView.setAdapter(contactAdapter);
        contactListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Ajout entrée mennu
        //au menu contextuel de l'activité
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mainMenuOptionDelete:
                this.deleteSelectedContact();
                break;
            case R.id.mainMenuOptionEdit:

                break;
        }

        return true;
    }

    private void editSelectedContact() {
        Intent intention = new Intent(this, FormActivity.class);

        intention.putExtra("data", this.selectedPerson.get("id"));
        intention.putExtra("data", this.selectedPerson.get("name"));
        intention.putExtra("data", this.selectedPerson.get("surname"));
        intention.putExtra("data", this.selectedPerson.get("email"));

        startActivityForResult(intention, 1);
    }

    //suppression du contact selectionné
    private void deleteSelectedContact() {

        if (this.selectedIndex != null) {

            try {

                String sql = "DELETE FROM contacts WHERE id=?";
                String[] params = {this.selectedPerson.get("id")};

                DataBaseHandler db = new DataBaseHandler(this);
                db.getWritableDatabase().execSQL(sql, params);
                //Réinitialisation de la liste des contacts
                this.ContactListInit();

            } catch (SQLiteException ex) {
                Toast.makeText(this, "Impossible de supprimer", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Vous devez selectionner un contact", Toast.LENGTH_LONG).show();
        }

    }

    private void ContactListInit() {
        //Récupération de la liste des contacts
        contactListInit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Mise effectuée", Toast.LENGTH_LONG).show();

            this.contactListInit();

        }
    }

    public void onAddContact(View view) {
        if (view == findViewById(R.id.buttonAddContact)) {
            Intent FormIntent = new Intent(this, FormActivity.class);
            startActivity(FormIntent);


        }


    }

    private List<Map<String, String>> getAllContacts() {
        // instancie à la base de donnée
        DataBaseHandler db = new DataBaseHandler(this);

        // Excute une requete de selection
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT name, surname, email, id FROM contacts", null);

        //Instencie la liste
        List<Map<String, String>> contactList = new ArrayList<>();


        while (cursor.moveToNext()) {
            Map<String, String> contactCole = new HashMap<>();
            contactCole.put("name", cursor.getString(0));
            contactCole.put("surname", cursor.getString(1));
            contactCole.put("email", cursor.getString(2));
            contactCole.put("id", cursor.getString(3));

            // ajouter du map à mon Array List
            contactList.add(contactCole);

        }

        return contactList;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        this.selectedIndex = position;
        this.selectedPerson = contactList.get(position);
        Toast.makeText(this, "Ligne" + position + "cliquer", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LIFE_CYCLE, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(LIFE_CYCLE, "onPause");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Log.i(LIFE_CYCLE, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(LIFE_CYCLE, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(LIFE_CYCLE, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LIFE_CYCLE, "onDestroy");
}
// Persistance des données après destruction de l'activité
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    outState.putInt("SelectedIndex", this.selectedIndex);
        super.onSaveInstanceState(outState);
    }


}

