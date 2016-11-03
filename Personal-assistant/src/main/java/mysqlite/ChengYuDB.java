package mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/3.
 */
public class ChengYuDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "CHENGYU.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME1 = "all_table";
    private final static String TABLE_NAME2 = "zhangwo_table";
    private final static String TABLE_NAME3 = "shoucang_table";
    private final static String CHENGYU_ID = "chengyu_id";
    private final static String CHENGYU_NAME = "chengyu_name";


    public ChengYuDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + TABLE_NAME1 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text);";
        String sql2 = "CREATE TABLE " + TABLE_NAME2 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text);";
        String sql3 = "CREATE TABLE " + TABLE_NAME3 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text);";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        String sql1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        String sql3 = "DROP TABLE IF EXISTS " + TABLE_NAME3;
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        onCreate(db);
    }


    /**
     * 所有成语表
     * @return
     */
    public Cursor select1() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME1, null, null, null, null, null, null);
        return cursor;
    }
    //增加操作
    public long insert1(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
/* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        long row = db.insert(TABLE_NAME1, null, cv);
        return row;
    }
    //删除操作
    public void delete1(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue ={ Integer.toString(id) };
        db.delete(TABLE_NAME1, where, whereValue);
    }
    //修改操作
    public void update1(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };

        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        db.update(TABLE_NAME1, cv, where, whereValue);
    }


    /**
     * 所有掌握成语表
     * @return
     */
    public Cursor select2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME2, null, null, null, null, null, null);
        return cursor;
    }
    //增加操作
    public long insert2(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
/* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        long row = db.insert(TABLE_NAME2, null, cv);
        return row;
    }
    //删除操作
    public void delete2(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue ={ Integer.toString(id) };
        db.delete(TABLE_NAME2, where, whereValue);
    }
    //修改操作
    public void update2(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };

        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        db.update(TABLE_NAME2, cv, where, whereValue);
    }


    /**
     * 收藏成语表
     * @return
     */
    public Cursor select3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME3, null, null, null, null, null, null);
        return cursor;
    }
    //增加操作
    public long insert3(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
/* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        long row = db.insert(TABLE_NAME3, null, cv);
        return row;
    }
    //删除操作
    public void delete3(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue ={ Integer.toString(id) };
        db.delete(TABLE_NAME3, where, whereValue);
    }
    //修改操作
    public void update3(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };

        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, name);
        db.update(TABLE_NAME3, cv, where, whereValue);
    }



}
