package com.example.alekseyignatenko.rss_reader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



import com.example.alekseyignatenko.rss_reader.R;
import com.example.alekseyignatenko.rss_reader.model.Article;
import com.squareup.picasso.Picasso;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RssViewHolder extends RecyclerView.ViewHolder {


    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewData;
    private Button button;

    private DateFormat f = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",Locale.ENGLISH);
    private DateFormat f2 = new SimpleDateFormat("dd MMM yyyy HH:mm");

    private Context context;

    public RssViewHolder(@NonNull View itemView,Context context) {
        super(itemView);

        this.context = context;

        imageView =itemView.findViewById(R.id.imageView);
        textViewTitle = itemView.findViewById(R.id.TextViewtitle);
        textViewDescription = itemView.findViewById(R.id.TexViewDescription);
        textViewData = itemView.findViewById(R.id.TexViewData);
        button = itemView.findViewById(R.id.button);
    }

    public void bind(final Article article) {

        if(article!=null) {
            textViewTitle.setText(article.getTitle());

            try {
                Date date = f.parse(article.getPubDate());
                textViewData.setText(f2.format(date));
            } catch (ParseException e) {
                textViewData.setText(article.getPubDate());
            }

            textViewDescription.setText("");
            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    if (textViewDescription.getText().length() == 0) {
                        Spanned spanned = Html.fromHtml(article.getDescription(), Html.FROM_HTML_MODE_LEGACY);
                        textViewDescription.setText(spanned);
                    } else {
                        textViewDescription.setText("");
                    }
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getLink()));
                    context.startActivity(intent);
                }
            });
            Picasso.get()
                    .load(article.getImageUrl())
                    .fit()
                    .placeholder(R.drawable.refresh_black_96x96)
                    .error(R.drawable.stop_symbol)
                    .into(imageView);
        }else {
            textViewTitle.setText("Загружаеться");
            textViewData.setText("");
            imageView.setImageResource(R.drawable.refresh_black_96x96);
        }
    }
}
