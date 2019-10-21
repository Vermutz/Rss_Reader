package com.example.alekseyignatenko.rss_reader.model;

import android.provider.ContactsContract;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RssObject {

    private String Title;
    private String description;
    private String Link;
    private Date PubDate;
    private String Ind;

    public RssObject(String title, String description, String link,String ind, String pubDate) {
        Title = title;
        this.description = description;
        Link = link;
        PubDate = new Date(pubDate);
        Ind =ind;
    }

    public String getInd() {
        return Ind;
    }

    public void setInd(String ind) {
        Ind = ind;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public Date getPubDate() {
        return PubDate;
    }

    public void setPubDate(Date pubDate) {
        PubDate = pubDate;
    }

}
