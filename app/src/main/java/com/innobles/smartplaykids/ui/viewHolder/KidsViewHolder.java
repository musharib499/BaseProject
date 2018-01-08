package com.innobles.smartplaykids.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innobles.smartplaykids.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mushareb Ali on 03,January,2018.
 * ali.musharib1@gmail.com
 */

public class KidsViewHolder  extends RecyclerView.ViewHolder {
    @BindView(R.id.imView)
    public CircleImageView imageView;

    @BindView(R.id.tvName)
    public TextView tvName;

    public View view;


    public KidsViewHolder(View itemView) {
        super(itemView);
        view=itemView;
        ButterKnife.bind(this, itemView);
    }
}