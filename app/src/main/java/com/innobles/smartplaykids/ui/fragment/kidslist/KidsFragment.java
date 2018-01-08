package com.innobles.smartplaykids.ui.fragment.kidslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.network.model.StudInfo;
import com.innobles.smartplaykids.network.model.StudentInfo;
import com.innobles.smartplaykids.ui.adapter.BaseAdapter;
import com.innobles.smartplaykids.ui.base.BaseFragment;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.viewHolder.KidsViewHolder;
import com.innobles.smartplaykids.ui.widget.EmptyRecyclerView;
import com.innobles.smartplaykids.utils.Constant;
import com.innobles.smartplaykids.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KidsFragment extends BaseFragment implements BaseAdapter.BindAdapterListener<KidsViewHolder> {

    @BindView(R.id.recycler_view)
    EmptyRecyclerView recyclerView;

    @BindView(R.id.nextButton)
    Button nextButton;

    @BindView(R.id.tvReport)
    TextView tvReport;

    /*error*/
    @BindView(R.id.lLayoutErrorView)
    RelativeLayout lLayoutErrorView;

    @BindView(R.id.imViewError)
    ImageView imViewError;

    @BindView(R.id.tvError)
    TextView tvError;

    @BindView(R.id.tvErrorContact)
    TextView tvErrorContact;

    private BaseAdapter<StudInfo, KidsViewHolder> baseAdapter;
    private MvpPresenter mPresenter;
    private String TAG=getClass().getName();

    public static KidsFragment newInstance() {

        return new KidsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kids, container, false);
        setUnBinder(ButterKnife.bind(this, v));
        setUp(v);
        onClickAction();
        setParam();

        return v;
    }


    @Override
    protected void setUp(View view) {
        mPresenter = new KidsPresenter(this);
        onBackArrowShow();
        setTitleMessage(getString(R.string.kind_info));
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
    public void onClickAction() {
        super.onClickAction();

        nextButton.setOnClickListener(this);
        tvReport.setOnClickListener(this);
        lLayoutErrorView.setOnClickListener(this);

    }

    private void onWaitLoadView() {
        recyclerView.setVisibility(View.GONE);
        tvError.setText(getString(R.string.be_patient));
        tvError.setTextColor(getResources().getColor(R.color.light_oragne));
        tvErrorContact.setText(R.string.world_report);
        tvErrorContact.setOnClickListener(null);


    }

    private void onNoInfoKidsView() {
        recyclerView.setVisibility(View.GONE);
        lLayoutErrorView.setVisibility(View.VISIBLE);
        String string = "Sorry name " + getResources().getString(R.string.no_kind_info);
        tvError.setText(string);
        tvError.setTextColor(getResources().getColor(R.color.red));
        tvErrorContact.setText(R.string.contact_us);
        tvErrorContact.setOnClickListener(this);
        nextButton.setBackgroundColor(getResources().getColor(R.color.red));
        nextButton.setTextColor(getResources().getColor(R.color.white));
        nextButton.setText(R.string.report_error);

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
                    onNoInfoKidsView();
                    onError(getString(R.string.some_error));
                }
            } else {
                onNoInfoKidsView();
                onError(studentInfo.getMessage());
            }

        }


    }

    @Override
    public void onLoadedFailure(Throwable error) {
        super.onLoadedFailure(error);
        onNoInfoKidsView();
    }

    @Override
    public void onShowProgressBar() {
        super.onShowProgressBar();
        onShowLoading("Please wiat......");
        onWaitLoadView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                onShowMessage("hello");
            /*    List<String> strings = new ArrayList<>();*/

             /*   if (baseAdapter != null) {
                    baseAdapter.refresh();
                    baseAdapter.notifyDataSetChanged();
                }
                tvReport.setVisibility(View.GONE);*/
                //  onNoInfoKidsView();
                break;
            case R.id.tvReport:
                onShowMessage("On Report");
                break;

            case R.id.tvErrorContact:
                onShowMessage("On Contact us");
                break;

            case R.id.lLayoutErrorView:
                setParam();
                break;
        }
    }

    @Override
    public void onNextActivity(int activity) {
        super.onNextActivity(activity);
    }

    public void setData(List<StudInfo> studentInfos) {

        baseAdapter = new BaseAdapter<>(getActivity(), studentInfos, this, KidsViewHolder.class, R.layout.item_kids_layout);
       /* Utils.recyclerView(recyclerView, this, true);*/
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setEmptyView(lLayoutErrorView);
        if (baseAdapter != null) {
            recyclerView.setAdapter(baseAdapter);
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBind(KidsViewHolder holder, int position) {
        Log.d(TAG,baseAdapter.getItem(position).getStudentName());
        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentName()))
            holder.tvName.setText(baseAdapter.getItem(position).getStudentName());


        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentImage()))
            Utils.setImageGlide(holder.view.getContext(), baseAdapter.getItem(position).getStudentName(), holder.imageView);
       /* holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onError("click");
            }
        });*/

    }


}
