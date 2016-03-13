package staunstrups.dk.tingle;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.*;
import android.util.Log;

/*
TingleV4: with SQLite as introduced in the Big Nerd book
 */

public class TingleActivity extends FragmentActivity implements UIFragment.toActivity {
  private static String TAG= "TingleActivity";

  Fragment fragmentListLand, fragmentUILand;

  @Override
  public void stateChange() {
    //Update list in landscape fragment
    if ((fragmentListLand != null)) ((ThingListFragment) fragmentListLand).stateChange();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tingle);
    FragmentManager fm = getSupportFragmentManager();

    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

      fragmentUILand= fm.findFragmentById(R.id.fragment_container_ui_landscape);
      fragmentListLand= fm.findFragmentById(R.id.fragment_container_list);

      if ((fragmentUILand == null) && (fragmentListLand == null)) {

        fragmentUILand = new UIFragment();
        fragmentListLand= new ThingListFragment();
        fm.beginTransaction()
            .add(R.id.fragment_container_ui_landscape, fragmentUILand)
            .add(R.id.fragment_container_list, fragmentListLand)
            .commit();
      }
    } else {
      //Orientation portrait
      Fragment fragmentUI = fm.findFragmentById(R.id.fragment_container_ui_portrait);
      if (fragmentUI == null) {
        fragmentUI = new UIFragment();
        fm.beginTransaction()
            .add(R.id.fragment_container_ui_portrait, fragmentUI)
            .commit();
      }
    }
  }
}
