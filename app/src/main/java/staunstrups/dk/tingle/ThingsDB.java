package staunstrups.dk.tingle;
/* Singleton to hold the database of things */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import staunstrups.dk.tingle.database.ThingsBaseHelper;
import staunstrups.dk.tingle.database.ThingsCursorWrapper;
import staunstrups.dk.tingle.database.ThingsDbSchema.*;

public class ThingsDB {
  private static ThingsDB sThingsDB;

  private Context mContext;
  private SQLiteDatabase mDatabase;

  public static ThingsDB get(Context context) {
    if (sThingsDB == null) sThingsDB= new ThingsDB(context);
    return sThingsDB;
  }

  public List<Thing> getThingsDB() {
    List<Thing> things= new ArrayList<Thing>();
    ThingsCursorWrapper cursor = queryThings(null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      things.add(cursor.getThing());
      cursor.moveToNext();
    }
    cursor.close();
    return things;
  }

  public void addThing(Thing thing) {
    ContentValues values= getContentValues(thing);
    mDatabase.insert(ThingTable.NAME, null, values);
  }

  private static ContentValues getContentValues(Thing thing){
    ContentValues values = new ContentValues();
    values.put(ThingTable.Cols.WHAT, thing.getWhat());
    values.put(ThingTable.Cols.WHERE, thing.getWhere());
    return values;
  }

  private ThingsDB(Context context) {
    mContext= context.getApplicationContext();
    mDatabase= new ThingsBaseHelper(mContext).getWritableDatabase();

      /* Hack to add contents to an empty database
    addThing(new Thing("Mouse", "Desk"));
    addThing(new Thing("iPhone", "Desk"));
    addThing(new Thing("Sunlasses", "Desk"));
    addThing(new Thing("Keyboard", "Desk"));
    addThing(new Thing("Display", "Desk"));
    addThing(new Thing("Computer", "Desk"));
    addThing(new Thing("Big Nerd book", "Desk"));
 */
  }

  private ThingsCursorWrapper queryThings(String whereClause, String[] whereArgs) {
    Cursor cursor = mDatabase.query(
        ThingTable.NAME,
        null, // Columns - null selects all columns
        whereClause, whereArgs,
        null, // groupBy
        null, // having
        null  // orderBy
    );
    return new ThingsCursorWrapper(cursor);
  }
}