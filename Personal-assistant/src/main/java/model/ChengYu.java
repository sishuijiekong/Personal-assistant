package model;

import java.io.Serializable;

/**
 * Created by 张显林 on 2016/11/3.
 * 成语实例类
 */
public class ChengYu implements Serializable {


    //名字
    private String name;
    //拼音
    private String pinyin;
    //解释
    private String chengyujs;
    //出处
    private String from_;
    //例子
    private String example;
    //英文解释
    private String ciyujs;
    //引证
    private String yinzhengjs;
    //同义词
    private String tongyi;
    //反义词
    private String fanyi;

    public String getChengyujs() {
        return chengyujs;
    }

    public String getCiyujs() {
        return ciyujs;
    }

    public String getExample() {
        return example;
    }

    public String getFanyi() {
        return fanyi;
    }

    public String getFrom_() {
        return from_;
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getTongyi() {
        return tongyi;
    }

    public String getYinzhengjs() {
        return yinzhengjs;
    }

    public void setChengyujs(String chengyujs) {
        this.chengyujs = chengyujs;
    }

    public void setCiyujs(String ciyujs) {
        this.ciyujs = ciyujs;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setFanyi(String fanyi) {
        this.fanyi = fanyi;
    }

    public void setFrom_(String from_) {
        this.from_ = from_;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setTongyi(String tongyi) {
        this.tongyi = tongyi;
    }

    public void setYinzhengjs(String yinzhengjs) {
        this.yinzhengjs = yinzhengjs;
    }

    @Override
    public String toString() {
        return name+"        解释"+chengyujs;
    }
}
