package com.example.alekseyignatenko.rss_reader.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.alekseyignatenko.rss_reader.R;
import com.example.alekseyignatenko.rss_reader.model.RssObject;

public class RssItemDialog extends DialogFragment {

    private RssObject rssObject;

    public RssItemDialog(RssObject rssObject) {
        this.rssObject = rssObject;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment, null);
        TextView textViewtitle = v.findViewById(R.id.Dialog_title);
        TextView textViewText = v.findViewById(R.id.Dialog_text);
        textViewtitle.setText(rssObject.getTitle());
        textViewText.setText(rssObject.getDescription());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }


}
