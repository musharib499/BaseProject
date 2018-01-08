package com.innobles.smartplaykids.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.innobles.smartplaykids.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mushareb Ali on 03,January,2018.
 * ali.musharib1@gmail.com
 */

public class AlertViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.tvInfo)
    public TextView tvInfo;

    @BindView(R.id.tvSchoolName)
    public TextView tvSchoolName;

    @BindView(R.id.tvDate)
    public TextView tvDate;

    public View view;



    public AlertViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        ButterKnife.bind(this, itemView);
    }
}