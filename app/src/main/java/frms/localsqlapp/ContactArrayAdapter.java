package frms.localsqlapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import frms.localsqlapp.model.Contact;

/**
 * Created by Formation on 09/01/2018.
 */

public class ContactArrayAdapter extends ArrayAdapter {

    private Activity context;
    private List<Contact> data;
   // private int resource;
    private LayoutInflater Inflater;


    public ContactArrayAdapter(Context context, @NonNull List<Contact> data) {
        super(context, 0, data);

        this.data = data;
      //  this.resource = resource;
        this.context = (Activity) context;
        this.Inflater = this.context.getLayoutInflater();
    }


    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = this.Inflater.inflate(R.layout.listview_contacts, parent, false);

        Contact contactData = this.data.get(position);

        TextView nameTextView = view.findViewById(R.id.textName);
        nameTextView.setText(contactData.getName());

        TextView surnameTextViewButtonTextView = view.findViewById(R.id.ListtextSurnamebutton);
        surnameTextViewButtonTextView.setText(contactData.getSurname());

        TextView emailTextViewButtonTextView = view.findViewById(R.id.textEmail);
        emailTextViewButtonTextView.setText(contactData.getEmail());

        return view;
    }
}
