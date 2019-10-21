package com.example.alekseyignatenko.rss_reader.NetWork;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentManager;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import com.example.alekseyignatenko.rss_reader.Adapter.DataSource;
import com.example.alekseyignatenko.rss_reader.Adapter.PagedRssAdapter;
import com.example.alekseyignatenko.rss_reader.DateSorting.OutcomeAscComparator;
import com.example.alekseyignatenko.rss_reader.MainActivity;
import com.example.alekseyignatenko.rss_reader.model.RssObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class ProcessInBackground extends AsyncTask<Integer,Void,Exception> {

    Exception exception = null;

    private String ind;

    private ArrayList<RssObject> RssItemList;

    private ArrayList<String> Titles;
    private ArrayList<String> pubDate;
    private ArrayList<String> Link;
    private ArrayList<String> description;

    Context c;

    RecyclerView RssListView;

    private String StringURL;

    public ProcessInBackground(String url, Context context, RecyclerView listView,ArrayList<RssObject> rssItemList,String Ind){
        Titles = new ArrayList<String>();
        pubDate = new ArrayList<String>();
        description = new ArrayList<String>();
        Link = new ArrayList<String>();


        ind =Ind;

        RssItemList = rssItemList;

        c = context;
        RssListView = listView;

        StringURL = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Exception s) {
        super.onPostExecute(s);
        RSSresult();
        if (MainActivity.isComplet()){
        SetArrayListListView();
        }else{
            MainActivity.setComplet(true);
        }
    }

    @Override
    protected Exception doInBackground(Integer... integers) {
        try {
            URL url = new URL(StringURL);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(false);

            XmlPullParser xpp =factory.newPullParser();

            xpp.setInput(getInputStrim(url),"UTF_8");

            boolean insideItem = false;

            int eventTyoe = xpp.getEventType();

            while (eventTyoe != XmlPullParser.END_DOCUMENT){
                if(eventTyoe == XmlPullParser.START_TAG){
                    if(xpp.getName().equalsIgnoreCase("item")){
                        insideItem = true;
                    }else if(xpp.getName().equalsIgnoreCase("title")){
                        if(insideItem){
                            Titles.add(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("pubDate")){
                        if(insideItem) {
                                pubDate.add(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("link")){
                        if(insideItem){
                            Link.add(xpp.nextText());
                        }
                    }else if(xpp.getName().equalsIgnoreCase("description")){
                        if(insideItem){
                            description.add(xpp.nextText());
                        }
                    }
                }else if(eventTyoe == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem = false;
                }

                eventTyoe = xpp.next();
            }
        }catch (MalformedURLException e){
            exception = e;
        }catch (XmlPullParserException e){
            exception = e;
        }catch (IOException e){
            exception = e;
        }
        return exception;
    }

    public InputStream getInputStrim(URL url){
        try {
            return url.openConnection().getInputStream();
        }catch (IOException e){
            return null;
        }
    }

    private void RSSresult(){
        for (int i =0;i<Titles.size();i++){
            RssObject rssObject = new RssObject(Titles.get(i), description.get(i), Link.get(i),ind, pubDate.get(i));
            //rssObject.setTitle(Titles.get(i));
            //rssObject.setPubDate(pubDate.get(i));
            //rssObject.setLink(Link.get(i));
            //rssObject.setDescription(description.get(i));
            RssItemList.add(rssObject);
        }
    }

    public void SetArrayListListView(){
        //ArrayList<String> dates = DateSorting.setFormat(pubDate);
        Collections.sort(RssItemList, new OutcomeAscComparator());

        DataSource RssObjectDS = new DataSource(RssItemList);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        PagedList pagedList = new PagedList.Builder<>(RssObjectDS,config)
                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();

        PagedRssAdapter rssAdapter= new PagedRssAdapter(new MyCallBack());


        rssAdapter.submitList(pagedList);

        RssListView.setAdapter(rssAdapter);

        //rssAdapter.notifyDataSetChanged();
        //RssListView.setOnItemClickListener(itemClickListener);
    }

}
