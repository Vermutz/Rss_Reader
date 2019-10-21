package com.example.alekseyignatenko.rss_reader.DateSorting;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DateSorting  {

    private static final String strDateFormat = "hh:mm:ss dd-MMM-yyyy";

    public static ArrayList<String> setFormat(ArrayList<Date> dates){
        ArrayList<String> NewformateArrayList = new ArrayList<String>();
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        for (Iterator<Date> iter = dates.iterator(); iter.hasNext(); ) {
            Date date = iter.next();
            NewformateArrayList.add(objSDF.format(date));
        }
        return NewformateArrayList;
    }
}
