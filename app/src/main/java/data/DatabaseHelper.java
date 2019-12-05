package data;

import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.KEY_ID +
                " INTEGER PRIMARY KEY, " + Constants.FOOD_NAME + " TEXT,"
                + Constants.FOOD_CALORIES_NAME + " INT, " + Constants.DATE_NAME + " LONG);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);
    }


    public int getTotalItems() {
        int totalItems = 0;
        String query = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase dba = this.getReadableDatabase();
        Cursor cursor = dba.rawQuery(query, null);
        totalItems = cursor.getCount();
        cursor.close();
        return totalItems;
    }

    public int totalCalories() {
        int cals = 0;
        SQLiteDatabase dba = this.getReadableDatabase();
        String query = "SELECT SUM( " + Constants.FOOD_CALORIES_NAME + " ) " +
                "FROM " + Constants.TABLE_NAME;
        Cursor cursor = dba.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cals = cursor.getInt(0);
        }

        cursor.close();
        dba.close();

        return cals;
    }

    public void deleteFood(int id) {
        SQLiteDatabase dba = this.getWritableDatabase();
        dba.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        dba.close();
    }

    
}