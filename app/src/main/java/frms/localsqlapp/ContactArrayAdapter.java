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

/**
 * Created by Formation on 09/01/2018.
 */

public class ContactArrayAdapter extends ArrayAdapter {

    private Activity context;
    private List<Map<String, String>>data;
   // private int resource;
    private LayoutInflater Inflater;


    public ContactArrayAdapter(Context context, @NonNull List<Map<String, String>> data) {
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

        Map<String, String> contactData = this.data.get(position);

        TextView nameTextView = view.findViewById(R.id.textName);
        nameTextView.setText(contactData.get("name"));

        TextView surnameTextViewButtonTextView = view.findViewById(R.id.ListtextSurnamebutton);
        nameTextView.setText(contactData.get("surname"));

        TextView emailTextViewButtonTextView = view.findViewById(R.id.textEmail);
        nameTextView.setText(contactData.get("email"));

        return view;
    }
}
