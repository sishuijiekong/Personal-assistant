package model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/2.
 */
public class Joke implements Serializable {

    private String content;
    private String hashId;
    private String unixtime;
    private String updatetime;

    public  Joke(){

    }

    public String getContent() {
        return content;
    }

    public String getHashId() {
        return hashId;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "笑话"+content;
    }
}
