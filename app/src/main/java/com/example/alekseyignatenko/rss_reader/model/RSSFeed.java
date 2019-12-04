package com.example.alekseyignatenko.rss_reader.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name="rss", strict=false)
public class RSSFeed {

    @Element(name="title")
    @Path("channel")
    private String channelTitle;

    @ElementList(name="item", inline=true)
    @Path("channel")
    private List<Article> articleList;

    @Element(name="url")
    @Path("channel/image")
    private String url;

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public void setUrlForAllArticles(){
        List<Article> articles = new ArrayList<>();
        for(Iterator<Article> iter = articleList.iterator();iter.hasNext();){
            Article article = iter.next();
            article.setImageUrl(url);
            articles.add(article);
        }
        articleList=articles;
    }
}

