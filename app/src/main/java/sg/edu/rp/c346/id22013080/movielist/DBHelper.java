package sg.edu.rp.c346.id22013080.movielist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int database_ver = 1;
    private static final String database_name = "movie.db";
    private static final String table_name = "movie";
    private static final String column_id = "_id";
    private static final String column_title = "title";
    private static final String column_genre = "genre";
    private static final String column_year = "year";
    private static final String column_rating = "rating";

    public DBHelper(Context context){
        super(context, database_name, null, database_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + table_name + "(" + column_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + column_title + " TEXT," + column_genre + " TEXT," + column_year + " INTEGER," + column_rating + " TEXT )";
        db.execSQL(createTable);
        Log.i("info", "created table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void insertMovie(String title, String genre, int year, String rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(column_title, title);
        values.put(column_genre, genre);
        values.put(column_year, year);
        values.put(column_rating, rating);

        db.insert(table_name, null, values);
        db.close();
    }

    public ArrayList<Movies> getMovie(){
        ArrayList<Movies> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {column_id, column_title, column_genre, column_year, column_rating};
        Cursor cursor = db.query(table_name, columns, null,null, null, null, null);

        if (cursor.moveToFirst()){
            do{
              int id = cursor.getInt(0);
              String title = cursor.getString(1);
              String genre = cursor.getString(2);
              int year = cursor.getInt(3);
              String rating = cursor.getString(4);
              Movies obj = new Movies(id, title,genre,year,rating);
              movies.add(obj);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return movies;
    }

    public int updateMovie(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(column_title, data.getTitle());
        values.put(column_genre, data.getGenre());
        values.put(column_year, data.getYear());
        values.put(column_rating, data.getRating());
        String condition = column_id + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(table_name, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = column_id + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(table_name, condition, args);
        db.close();
        return result;
    }
}
