package com.example.alekseyignatenko.rss_reader.DateSorting;

import com.example.alekseyignatenko.rss_reader.model.RssObject;

import java.util.Comparator;

public class OutcomeAscComparator implements Comparator<RssObject>
{

    @Override
    public int compare(RssObject left, RssObject right) {
        return right.getPubDate().compareTo(left.getPubDate());
    }
}
