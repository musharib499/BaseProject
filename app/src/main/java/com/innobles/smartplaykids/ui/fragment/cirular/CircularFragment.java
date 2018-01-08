package com.innobles.smartplaykids.ui.fragment.cirular;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.network.model.StudInfo;
import com.innobles.smartplaykids.network.model.StudentInfo;
import com.innobles.smartplaykids.ui.adapter.BaseAdapter;
import com.innobles.smartplaykids.ui.base.BaseFragment;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.fragment.attendance.AttendancePresenter;
import com.innobles.smartplaykids.ui.viewHolder.AttendanceViewHolder;
import com.innobles.smartplaykids.ui.viewHolder.CircularViewHolder;
import com.innobles.smartplaykids.ui.widget.EmptyRecyclerView;
import com.innobles.smartplaykids.utils.Constant;
import com.innobles.smartplaykids.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircularFragment extends BaseFragment implements BaseAdapter.BindAdapterListener<CircularViewHolder> {

    @BindView(R.id.recycler_view)
    EmptyRecyclerView recyclerView;
    /*error*/
    @BindView(R.id.lLayoutErrorView)
    LinearLayout lLayoutErrorView;

    private BaseAdapter<StudInfo, CircularViewHolder> baseAdapter;
    private MvpPresenter mPresenter;
    private String TAG = getClass().getName();

    public static CircularFragment newInstance() {

        return new CircularFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_circular, container, false);
        setUnBinder(ButterKnife.bind(this, v));
        setUp(v);
        onClickAction();
        setParam();

        return v;
    }


    @Override
    protected void setUp(View view) {
        mPresenter = new AttendancePresenter(this);
        onBackArrowShow();
        setTitleMessage(getString(R.string.circular));
    }

    private void setParam() {

        Login login = getSmartApplication().getLoginResponse();
        if (login != null && !TextUtils.isEmpty(login.getMobileNumber())) {
            HashMap<String, String> params = new HashMap<>();
            params.put(Constant.MOBILE_NUMBER, login.getMobileNumber());

            mPresenter.onHashMapParam(0, params);
        }
    }

    @Override
    public void onLoadedSuccess(Object o) {
        super.onLoadedSuccess(o);
        if (o instanceof StudentInfo) {
            StudentInfo studentInfo = (StudentInfo) o;
            if (studentInfo.getStatus()) {
                if (studentInfo.getStudInfo() != null) {
                    setData(studentInfo.getStudInfo());
                } else {
                    onError(getString(R.string.some_error));
                }
            } else {
                onError(studentInfo.getMessage());
            }

        }


    }

    @Override
    public void onLoadedFailure(Throwable error) {
        super.onLoadedFailure(error);

    }

    @Override
    public void onShowProgressBar() {
        super.onShowProgressBar();
        onShowLoading("Please wiat......");
    }

    @Override
    public void onNextActivity(int activity) {
        super.onNextActivity(activity);
    }

    public void setData(List<StudInfo> studentInfos) {

        baseAdapter = new BaseAdapter<>(getActivity(), studentInfos, this, CircularViewHolder.class, R.layout.item_circular);
       /* Utils.recyclerView(recyclerView, this, true);*/
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        Utils.recyclerView(recyclerView,getActivity(),true).setEmptyView(lLayoutErrorView);
        if (baseAdapter != null) {
            recyclerView.setAdapter(baseAdapter);
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBind(CircularViewHolder holder, int position) {
        Log.d(TAG, baseAdapter.getItem(position).getStudentName());
        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentName()))
            holder.tvName.setText(baseAdapter.getItem(position).getStudentName());


      //  if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentImage()))
      //      Utils.setImageGlide(holder.view.getContext(), baseAdapter.getItem(position).getStudentName(), holder.imageView);
       /* holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onError("click");
            }
        });*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }


}
