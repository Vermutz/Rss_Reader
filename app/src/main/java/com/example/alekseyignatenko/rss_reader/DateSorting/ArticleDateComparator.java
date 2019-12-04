package com.example.alekseyignatenko.rss_reader.DateSorting;

import com.example.alekseyignatenko.rss_reader.model.Article;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ArticleDateComparator implements Comparator<Article>
{

    private SimpleDateFormat f = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    @Override
    public int compare(Article left, Article right) {

        Date dateLeft;
        Date dateRight;
        try {
            dateLeft = f.parse(left.getPubDate());
            dateRight = f.parse(right.getPubDate());
            return dateRight.compareTo(dateLeft);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
