package com.collet.alexandre.moodtracker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.controller.HistoryActivity;
import com.collet.alexandre.moodtracker.model.MoodDataStorage;
import com.collet.alexandre.moodtracker.model.MoodList;

import java.util.List;

public class MoodAdapter extends ArrayAdapter<MoodDataStorage>  {

    private Context mContext;
    private List<MoodList> moodList;

    public MoodAdapter(HistoryActivity moodList, Context context ) {
        super(context,0);
        mContext = context;
        moodList = moodList;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        MoodList currentMood = moodList.get(position);

        RelativeLayout txtColor = (RelativeLayout) listItem.findViewById(R.id.item_history_layout);
        txtColor.setBackgroundColor (currentMood.getColor());

        TextView txtCommentaire = (TextView) listItem.findViewById(R.id.item_history_text);
        txtCommentaire.setText(currentMood.getComment());

        txtColor.setBackgroundColor(currentMood.getColor());

        // Get the Layout Parameters for ListView Current Item View
        ViewGroup.LayoutParams params = listItem.getLayoutParams();

        // Set the height of the Item View
        params.height = 298;
        listItem.setLayoutParams(params);

        return listItem;
    }
}
