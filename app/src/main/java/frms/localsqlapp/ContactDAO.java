package frms.localsqlapp;

import android.database.Cursor;
import fr.sm.datab.DataBase.DataBaseHandler;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;

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
          contact=  hydrateContact(cursor);

        }
        cursor.close();

        return contact;
    }

    private Contact hydrateContact(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getInt(0));
        contact.setName(cursor.getString(1));
        contact.setSurname(cursor.getString(2));
        contact.setEmail(cursor.getString(3));

        return contact;
    }

    public List<Contact> findAll() {
        List<Contact> contactList = new ArrayList<>();
        //  return  contactList;

        String sql = "SELECT id, name, surname, email  FROM contacts";
        Cursor cursor = this.db.getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            contactList.add(this.hydrateContact(cursor));
        }
            cursor.close();
            return contactList;
        }
        //Suression simple en fonction de sa clé primaire
        public void deleteOneById(Long id) throws  SQLException{
            String[] params = {id.toString()};
            String sql = "DELETE FROM contacts WHERE id=?";
            this.db.getWritableDatabase().execSQL(sql,params);
        }
    }

