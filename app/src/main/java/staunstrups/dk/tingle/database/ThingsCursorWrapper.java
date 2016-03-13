package staunstrups.dk.tingle.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import staunstrups.dk.tingle.Thing;

public class ThingsCursorWrapper extends CursorWrapper {
  public ThingsCursorWrapper(Cursor cursor) {super(cursor);}
  public Thing getThing(){
    String what= getString(getColumnIndex(ThingsDbSchema.ThingTable.Cols.WHAT));
    String where= getString(getColumnIndex(ThingsDbSchema.ThingTable.Cols.WHERE));
    return new Thing(what, where);
  }
}
