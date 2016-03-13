package staunstrups.dk.tingle.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jst on 3/5/16.
 */
public class ThingsBaseHelper extends SQLiteOpenHelper {

  private static final String TAG = "ThingBaseHelper";
  private static final int VERSION = 1;
  private static final String DATABASE_NAME = "thingBase.db";

  public ThingsBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + ThingsDbSchema.ThingTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            //ThingTable.Cols.UUID + ", " +
            ThingsDbSchema.ThingTable.Cols.WHAT + ", " + ThingsDbSchema.ThingTable.Cols.WHERE  + ")"
    );
  }
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  }
}
