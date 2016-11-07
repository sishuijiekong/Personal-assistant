package model;

/**
 * Created by 28618 on 2016/11/6.
 */
public class QuWeiHanZi {

    private String id;
    private String zi;
    private String pinyin;
    private String bihua;
    private String type;

    public QuWeiHanZi(){

    }

    public String getBihua() {
        return bihua;
    }

    public String getId() {
        return id;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getType() {
        return type;
    }

    public String getZi() {
        return zi;
    }

    public void setBihua(String bihua) {
        this.bihua = bihua;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    @Override
    public String toString() {
        return "汉字"+zi+"拼音"+pinyin;
    }
}
