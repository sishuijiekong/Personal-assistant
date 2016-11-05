package model;

import java.io.Serializable;

/**
 * Created by 张显林 on 2016/11/4.
 * 成语的简略信息
 * name 成语名称
 * pinyin 成语拼音
 */

public class ChengYu_m implements Serializable {
    private String name;
    private String pinyin;

    public ChengYu_m(){

    }

    public ChengYu_m(String name,String pinyin){
        this.name=name;
        this.pinyin=pinyin;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
