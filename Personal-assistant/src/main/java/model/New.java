package model;

/**
 * Created by 28618 on 2016/11/2.
 */
public class New {
    private  String title;
    private  String date;
    private  String author_name;
    private  String thumbnail_pic_s;
    private  String url;
    private  String uniquekey;
    private  String type;
    private  String realtype;

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getRealtype() {
        return realtype;
    }


    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public String getType() {
        return type;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "标题"+title;
    }
}
