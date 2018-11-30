package marian.diaz.com.databasesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dhelper extends SQLiteOpenHelper {
    final static String DBNAME = "Students.db";
    final static int VER = 1;
    final static String TABLE = "scores";

    public dhelper(Context context) {
        super(context, DBNAME, null, VER);
        //this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE scores (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT," +
                "Lname TEXT, Grade INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = "DROP TABLE IF EXISTS scores";
        db.execSQL(deleteTable);
        onCreate(db);
    }

    public boolean insert(String fname, String lname, int grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname", fname);
        cv.put("Lname", lname);
        cv.put("Grade", grade);
        long inserted = db.insert(TABLE, null, cv);
        if (inserted != -1 ){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor populateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM scores", null);

    }


    public boolean update(String id, String fname, String lname, int grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Fname", fname);
        cv.put("Lname", lname);
        cv.put("Grade", grade);
       db.update(TABLE, cv, "ID=?", new String[]{id});
       return true;

    }
}
