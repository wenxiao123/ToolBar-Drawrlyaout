package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZhangYang on 2017/5/22.
 */

public class DbHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "sb.DB";
    public DbHelper(Context context) {
        super(context,DB_NAME,null,1);
    }

    /**
     * onCreate里面写创建表的语句
     * 什么时候执行？(创建)
     * 只会在创建数据库执行
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table new(id integer primary key autoincrement,"+
        "title text not null default '',url_id text not null)");
        db.execSQL("create table news(id integer primary key autoincrement,"+
                "title text not null default '',url_id text not null)");
        db.execSQL("create table shangcang(id integer primary key autoincrement,"+
                "title text not null default '',url_id text not null,img text not null,content text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
