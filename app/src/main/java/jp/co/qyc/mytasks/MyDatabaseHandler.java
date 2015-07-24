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
    private static final String DB_NAME = "my_tasks";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE="create table task" + "( _id integer primary" +
            " key autoincrement, title text not null, date datetime not null);";

    public MyDatabaseHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        insertData(db, "test1", "19631122121211");
        insertData(db, "test2", "19631122121212");
        insertData(db, "test3", "19631122121213");
        insertData(db, "test4", "19631122121214");
        insertData(db, "test5", "19631122121215");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertData(SQLiteDatabase db, String title, String date){
        db.execSQL("insert into task values(null, ?, ?)", new String[] {title, date});
    }

    public List<Task> getALllContact(){
        List<Task> tasks = new ArrayList<Task>();

        String selectQuery="SELECT * FROM task";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Task task = new Task(cursor.getString(1), cursor.getString(2));
                tasks.add(task);
            }while(cursor.moveToNext());
        }

        System.out.println("-------------" + tasks.size());

        return tasks;
    }
}
