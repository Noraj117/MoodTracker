package com.collet.alexandre.moodtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.model.MoodList;

import java.util.ArrayList;
import java.util.List;

public class MoodAdapter extends ArrayAdapter<MoodList>  {

    private Context mContext;
    private List<MoodList> mMoodList = new ArrayList<>();

    public MoodAdapter(@NonNull Context context, ArrayList<MoodList> moodList ) {
        super(context,0, moodList);
        mContext = context;
        mMoodList = moodList;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        MoodList moodList = mMoodList.get(position);

        TextView txtColor = (TextView) listItem.findViewById(R.id.item_history_toast);
        txtColor.setText (moodList.getColor()+"");

        TextView txtComment = (TextView) listItem.findViewById(R.id.item_history_text);
        txtComment.setText(moodList.getComment());


        // Get the Layout parameters for ListView Current Item View
        ViewGroup.LayoutParams params = listItem.getLayoutParams();

        // Set the height of the Item View
        params.height = 298;
        listItem.setLayoutParams(params);

        return listItem;
    }
}
