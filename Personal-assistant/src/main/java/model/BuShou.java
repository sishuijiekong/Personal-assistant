package model;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 张显林 on 2016/11/5.
 * 汉字部首实例类
 */
public class BuShou implements Serializable {

    private String bihua;
    private String bushou;

    public BuShou(){

    }
    public BuShou(String bihua, String bushou){
        this.bihua=bihua;
        this.bushou=bushou;

    }

    public String getBihua() {
        return bihua;
    }

    public String getBushou() {
        return bushou;
    }

    public void setBihua(String bihua) {
        if(bihua.equals("1")){
            this.bihua = "一画";
        }else if(bihua.equals("2")){
            this.bihua = "二画";
        }else if(bihua.equals("3")){
            this.bihua = "三画";
        }else if(bihua.equals("4")){
            this.bihua = "四画";
        }else if(bihua.equals("5")){
            this.bihua = "五画";
        }else if(bihua.equals("6")){
            this.bihua = "六画";
        }else if(bihua.equals("7")){
            this.bihua = "七画";
        }else if(bihua.equals("8")){
            this.bihua = "八画";
        }else if(bihua.equals("9")){
            this.bihua = "九画";
        }else if(bihua.equals("10")){
            this.bihua = "十画";
        }else if(bihua.equals("11")){
            this.bihua = "十一画";
        }else if(bihua.equals("12")){
            this.bihua = "十二画";
        }else if(bihua.equals("13")){
            this.bihua = "十三画";
        }else if(bihua.equals("14")){
            this.bihua = "十四画";
        }else if(bihua.equals("15")){
            this.bihua = "十五画";
        }

    }

    public void setBushou(String bushou) {

        this.bushou = bushou+"部";
    }

    @Override
    public String toString() {
        return "笔画"+bihua+" 部首"+bushou;
    }
}
