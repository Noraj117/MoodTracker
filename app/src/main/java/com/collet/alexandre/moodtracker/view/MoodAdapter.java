package com.collet.alexandre.moodtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.model.MoodDataStorage;

import java.util.ArrayList;
import java.util.List;

public class MoodAdapter extends ArrayAdapter<MoodDataStorage> {

    private Context mContext;
    private List<MoodDataStorage> moodList = new ArrayList<>();

    public MoodAdapter(@NonNull Context context, ArrayList<MoodDataStorage> list) {
        super(context, 0, list);
        mContext = context;
        moodList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        MoodDataStorage currentMood = moodList.get(position);

        TextView txtColor = (TextView) listItem.findViewById(R.id.item_history_toast);
        txtColor.setText(currentMood.getCouleur()+"");

        TextView txtCommentaire = (TextView) listItem.findViewById(R.id.item_history_text);
        txtCommentaire.setText(currentMood.getCommentaire());

        return listItem;
    }
}