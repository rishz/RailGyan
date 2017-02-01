package com.rishabhshukla.rubio;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

/**
 * Created by rishabhshukla on 29/01/17.
 */

public class childViewHolder extends ChildViewHolder {
    public TextView headChild,hasArrivedChil,hasDepartedChil,stationChil,schArrChil,actArrChil,schDepChil,statusChil,
            distChilChil,minsLateChil,actDepChil;

    public childViewHolder(View itemView) {
        super(itemView);
        hasArrivedChil = (TextView) itemView.findViewById(R.id.tvHasArrivedChild);
        hasDepartedChil = (TextView) itemView.findViewById(R.id.tvHasDepartedChild);
        stationChil = (TextView) itemView.findViewById(R.id.tvStnChild);
        schArrChil = (TextView) itemView.findViewById(R.id.tvSchArrChild);
        actArrChil = (TextView) itemView.findViewById(R.id.tvActArrChild);
        schDepChil = (TextView) itemView.findViewById(R.id.tvSchDepChild);
        actDepChil = (TextView) itemView.findViewById(R.id.tvActDepChild);
        statusChil = (TextView) itemView.findViewById(R.id.tvStatusChild);
        distChilChil = (TextView) itemView.findViewById(R.id.tvDistChild);

    }
}
