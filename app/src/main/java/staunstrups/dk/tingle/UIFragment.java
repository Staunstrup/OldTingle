package staunstrups.dk.tingle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UIFragment extends Fragment {
  private static String TAG= "TingleFragment";

  // GUI variables
  private Button addThing, listThings;
  private TextView lastAdded;
  private TextView newWhat, newWhere;

  //Database of things
  private static ThingsDB thingsDB;

  public interface toActivity { void stateChange(); }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    thingsDB = ThingsDB.get(getActivity());
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

    final View v = inflater.inflate(R.layout.fragment_ui, container, false);

    // Button add thing
    addThing = (Button) v.findViewById(R.id.add_button);
    addThing.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)) {
          thingsDB.addThing(new Thing(newWhat.getText().toString(),
              newWhere.getText().toString()));
          newWhat.setText("");
          newWhere.setText("");
           ((toActivity) getActivity()).stateChange();
        }
      }
    });

    //Log.i(TAG, "Orientation: " + getResources().getConfiguration().orientation);
    //BUtton list things in portrait
    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      listThings = (Button) v.findViewById(R.id.list_button);
      listThings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          // Start ListActivity
          Intent i = new Intent(getActivity(), ListActivity.class);
          startActivity(i);
        }
      });
    }

    // Textfields for describing a thing
    newWhat= (TextView) v.findViewById(R.id.what_text);
    newWhere= (TextView) v.findViewById(R.id.where_text);

    return v;
  }
}