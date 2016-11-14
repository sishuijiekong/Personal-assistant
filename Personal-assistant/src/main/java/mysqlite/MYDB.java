package mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import model.ChengYu_m;

/**
 * Created by 张显林 on 2016/11/3.
 * 数据库（sqlite）构建  数据操作
 */
public class MYDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "CHENGYU.db";
    private final static int DATABASE_VERSION = 1;
    //所有成语表
    private final static String TABLE_NAME1 = "allchengyu_table";
    //成语学习足迹表
    private final static String TABLE_NAME2 = "zujichengyu_table";
    //成语收藏表
    private final static String TABLE_NAME3 = "shoucangchengyu_table";
    //成语ID
    private final static String CHENGYU_ID = "chengyu_id";
    //成语名字
    private final static String CHENGYU_NAME = "chengyu_name";
    //成语拼音
    private final static String CHENGYU_PINYIN ="chengyu_pinyin";
    //查看时间
    private final static String READ_TIME ="read_time";




    //汉字收藏表
    private final static String TABLE_NAME4 = "hanzishoucang_table";
    //汉字ID
    private final static String HANZI_ID = "hanziid";
    //汉字文字
    private final static String HANZI_ZI = "hanzizi";
    //汉字拼音
    private final static String HANZI_PINYIN = "hanzipinyin";



    //趣味汉字表
    private final static String TABLE_NAME5 = "quweihanzi_table";
    //汉字ID
    private final static String QHANZI_ID = "hanziid";
    //汉字文字
    private final static String QHANZI_ZI = "hanzizi";
    //汉字拼音
    private final static String QHANZI_PINYIN = "hanzipinyin";
    //汉字笔画
    private final static String QHANZI_BIHUA = "hanzibihua";
    //趣味类型
    private final static String QHANZI_TYPE = "hanzitype";




    public MYDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        /**
         * CHENGYU_ID 整形  主键  自动增长
         * CHENGYU_NAME String型 非空 唯一性约束
         * CHENGYU_PINYIN String型
         */

        String sql1 = "CREATE TABLE " + TABLE_NAME1 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text NOT NULL UNIQUE,"+CHENGYU_PINYIN+" text);";

        /**
         * CHENGYU_ID 整形  主键  自动增长
         * CHENGYU_NAME String型
         * READ_TIME Date型
         */

        String sql2 = "CREATE TABLE " + TABLE_NAME2 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text,"+READ_TIME+" date);";

        /**
         * CHENGYU_ID 整形  主键  自动增长
         * CHENGYU_NAME String型 非空 唯一性约束
         * READ_TIME Date型
         */

        String sql3 = "CREATE TABLE " + TABLE_NAME3 + " (" + CHENGYU_ID
                + " INTEGER primary key autoincrement, " + CHENGYU_NAME + " text ,"+READ_TIME+" text);";


        /**
         * HANZI_ID 整形  主键  自动增长
         * HANZI_ZI String型 非空 唯一性约束
         * HANZI_PINYIN String型
         */

        String sql4 = "CREATE TABLE " + TABLE_NAME4 + " (" + HANZI_ID
                + " INTEGER primary key autoincrement, " + HANZI_ZI + " text NOT NULL UNIQUE,"+HANZI_PINYIN+" text);";


        /**
         * QHANZI_ID 整形  主键  自动增长
         * QHANZI_ZI String型 非空 唯一性约束
         * QHANZI_PINYIN String型
         * QHANZI_BIHUA String型
         * QHANZI_TYPE String型
         */

        String sql5 = "CREATE TABLE " + TABLE_NAME5 + " (" + QHANZI_ID
                + " INTEGER primary key autoincrement, " + QHANZI_ZI + " text,"+QHANZI_PINYIN+" text,"+QHANZI_TYPE+" text ,"+QHANZI_BIHUA+" text);";


        /**
         * 执行SQL语句创建数据表
         */
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
    }

    /**
     * 更新时数据库的操作
     * @param db 数据库名称
     * @param i 旧的版本号
     * @param i1 新的版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        String sql1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        String sql3 = "DROP TABLE IF EXISTS " + TABLE_NAME3;
        String sql4 = "DROP TABLE IF EXISTS " + TABLE_NAME4;
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        onCreate(db);
    }


    /**
     * 所有成语表
     * @return
     */

    //查询所有成语
    public Cursor select1() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME1, null, null, null, null, null, null);
        return cursor;
    }
    //增加一条成语操作
    public long insert1(ChengYu_m chengYu_m)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, chengYu_m.getName());
        cv.put(CHENGYU_PINYIN, chengYu_m.getPinyin());
        long row = db.insert(TABLE_NAME1, null, cv);
        return row;
    }
    //根据成语名删除一条成语
    public void delete1(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_NAME + " = ?";
        String[] whereValue ={ name};
        db.delete(TABLE_NAME1, where, whereValue);
    }
    /**
     * 足迹成语表
     * @return
     */

    //查询所有足迹
    public Cursor select2() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME2, null, null, null, null, null, null);
        return cursor;
    }
    //增加一条足迹操作
    public long insert2(ChengYu_m chengYu_m)
    {
        Date date=new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, chengYu_m.getName());
        cv.put(READ_TIME, date.toString());
        long row = db.insert(TABLE_NAME2, null, cv);
        return row;
    }
    //删除一条足迹操作
    public void delete2(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_NAME + " = ?";
        String[] whereValue ={ name };
        db.delete(TABLE_NAME2, where, whereValue);
    }
    /**
     * 收藏成语表
     * @return
     */

    //获取所有搜藏
    public Cursor select3() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME3, null, null, null, null, null, null);
        return cursor;
    }
    //增加一条搜藏操作
    public long insert3(ChengYu_m chengYu_m)
    {
        Date date=new Date();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CHENGYU_NAME, chengYu_m.getName());
        cv.put(READ_TIME, date.toString());
        long row = db.insert(TABLE_NAME3, null, cv);
        return row;
    }
    //删除一条搜藏操作
    public void delete3(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = CHENGYU_NAME + " = ?";
        String[] whereValue ={ name };
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


    /**
     * 汉字收藏表
     * @return
     */

    //查询所有汉字收藏
    public Cursor hanzishoucangselect() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME4, null, null, null, null, null, null);
        return cursor;
    }
    //增加一条汉字收藏操作
    public long hanzishoucanginsert(String hanzi,String pinyin)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HANZI_ZI, hanzi);
        cv.put(HANZI_PINYIN, pinyin);
        long row = db.insert(TABLE_NAME4, null, cv);
        return row;
    }
    //根据成语名删除一条汉字收藏
    public void hanzishoucangdelete1(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = HANZI_ZI + " = ?";
        String[] whereValue ={ name};
        db.delete(TABLE_NAME4, where, whereValue);
    }



    /**
     * 趣味汉字表
     * @return
     */

    //查询所有汉字收藏
    public Cursor quweihanziselect(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        String where = QHANZI_TYPE + " = ?";
        String[] whereValue ={ type};
        Cursor cursor = db
                .query(TABLE_NAME5, null, where, whereValue, null, null, null);
        return cursor;
    }
    //增加一条汉字收藏操作
    public long quweihanziinsert(String hanzi,String pinyin,String bihua,String type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QHANZI_ZI, hanzi);
        cv.put(QHANZI_PINYIN, pinyin);
        cv.put(QHANZI_BIHUA, bihua);
        cv.put(QHANZI_TYPE, type);
        long row = db.insert(TABLE_NAME5, null, cv);
        return row;
    }
    //根据成语名删除一条汉字收藏
    public void quweihanzidelete1(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = QHANZI_ZI + " = ?";
        String[] whereValue ={ name};
        db.delete(TABLE_NAME5, where, whereValue);
    }

}
