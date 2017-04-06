package com.example.a33528.reslifetoolkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by s522706 on 4/6/2017.
 */



public class MySQLiteHelper extends SQLiteOpenHelper {

    // Table Name
    private static final String TABLE_PF = "programmingform";

    //Programming Form Column Names
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

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResLifeToolkitDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PF_TABLE = "CREATE TABLE IF NOT EXISTS programmingform ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "raname TEXT, "+ "hallname TEXT, " + "floornum TEXT, " + "eventtitle TEXT, " + "eventreason TEXT, " + "eventdescription TEXT, " + "eventpublicity TEXT, " + "eventdate TEXT, " + "cost TEXT, " + "attendees TEXT, " + "goals TEXT, "+ "positionfordelete INTEGER, " + "isevent INTEGER" + ")";
        db.execSQL(CREATE_PF_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS programmingform");
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
        curEntry.put("positionfordelete", pf.getPositionForDelete());
        if(pf.getIsEvent()){
            curEntry.put("isevent", 1);}
        else{
            curEntry.put("isevent", 0);}

        db.insert(TABLE_PF,null,curEntry);

        Log.d("addForm", "Form added " + pf.getRaName());

        db.close();
    }

    public ProgrammingForm getProgrammingForm(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PF, // a. table
                        PF_COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        ProgrammingForm pf = new ProgrammingForm();
        pf.setId(Integer.parseInt(cursor.getString(0)));
        pf.setRaName(cursor.getString(1));
        pf.setHallName(cursor.getString(2));
        pf.setHallFloor(cursor.getString(3));
        pf.setEventTitle(cursor.getString(4));
        pf.setEventReason(cursor.getString(5));
        pf.setEventDescription((cursor.getString(6)));
        pf.setEventPublicity(cursor.getString(7));
        pf.setEventDate(cursor.getString(8));
        pf.setCost(cursor.getString(9));
        pf.setAttendees(cursor.getString(10));
        pf.setGoals(cursor.getString(11));
        pf.setPositionForDelete(Integer.parseInt(cursor.getString(12)));

        if(Integer.parseInt(cursor.getString(13) )== 1){
            pf.setIsEvent(true);}
        else{
            pf.setIsEvent(false);}

        //log
        Log.d("getForm("+id+")", pf.toString());

        // 5. return book
        return pf;
    }

    public ArrayList<ProgrammingForm> getAllProgrammingForms() {
        ArrayList<ProgrammingForm> pfs = new ArrayList<ProgrammingForm>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PF;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ProgrammingForm pf;
        if (cursor.moveToFirst()) {
            do {
                pf = new ProgrammingForm();
                pf.setId(Integer.parseInt(cursor.getString(0)));
                pf.setRaName(cursor.getString(1));
                pf.setHallName(cursor.getString(2));
                pf.setHallFloor(cursor.getString(3));
                pf.setEventTitle(cursor.getString(4));
                pf.setEventReason(cursor.getString(5));
                pf.setEventDescription((cursor.getString(6)));
                pf.setEventPublicity(cursor.getString(7));
                pf.setEventDate(cursor.getString(8));
                pf.setCost(cursor.getString(9));
                pf.setAttendees(cursor.getString(10));
                pf.setGoals(cursor.getString(11));
                pf.setPositionForDelete(Integer.parseInt(cursor.getString(12)));

                if(Integer.parseInt(cursor.getString(13) )== 1){
                    pf.setIsEvent(true);}
                else{
                    pf.setIsEvent(false);}
                pfs.add(pf);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", pfs.toString());

        // return books
        return pfs;
    }


}