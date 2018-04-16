package com.eap.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.eap.model.Form;
import com.eap.model.Model;

import java.util.ArrayList;
import java.util.List;

public class FormDatabaseAdapter  {
    static final String DATABASE_NAME = "database.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table FORM( ID integer primary key autoincrement,NAME  text,MITROO  number,TMIMA text,EPIDOSI double,KAT_METAKINISIS text, FOREAS text, " +
                                          "FOREAS_YPODOXIS text, COUNTRY_LAT double, COUNTRY_LNG double, COUNTRY text, METAKINISH text );";

    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;

    public  FormDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to openthe Database
    public  FormDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public static boolean checkDuplicate(Form form) {

        String query = "select * from FORM where MITROO="+form.getMitroo();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public String insertEntry(Form form)
    {
        boolean check = true;
        check = checkDuplicate(form);
        if (check){
            Toast.makeText(context, "User Already Exists", Toast.LENGTH_LONG).show();
        } else {
            Log.d("insertEntry", "database path:" + context.getDatabasePath(DATABASE_NAME));
            try {
                ContentValues newValues = new ContentValues();
                newValues.put("NAME", form.getName());
                newValues.put("MITROO", form.getMitroo());
                newValues.put("TMIMA", form.getTmima());
                newValues.put("EPIDOSI", form.getEpidosi());
                newValues.put("KAT_METAKINISIS", form.getKat_metakinisis());
                newValues.put("FOREAS", form.getForeas());
                newValues.put("FOREAS_YPODOXIS", form.getForeas_ipodoxis());
                newValues.put("COUNTRY_LAT", form.getCountry_lat());
                newValues.put("COUNTRY_LNG", form.getCountry_lng());
                newValues.put("METAKINISH", form.getMetakinisi());
                newValues.put("COUNTRY", form.getCountry());

                // Insert the row into your table
                db = dbHelper.getWritableDatabase();
                long result = db.insert("FORM", null, newValues);
                Log.d("insertEntry", "result" + result);
                Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                System.out.println("Exceptions " + ex);
                Log.e("Note", "One row entered");
            }
        }
        return ok;
    }

    public List<Model> getAllByGrade(){
        List<Model> models = new ArrayList<Model>();
        db = dbHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        Cursor cursor = db.rawQuery("select * from FORM order by EPIDOSI desc", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setName(cursor.getString(cursor.getColumnIndex(Model.COLUMN_NAME)));
                model.setMitroo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Model.COLUMN_MITROO))));
                model.setTmima(cursor.getString(cursor.getColumnIndex(Model.COLUMN_TMIMA)));
                model.setEpidosi(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_EPIDOSI))));
                model.setKat_metakinisis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_KAT_METAKINISIS)));
                model.setForeas(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS)));
                model.setForeas_ipodoxis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS_YPODOXIS)));
                model.setCountry_lat(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LAT))));
                model.setCountry_lng(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LNG))));
                model.setCountry(cursor.getString(cursor.getColumnIndex(Model.COLUMN_COUNTRY )));
                model.setMetakinisi(cursor.getString(cursor.getColumnIndex(Model.COLUMN_METAKINISH)));
                models.add(model);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        return models;
    }

    public List<Model> getByName(String name){
        List<Model> models = new ArrayList<Model>();
        db = dbHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }

        String query = "select * from FORM where NAME='"+name+"' order by EPIDOSI desc";
        Cursor cursor = db.rawQuery(query, null);
        Log.d("name cursor","cursor:"+cursor.getCount());
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setName(cursor.getString(cursor.getColumnIndex(Model.COLUMN_NAME)));
                model.setMitroo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Model.COLUMN_MITROO))));
                model.setTmima(cursor.getString(cursor.getColumnIndex(Model.COLUMN_TMIMA)));
                model.setEpidosi(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_EPIDOSI))));
                model.setKat_metakinisis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_KAT_METAKINISIS)));
                model.setForeas(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS)));
                model.setForeas_ipodoxis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS_YPODOXIS)));
                model.setCountry_lat(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LAT))));
                model.setCountry_lng(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LNG))));
                model.setMetakinisi(cursor.getString(cursor.getColumnIndex(Model.COLUMN_METAKINISH)));
                model.setCountry(cursor.getString(cursor.getColumnIndex(Model.COLUMN_COUNTRY )));

                Log.d("name",""+model.toString());
                models.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("models",""+models.toString());
        // close db connection
        db.close();
        return models;
    }

    public List<Model> getByCategory(String category) {
        List<Model> models = new ArrayList<Model>();
        db = dbHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        String query = "select * from FORM where KAT_METAKINISIS='"+category+"' order by EPIDOSI desc";
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setName(cursor.getString(cursor.getColumnIndex(Model.COLUMN_NAME)));
                model.setMitroo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Model.COLUMN_MITROO))));
                model.setTmima(cursor.getString(cursor.getColumnIndex(Model.COLUMN_TMIMA)));
                model.setEpidosi(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_EPIDOSI))));
                model.setKat_metakinisis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_KAT_METAKINISIS)));
                model.setForeas(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS)));
                model.setForeas_ipodoxis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS_YPODOXIS)));
                model.setCountry_lat(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LAT))));
                model.setCountry_lng(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LNG))));
                model.setMetakinisi(cursor.getString(cursor.getColumnIndex(Model.COLUMN_METAKINISH)));
                model.setCountry(cursor.getString(cursor.getColumnIndex(Model.COLUMN_COUNTRY )));
                models.add(model);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        return models;
    }

    public List<Model> getByForea(String foreas) {
        List<Model> models = new ArrayList<Model>();
        db = dbHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        String query = "select * from FORM where FOREAS='"+foreas+"' order by EPIDOSI desc";
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setName(cursor.getString(cursor.getColumnIndex(Model.COLUMN_NAME)));
                model.setMitroo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Model.COLUMN_MITROO))));
                model.setTmima(cursor.getString(cursor.getColumnIndex(Model.COLUMN_TMIMA)));
                model.setEpidosi(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_EPIDOSI))));
                model.setKat_metakinisis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_KAT_METAKINISIS)));
                model.setForeas(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS)));
                model.setForeas_ipodoxis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS_YPODOXIS)));
                model.setCountry_lat(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LAT))));
                model.setCountry_lng(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LNG))));
                model.setMetakinisi(cursor.getString(cursor.getColumnIndex(Model.COLUMN_METAKINISH)));
                model.setCountry(cursor.getString(cursor.getColumnIndex(Model.COLUMN_COUNTRY )));
                models.add(model);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        return models;
    }


    public List<Model> getByOldMove(String metakinisi) {
        List<Model> models = new ArrayList<Model>();
        db = dbHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        String query = "select * from FORM where METAKINISH='"+metakinisi+"' order by EPIDOSI desc";
        Cursor cursor = db.rawQuery(query, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setName(cursor.getString(cursor.getColumnIndex(Model.COLUMN_NAME)));
                model.setMitroo(String.valueOf(cursor.getInt(cursor.getColumnIndex(Model.COLUMN_MITROO))));
                model.setTmima(cursor.getString(cursor.getColumnIndex(Model.COLUMN_TMIMA)));
                model.setEpidosi(String.valueOf(cursor.getDouble  (cursor.getColumnIndex(Model.COLUMN_EPIDOSI))));
                model.setKat_metakinisis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_KAT_METAKINISIS)));
                model.setForeas(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS)));
                model.setForeas_ipodoxis(cursor.getString(cursor.getColumnIndex(Model.COLUMN_FOREAS_YPODOXIS)));
                model.setCountry_lat(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LAT))));
                model.setCountry_lng(String.valueOf(cursor.getDouble(cursor.getColumnIndex(Model.COLUMN_COUNTRY_LNG))));
                model.setMetakinisi(cursor.getString(cursor.getColumnIndex(Model.COLUMN_METAKINISH)));
                model.setCountry(cursor.getString(cursor.getColumnIndex(Model.COLUMN_COUNTRY )));
                models.add(model);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        return models;
    }


    // method to delete a Record of User Form
    public int deleteEntry(String mitroo)
    {
        String where="MITROO=?";
        db = dbHelper.getReadableDatabase();
        int numberOFEntriesDeleted= db.delete("FORM", where, new String[]{mitroo}) ;
        Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        // close db connection
        db.close();
        return numberOFEntriesDeleted;
    }
}
