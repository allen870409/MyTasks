package jp.co.qyc.mytasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A13054 on 2015/07/24.
 */
public class MyDatabaseHandler extends SQLiteOpenHelper{
    private static final String DB_NAME = "my_tasks1";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE="create table task" + "( _id integer primary" +
            " key autoincrement, title String not null, priority integer not null, date String not null);";
    SQLiteDatabase db;

    public MyDatabaseHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE);
        for(int i = 0; i< 20 ; i++){
            insert("test" + i, "19631122121211" + i);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Task find(String id){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from task where _id = ?", new String[]{id});
        Task task = null;
        if(cursor != null){
            cursor.moveToFirst();
            task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
        }
        return task;
    }

    public void update(String id, String title, String date){
        db.execSQL("update task set title = ?, date = ? where _id = ?", new String[] {title, date, id});
    }


    public void insert(String title, String date){
        db.execSQL("insert into task values(null, ?, ?)", new String[] {title, date});
    }

    public List<Task> getALllContact(){
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery="SELECT * FROM task";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
                tasks.add(task);
            }while(cursor.moveToNext());
        }

        System.out.println("-------------" + tasks.size());

        return tasks;
    }
}
