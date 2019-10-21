package com.example.alekseyignatenko.rss_reader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alekseyignatenko.rss_reader.Adapter.DataSource;
import com.example.alekseyignatenko.rss_reader.Adapter.PagedRssAdapter;
import com.example.alekseyignatenko.rss_reader.Dialog.RssItemDialog;
import com.example.alekseyignatenko.rss_reader.NetWork.MainThreadExecutor;
import com.example.alekseyignatenko.rss_reader.NetWork.ProcessInBackground;
import com.example.alekseyignatenko.rss_reader.model.RssObject;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewData;

    RecyclerView listViewRSS;

    private static boolean complet = false;

    PagedList pagedList;


    private static final String MeduzaURL = "https://meduza.io/rss/podcasts/meduza-v-kurse";
    private static final String HabURL = "https://habr.com/ru/rss/hubs/all/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRSS = (RecyclerView) findViewById(R.id.ListViewRSS);

        imageView =findViewById(R.id.imageView);
        textViewTitle = findViewById(R.id.TextViewtitle);
        textViewDescription = findViewById(R.id.TexViewDescription);
        textViewData = findViewById(R.id.TexViewData);

        listViewRSS.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<RssObject> RssItemList = new ArrayList<RssObject>();

        ProcessInBackground PIBM = new ProcessInBackground(MeduzaURL,this,listViewRSS,RssItemList,"M");
        PIBM.execute();
        ProcessInBackground PIBH = new ProcessInBackground(HabURL,this,listViewRSS,RssItemList,"H");
        PIBH.execute();
    }

    public static boolean isComplet() {
        return complet;
    }

    public static void setComplet(boolean complet) {
        MainActivity.complet = complet;
    }

}
