package com.innobles.smartplaykids.ui.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.network.model.StudInfo;
import com.innobles.smartplaykids.network.model.StudentInfo;
import com.innobles.smartplaykids.ui.adapter.BaseAdapter;
import com.innobles.smartplaykids.ui.base.MainBaseNavigationActivity;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.base.MyAccount;
import com.innobles.smartplaykids.ui.viewHolder.DashBoardViewHolder;
import com.innobles.smartplaykids.ui.viewHolder.KidsViewHolder;
import com.innobles.smartplaykids.ui.widget.EmptyRecyclerView;
import com.innobles.smartplaykids.utils.Command;
import com.innobles.smartplaykids.utils.Constant;
import com.innobles.smartplaykids.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoardActivity extends MainBaseNavigationActivity implements BaseAdapter.BindAdapterListener<DashBoardViewHolder>{
    @BindView(R.id.recycler_view)
    EmptyRecyclerView recyclerView;
    /*error*/
    @BindView(R.id.lLayoutErrorView)
    LinearLayout lLayoutErrorView;

    private BaseAdapter<StudInfo, DashBoardViewHolder> baseAdapter;
    private MvpPresenter mPresenter;
    private String TAG=getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dash_board,onMainFrameLayout());
        setUnBinder(ButterKnife.bind(this));
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
    protected void setUp() {
        super.setUp();
        mPresenter=new DashBoardPresenter(this);
        onBottomNavigationShow();
        setTitleMessage(getString(R.string.app_name));
        setParam();
        onClickAction();


    }

    @Override
    public void onClickAction() {
        super.onClickAction();

//        lLayoutErrorView.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lLayoutErrorView:
                setParam();
                break;
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
    public void setData(List<StudInfo> studentInfos) {

        baseAdapter = new BaseAdapter<>(this, studentInfos, this, DashBoardViewHolder.class, R.layout.item_dashboard);
       /* Utils.recyclerView(recyclerView, this, true);*/
        recyclerView.setVisibility(View.VISIBLE);
        Utils.recyclerView(recyclerView,this,true).setEmptyView(lLayoutErrorView);
        if (baseAdapter != null) {
            recyclerView.setAdapter(baseAdapter);
            baseAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBind(DashBoardViewHolder holder, int position) {
        Log.d(TAG,baseAdapter.getItem(position).getStudentName());
       // Log.d(TAG,baseAdapter.getItem(position).getParentID());
        Log.d(TAG,baseAdapter.getItem(position).getStudentImage());

        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentName()))
            holder.tvName.setText(baseAdapter.getItem(position).getStudentName());

       /* if (!TextUtils.isEmpty(baseAdapter.getItem(position).getParentID()))
           holder.tvInfo.setText(baseAdapter.getItem(position).getParentID().toString());*/

        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentID()))
            holder.tvLocation.setText(baseAdapter.getItem(position).getStudentID().toString());


        if (!TextUtils.isEmpty(baseAdapter.getItem(position).getStudentImage()))
            Utils.setImageGlide(holder.view.getContext(), baseAdapter.getItem(position).getStudentName(), holder.imageView);

       /* holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onError("click");
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_notifications:
                Intent intent=new Intent(DashBoardActivity.this, MyAccount.class);
                intent.putExtra("TAG", Command.NOTIFICATION_CENTER);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
