package model;

/**
 * Created by 28618 on 2016/11/6.
 */
public class HanZi {

    private String id;
    private String zi;
    private String py;
    private String wubi;
    private String pinyin;
    private String bushou;
    private String bihua;

    public  HanZi(){

    }

    public String getBihua() {
        return bihua;
    }

    public String getBushou() {
        return bushou;
    }

    public String getId() {
        return id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getPy() {
        return py;
    }

    public String getWubi() {
        return wubi;
    }

    public String getZi() {
        return zi;
    }

    public void setBihua(String bihua) {
        this.bihua = bihua;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public void setWubi(String wubi) {
        this.wubi = wubi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    @Override
    public String toString() {
        return "汉字"+zi+"拼音"+pinyin;
    }
}
