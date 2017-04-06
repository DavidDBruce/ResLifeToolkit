package com.example.a33528.reslifetoolkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by s522706 on 4/6/2017.
 */



public class MySQLiteHelper extends SQLiteOpenHelper {

    // Books table name
    private static final String TABLE_PF = "programmingforms";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_RANAME = "raname";
    private static final String KEY_HALLNAME = "hallname";
    private static final String KEY_FLOORNUM = "floornum";
    private static final String KEY_EVENTTITLE = "eventtitle";
    private static final String KEY_EVENTREASON = "eventreason";
    private static final String KEY_EVENTDESCRIPTION = "eventdescription";
    private static final String KEY_EVENTPUBLICITY = "eventpublicity";
    private static final String KEY_EVENTDATE = "eventdate";
    private static final String KEY_COST = "cost";
    private static final String KEY_ATTENDEES = "attendees";
    private static final String KEY_GOALS = "goals";
    private static final String KEY_POSITIONFORDELETE = "positionfordelete";
    private static final String KEY_ISEVENT = "isevent";

    private static final String[] PF_COLUMNS = {KEY_ID,KEY_RANAME,KEY_HALLNAME,KEY_FLOORNUM,KEY_EVENTTITLE,KEY_EVENTREASON,KEY_EVENTDESCRIPTION,KEY_EVENTPUBLICITY,KEY_EVENTDATE,KEY_COST,KEY_ATTENDEES,KEY_GOALS,KEY_POSITIONFORDELETE,KEY_ISEVENT};

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResLifeToolkit.db";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PF_TABLE = "CREATE TABLE programmingforms ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "raname TEXT, "+ "hallname TEXT, " + "floornum TEXT, " + "eventtitle TEXT, " + "eventreason TEXT, " + "eventdescription TEXT, " + "eventpublicity TEXT, " + "eventdate TEXT, " + "cost TEXT, " + "attendees TEXT, " + "goals TEXT, "+ "positionfordelete INTEGER, " + "isevent INTEGER";
        db.execSQL(CREATE_PF_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addProgrammingForm(ProgrammingForm pf)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues curEntry = new ContentValues();

        curEntry.put("raname", pf.getRaName());
        curEntry.put("hallname", pf.getHallName());
        curEntry.put("floornum", pf.getHallFloor());
        curEntry.put("eventtitle", pf.getEventTitle());
        curEntry.put("eventreason", pf.getEventReason());
        curEntry.put("eventdescription", pf.getEventDescription());
        curEntry.put("eventpublicity", pf.getEventPublicity());
        curEntry.put("eventdate", pf.getEventDate());
        curEntry.put("cost", pf.getCost());
        curEntry.put("attendees", pf.getAttendees());
        curEntry.put("goals", pf.getGoals());
        //curEntry.put("positionfordelete", pf.getPositionForDelete());
        if(pf.getIsEvent()){
            curEntry.put("isevent", 1);}
        else{
            curEntry.put("isevent", 0);}

        db.insert(TABLE_PF,null,curEntry);

        db.close();
    }

}