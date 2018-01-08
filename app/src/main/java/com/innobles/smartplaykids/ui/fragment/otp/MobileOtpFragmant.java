package com.innobles.smartplaykids.ui.fragment.otp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.MobileOtp;
import com.innobles.smartplaykids.ui.base.BaseFragment;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.base.MyAccount;
import com.innobles.smartplaykids.ui.fragment.kidslist.KidsFragment;
import com.innobles.smartplaykids.utils.Command;
import com.innobles.smartplaykids.utils.Constant;
import com.innobles.smartplaykids.utils.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileOtpFragmant extends BaseFragment {

    @BindView(R.id.tvMobile)
    TextView tvMobile;

    @BindView(R.id.nextButton)
    Button btnNext;


    @BindView(R.id.tvResend)
    TextView tvResend;

    @BindView(R.id.otpViewMobileNumber)
    PinEntryEditText otpViewNumber;

    String mobileNumber;

    MvpPresenter mPresenter;

    public static MobileOtpFragmant newInstance() {
        return new MobileOtpFragmant();
    }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
  }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mobile_otp, container, false);
        setUnBinder(ButterKnife.bind(this, v));
        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        onBackArrowShow();
        onClickAction();
        setTitleMessage(getString(R.string.confirm_mobile));
        mPresenter = new MobilePresenter(this);
        mobileNumber = getActivity().getIntent().getExtras().getString(Constant.MOBILE_NUMBER);
        tvMobile.setText(Utils.getNumberWithHideLast(mobileNumber));
    }

    @Override
    public void onClickAction() {
        super.onClickAction();
        btnNext.setOnClickListener(this);
        tvResend.setOnClickListener(this);

    }

    @Override
    public void onLoadedSuccess(Object o) {
      super.onLoadedSuccess(o);
        if (o instanceof MobileOtp) {
            MobileOtp mobileOtp = (MobileOtp) o;
            if (mobileOtp.getStatus()) {


            } else {
                onNextActivity(1);
                onShowMessage(mobileOtp.getMessage());
                onError(mobileOtp.getMessage());
            }
        }
    }

    @Override
    public void onShowProgressBar() {
        onShowLoading(getResources().getString(R.string.please_wait));
    }

    @Override
    public void onNextActivity(int activity) {
        super.onNextActivity(activity);
        switch (activity)
        {
            case 1:
                Intent intent=new Intent(getActivity(), MyAccount.class);
                intent.putExtra("TAG", Command.KIDS_INFO);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvResend:
                if (mobileNumber.toString().length() >= 10) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put(Constant.MOBILE_NUMBER, mobileNumber);
                    mPresenter.onHashMapParam(0,params);
                }
                break;
            case R.id.nextButton:
                //onShowMessage("show");
                onNextActivity(1);
                break;
        }
    }
}
