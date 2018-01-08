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

public class DashBoardViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imView)
    public CircleImageView imageView;

    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.tvInfo)
    public TextView tvInfo;

    @BindView(R.id.tvLocation)
    public TextView tvLocation;

    public View view;


    public DashBoardViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        ButterKnife.bind(this, itemView);
    }
}