package com.example.a33528.reslifetoolkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Table Name
    private static final String TABLE_PF = "programmingform";
    private static final String TABLE_DL = "dutylogs";
    private static final String TABLE_RA = "ra";
    private static final String TABLE_BD = "building";

    //RA Column Names
    private static final String KEY_RAID = "raid";

    //Building Column Names
    private static final String KEY_BUILDINGID = "buildingid";

    //Programming Form Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_FLOOR = "floor";
    private static final String KEY_TITLE = "title";
    private static final String KEY_REASON = "reason";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PUBLICITY = "publicity";
    private static final String KEY_DATETIME = "datetime";
    private static final String KEY_COST = "cost";
    private static final String KEY_ATTENDEES = "attendees";
    private static final String KEY_GOALS = "goals";
    private static final String KEY_POSITIONFORDELETE = "positionfordelete";
    private static final String KEY_ISEVENT = "isevent";

    //Duty Log Column Names
    private static final String KEY_8ROUNDS = "eigthrounds";
    private static final String KEY_10ROUNDS = "tenrounds";
    private static final String KEY_12ROUNDS = "twelverounds";
    private static final String KEY_2ROUNDS = "tworounds";
    private static final String KEY_DAYROUNDS = "dayrounds";
    private static final String KEY_DOCUMENTATIONS = "documentations";
    private static final String KEY_WORKORDERS = "workorders";
    private static final String KEY_LOGDATE = "logdate";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ResLifeToolkitDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String CREATE_RA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RA + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT"
                + " )";
        db.execSQL(CREATE_RA_TABLE);

        String CREATE_BD_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BD + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT"
                + " )";
        db.execSQL(CREATE_BD_TABLE);

        String CREATE_PF_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PF + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_RAID + " INT, "
                + KEY_BUILDINGID +" INT, "
                + KEY_FLOOR +" TEXT, "
                + KEY_TITLE +" TEXT, "
                + KEY_REASON +" TEXT, "
                + KEY_DESCRIPTION +" TEXT, "
                + KEY_PUBLICITY +" TEXT, "
                + KEY_DATETIME +" TEXT, "
                + KEY_COST +" TEXT, "
                + KEY_ATTENDEES + " TEXT, "
                + KEY_GOALS + " TEXT, "
                + KEY_POSITIONFORDELETE + " INTEGER, "
                + KEY_ISEVENT + " BIT, "
                + "FOREIGN KEY(" + KEY_RAID + ") REFERENCES " + TABLE_RA + "(" + KEY_ID + "), "
                + "FOREIGN KEY(" + KEY_BUILDINGID + ") REFERENCES " + TABLE_BD + "(" + KEY_ID + "), "
                + " )";
        db.execSQL(CREATE_PF_TABLE);

        String CREATE_DL_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_DL + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " //0
                + KEY_NAME + " TEXT, "                            //1
                + KEY_8ROUNDS + " TEXT, "                         //2
                + KEY_10ROUNDS + " TEXT, "                        //3
                + KEY_12ROUNDS + " TEXT, "                        //4
                + KEY_2ROUNDS + " TEXT, "                         //5
                + KEY_DAYROUNDS + " TEXT, "                       //6
                + KEY_DOCUMENTATIONS + " TEXT, "                  //7
                + KEY_WORKORDERS + " TEXT, "                      //8
                + KEY_POSITIONFORDELETE + " INTEGER, "            //9
                + KEY_LOGDATE + " TEXT"                           //10
                + ")";

        db.execSQL(CREATE_DL_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS programmingform");
        db.execSQL("DROP TABLE IF EXISTS dutylogs");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addProgrammingForm(ProgrammingForm pf) {
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
        curEntry.put("eventtime",pf.getEventTime());

        db.insert(TABLE_PF,null,curEntry);
        db.close();
    }

    public void deleteProgrammingForm(ProgrammingForm pf) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PF, KEY_ID+" = ?", new String[] { String.valueOf(pf.getId()) });

        db.close();
    }

    public ArrayList<ProgrammingForm> getAllProgrammingForms() {
        ArrayList<ProgrammingForm> pfs = new ArrayList<ProgrammingForm>();

        String query = "SELECT  * FROM " + TABLE_PF;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

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
                pf.setEventTime(cursor.getString(14));
                pfs.add(pf);
            } while (cursor.moveToNext());
        }

        return pfs;
    }

    public void clearProgrammingForms() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS programmingform");
        onCreate(db);
    }

    public void clearDutyLogs() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS dutylogs");
        onCreate(db);
    }

    public int updateProgrammingForm(ProgrammingForm pf) {

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
        curEntry.put("eventtime", pf.getEventTime());

        int i = db.update(TABLE_PF, curEntry, KEY_ID+" = ?", new String[] { String.valueOf(pf.getId()) });

        db.close();

        return i;

    }

    public void addDutyLog(DutyLog dl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues curEntry = new ContentValues();


        curEntry.put(KEY_NAME, dl.getRaOnDuty());//1
        curEntry.put(KEY_8ROUNDS,dl.getRound8());//2
        curEntry.put(KEY_10ROUNDS,dl.getRound10());//3
        curEntry.put(KEY_12ROUNDS,dl.getRound12());//4
        curEntry.put(KEY_2ROUNDS,dl.getRound2());//5
        curEntry.put(KEY_DAYROUNDS, dl.getRoundDay());//6
        curEntry.put(KEY_DOCUMENTATIONS, dl.getDocumentations());//7
        curEntry.put(KEY_WORKORDERS, dl.getWorkOrders());//8
        curEntry.put(KEY_POSITIONFORDELETE, dl.getPositionForDelete());//9
        curEntry.put(KEY_LOGDATE, dl.getLogDate());//10

        db.insert(TABLE_DL,null,curEntry);
        db.close();

    }

    public void deleteDutyLog(DutyLog dl) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DL, KEY_ID+" = ?", new String[] { String.valueOf(dl.getId()) });

        db.close();

    }

    public ArrayList<DutyLog> getAllDutyLogs() {
        ArrayList<DutyLog> dls = new ArrayList<DutyLog>();

        String query = "SELECT  * FROM " + TABLE_DL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        DutyLog dl;
        if (cursor.moveToFirst()) {
            do {
                dl = new DutyLog();
                dl.setId(Integer.parseInt(cursor.getString(0)));
                dl.setRaOnDuty((cursor.getString(1)));
                dl.setRound8(cursor.getString(2));
                dl.setRound10(cursor.getString(3));
                dl.setRound12(cursor.getString(4));
                dl.setRound2(cursor.getString(5));
                dl.setRoundDay(cursor.getString(6));
                dl.setDocumentations(cursor.getString(7));
                dl.setWorkOrders(cursor.getString(8));
                dl.setPositionForDelete(Integer.parseInt(cursor.getString(9)));
                dl.setLogDate(cursor.getString(10));
                dls.add(dl);
            } while (cursor.moveToNext());
        }

        return dls;
    }

    public int updateDutyLog(DutyLog dl) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues curEntry = new ContentValues();

        curEntry.put(KEY_NAME, dl.getRaOnDuty());//1
        curEntry.put(KEY_8ROUNDS,dl.getRound8());//2
        curEntry.put(KEY_10ROUNDS,dl.getRound10());//3
        curEntry.put(KEY_12ROUNDS,dl.getRound12());//4
        curEntry.put(KEY_2ROUNDS,dl.getRound2());//5
        curEntry.put(KEY_DAYROUNDS, dl.getRoundDay());//6
        curEntry.put(KEY_DOCUMENTATIONS, dl.getDocumentations());//7
        curEntry.put(KEY_WORKORDERS, dl.getWorkOrders());//8
        curEntry.put(KEY_POSITIONFORDELETE, dl.getPositionForDelete());//9
        curEntry.put(KEY_LOGDATE, dl.getLogDate());//10

        int i = db.update(TABLE_DL, curEntry, KEY_ID+" = ?", new String[] { String.valueOf(dl.getId()) });

        db.close();

        return i;

    }
}