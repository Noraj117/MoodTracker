package com.collet.alexandre.moodtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.collet.alexandre.moodtracker.R;
import com.collet.alexandre.moodtracker.model.MoodList;

import java.util.ArrayList;
import java.util.List;

public class MoodAdapter extends ArrayAdapter<MoodList>  {

    private Context mContext;
    private List<MoodList> mMoodList;

    public MoodAdapter(@NonNull Context context, ArrayList<MoodList> moodList ) {
        super(context,0, moodList);
        mContext = context;
        mMoodList = moodList;

    }
    /*
    Method for inflate custom view for the ListView.
     */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);


        final MoodList moodList = mMoodList.get(position);

        RelativeLayout txtColor = listItem.findViewById(R.id.item_history_layout);
        txtColor.setBackgroundColor(moodList.getColor());

        TextView txtComment = listItem.findViewById(R.id.item_history_text);
        txtComment.setText(moodList.getDate(position));

        listItem.setOnClickListener(new View.OnClickListener() {

            /*
            Method used to get the comment stored in SharedPreferences and show it in toast message when user click on.
             */
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), moodList.getComment(), Toast.LENGTH_SHORT).show();
            }
        });

        // Get the Layout parameters for ListView Current Item View
        ViewGroup.LayoutParams params = listItem.getLayoutParams();

        // Set the height of the Item View

        params.height = 325;
        listItem.setLayoutParams(params);

        return listItem;
    }
}