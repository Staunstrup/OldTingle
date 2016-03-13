package staunstrups.dk.tingle;

import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;

public class ListActivity extends FragmentActivity  {
  private static String TAG= "ListActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragmentList = fm.findFragmentById(R.id.fragment_container_list);
    if (fragmentList == null) {
      fragmentList = new ThingListFragment();
      fm.beginTransaction()
          .add(R.id.fragment_container_list, fragmentList)
          .commit();
    }
  }
}
