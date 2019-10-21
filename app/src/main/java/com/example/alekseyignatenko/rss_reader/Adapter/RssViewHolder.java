package com.example.alekseyignatenko.rss_reader.Adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.alekseyignatenko.rss_reader.R;
import com.example.alekseyignatenko.rss_reader.model.RssObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RssViewHolder extends RecyclerView.ViewHolder {


    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewData;

    public RssViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView =itemView.findViewById(R.id.imageView);
        textViewTitle = itemView.findViewById(R.id.TextViewtitle);
        textViewDescription = itemView.findViewById(R.id.TexViewDescription);
        textViewData = itemView.findViewById(R.id.TexViewData);


    }

    public void bind(final RssObject rssObject) {

        DateFormat f = new SimpleDateFormat("dd MMM yyyy hh:mm");

        textViewTitle.setText(rssObject.getTitle());
        textViewData.setText(f.format(rssObject.getPubDate()));
        //textViewDescription.setText(rssObject.getDescription());

        itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(textViewDescription.getText().length()==0){
                    Spanned spanned = Html.fromHtml(rssObject.getDescription(),Html.FROM_HTML_MODE_LEGACY);
                    textViewDescription.setText(spanned);
                }else{
                    textViewDescription.setText("");
                }
            }
        });

        if(rssObject.getInd().equals("M")) {
            imageView.setImageResource(R.drawable.meduza);
        }else if(rssObject.getInd().equals("H")){
            imageView.setImageResource(R.drawable.logo);
        }

    }
}
