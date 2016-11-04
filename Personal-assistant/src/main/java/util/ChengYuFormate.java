package util;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ChengYuFormate {


    public static List<String> formate1(String str){
        List<String> list=new ArrayList<>();
        String s1 = str.replace('"',' ');
        String s2 = s1.replace('[',' ');
        String s3 = s2.replace(']',' ');
        String[] sourceStrArray = s3.split(",");
        for(int i=0;i<sourceStrArray.length;i++){
            list.add(sourceStrArray[i].toString());
            Log.d("rrrrrrr",sourceStrArray[i].toString());
        }
        return list;

    }
}
