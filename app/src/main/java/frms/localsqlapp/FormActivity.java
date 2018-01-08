package frms.localsqlapp;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.sm.datab.DataBase.DataBaseHandler;


public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }
    public  void onValide(View v){
        //ref du bouton
        Button clickButton =(Button) v;

        DataBaseHandler ab= new DataBaseHandler(this);


            }
        }



