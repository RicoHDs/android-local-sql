package frms.localsqlapp;

import android.database.Cursor;
import fr.sm.datab.DataBase.DataBaseHandler;
import android.database.SQLException;

import frms.localsqlapp.model.Contact;

/**
 * Created by Formation on 10/01/2018.
 */

public class ContactDAO {
    private DataBaseHandler db;

    public ContactDAO(DataBaseHandler db){
        this.db=db;
    }
    public Contact findOneById(long id)throws SQLException{
        String[] params ={String.valueOf(id)};
        String sql=  "SELECT id, name, surname, email  FROM contacts WHERE id=2";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, params) ;
        Contact contact = new Contact();
        if(cursor.moveToNext()){
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setSurname(cursor.getString(2));
            contact.setEmail(cursor.getString(3));

        }
        cursor.close();

        return contact;
    }
}
