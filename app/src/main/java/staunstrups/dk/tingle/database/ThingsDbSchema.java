package staunstrups.dk.tingle.database;

public class ThingsDbSchema {

  public static final class ThingTable {
    public static final String NAME= "things";

    public static final class Cols {
      //public static final String UUID="uuid";
      public static final String WHAT="what";
      public static final String WHERE="whereC";  //where is a keyword in SQL
    }
  }
}
