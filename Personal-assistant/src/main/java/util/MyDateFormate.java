package util;

/**
 * Created by Administrator on 2016/11/16.
 */
public class MyDateFormate  {

    public static String formatetime(String date){
        String str=null;
        String[] strlist=date.split(" ");
        str=strlist[strlist.length-1];
        switch(strlist[1]){
            case "Jan":str+="-1";
                break;
            case "Feb":str+="-2";
                break;
            case "Mar":str+="-3";
                break;
            case "Apr":str+="-4";
                break;
            case "May":str+="-5";
                break;
            case "Jun":str+="-6";
                break;
            case "Jul":str+="-7";
                break;
            case "Aug":str+="-8";
                break;
            case "Sept":str+="-9";
                break;
            case "Oct":str+="-10";
                break;
            case "Nov":str+="-11";
                break;
            case "Dec":str+="-12";
                break;

        }
        str+="-"+strlist[2]+" "+strlist[3];


        return str;

    }
}
