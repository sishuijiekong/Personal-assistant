package util;

import android.content.Context;

import mysqlite.MYDB;

/**
 * Created by Administrator on 2016/11/7.
 */
public class InitData {

    public static void SetDataDuoBiHua(Context context){

        MYDB mMYDB=new MYDB(context);

        mMYDB.quweihanziinsert("齾","yà","35","0");
        mMYDB.quweihanziinsert("龗","líng","34","0");
        mMYDB.quweihanziinsert("鱻","xiān","33","0");
        mMYDB.quweihanziinsert("麤","cū","33","0");
        mMYDB.quweihanziinsert("灪","yù","32","0");
        mMYDB.quweihanziinsert("龖","dá","32","0");
        mMYDB.quweihanziinsert("籲","yù","32","0");
        mMYDB.quweihanziinsert("灩","yàn","31","0");
        mMYDB.quweihanziinsert("驫","biāo","30","0");
        mMYDB.quweihanziinsert("鱺","lí","30","0");
        mMYDB.quweihanziinsert("鸝","lí","30","0");
        mMYDB.quweihanziinsert("癵","luán","30","0");
        mMYDB.quweihanziinsert("鸞","luán","30","0");
        mMYDB.quweihanziinsert("饢","náng","30","0");
        mMYDB.quweihanziinsert("麣","yán","30","0");
        mMYDB.quweihanziinsert("厵","yuán","30","0");
        mMYDB.quweihanziinsert("籱","zhuó","30","0");
        mMYDB.quweihanziinsert("爨","cuàn","30","0");
        mMYDB.quweihanziinsert("驪","lí","29","0");
        mMYDB.quweihanziinsert("讟","dú","29","0");
        mMYDB.quweihanziinsert("麷","lí","29","0");
        mMYDB.quweihanziinsert("靏","fēng","29","0");
        mMYDB.quweihanziinsert("韊","lán","29","0");
        mMYDB.quweihanziinsert("纞","liàn","29","0");
        mMYDB.quweihanziinsert("虋","mén","29","0");
        mMYDB.quweihanziinsert("鸜","qú","29","0");
        mMYDB.quweihanziinsert("钃","zhú","29","0");
        mMYDB.quweihanziinsert("鬱","yù","29","0");
        mMYDB.quweihanziinsert("齼","chǔ","28","0");
        mMYDB.quweihanziinsert("戇","戇","28","0");
        mMYDB.quweihanziinsert("麷","fēng","28","0");
        mMYDB.quweihanziinsert("欟","guàn","28","0");
        mMYDB.quweihanziinsert("鱹","guàn","28","0");
        mMYDB.quweihanziinsert("鸛","guàn","28","0");
        mMYDB.quweihanziinsert("雧","jí","28","0");
        mMYDB.quweihanziinsert("齽","jìn","28","0");
        mMYDB.quweihanziinsert("钁","jué","28","0");
        mMYDB.quweihanziinsert("躨","kuí","28","0");
        mMYDB.quweihanziinsert("钄","làn","28","0");
        mMYDB.quweihanziinsert("鼺","léi","28","0");
        mMYDB.quweihanziinsert("欞","líng","28","0");
        mMYDB.quweihanziinsert("爧","líng","28","0");
        mMYDB.quweihanziinsert("麢","líng","28","0");
        mMYDB.quweihanziinsert("癴","luán","28","0");
        mMYDB.quweihanziinsert("囖","luó","28","0");
        mMYDB.quweihanziinsert("钀","niè","28","0");
        mMYDB.quweihanziinsert("鸘","shuāng","28","0");
        mMYDB.quweihanziinsert("钂","tǎng","28","0");
        mMYDB.quweihanziinsert("驨","xí","28","0");
        mMYDB.quweihanziinsert("豔","yàn","28","0");
        mMYDB.quweihanziinsert("鸚","yīng","28","0");
        mMYDB.quweihanziinsert("鸙","yuè","28","0");
        mMYDB.quweihanziinsert("鑿","záo","28","0");
    }

    public static void SetDataShangXia(Context context){

        MYDB mMYDB=new MYDB(context);
        mMYDB.quweihanziinsert("昌","chāng","8","2");
        mMYDB.quweihanziinsert("炎","yán","8","2");
    }

    public static void SetDataZuoYou(Context context){

        MYDB mMYDB=new MYDB(context);
        mMYDB.quweihanziinsert("鍂","piān","16","1");
        mMYDB.quweihanziinsert("林","lín","8","1");

    }

    public static void SetDataSanGe(Context context){

        MYDB mMYDB=new MYDB(context);
        mMYDB.quweihanziinsert("鑫","xīn","24","3");
        mMYDB.quweihanziinsert("淼","miǎo","12","3");

    }

    public static void SetDataSiGe(Context context){

        MYDB mMYDB=new MYDB(context);
        mMYDB.quweihanziinsert("燚","yì","16","4");
        mMYDB.quweihanziinsert("㙓","kui","12","4");

    }







}
