package model;

import java.io.Serializable;

/**
 * Created by 28618 on 2016/11/5.
 */
public class PinYin implements Serializable {

    private String pinyin_key;
    private String pinyin;

    public  PinYin(){

    }
    public  PinYin(String pinyin_key,String pinyin){
        this.pinyin_key=pinyin_key;
        this.pinyin=pinyin;

    }

    public String getPinyin() {
        return pinyin;
    }

    public String getPinyin_key() {
        return pinyin_key;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setPinyin_key(String pinyin_key) {
        this.pinyin_key = pinyin_key;
    }

    @Override
    public String toString() {
        return "首字母"+pinyin_key+" 拼音"+pinyin;
    }
}
