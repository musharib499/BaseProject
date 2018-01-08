package com.innobles.smartplaykids.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.innobles.smartplaykids.R;
import com.innobles.smartplaykids.network.model.Login;
import com.innobles.smartplaykids.ui.base.BaseFragment;
import com.innobles.smartplaykids.ui.base.MvpPresenter;
import com.innobles.smartplaykids.ui.base.MyAccount;
import com.innobles.smartplaykids.ui.fragment.kidslist.KidsFragment;
import com.innobles.smartplaykids.utils.Command;
import com.innobles.smartplaykids.utils.Constant;

import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends BaseFragment {

    MvpPresenter mPresenter;
    @Nullable
    @BindView(R.id.etMobileNumber)
    EditText etMobile;

    @BindView(R.id.nextButton)
    Button btnNext;

    @BindView(R.id.tv_message)
    TextView tvMessage;

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_login, container, false);
        setUnBinder(ButterKnife.bind(this, v));
        onAttach(getActivity());
        setUp(v);
        return v;
    }

    @Override
    protected void setUp(View view) {
        mPresenter=new LoginPresenter(this);
        setTitleMessage(getString(R.string.login));
        onClickAction();
    }
    @Override
    public void onLoadedSuccess(Object o) {
        super.onLoadedSuccess(o);
        if (o instanceof Login ) {
            Login login = (Login) o;
            if (login.getStatus()) {
                onShowMessage(login.getMessage());
                login.setMobileNumber(etMobile.getText().toString());
                getSmartApplication().setLoginResponse(login);

                onNextActivity(1);


            } else {

                tvMessage.setVisibility(View.VISIBLE);
                onShowMessage(login.getMessage());
                etMobile.setError(getString(R.string.mobile_not_registered_in_school));
            }
        }
    }

    @Override
    public void onLoadedFailure(Throwable error) {
        super.onLoadedFailure(error);
         onErrorThrowable(error);

    }

    @Override
    public void onShowProgressBar() {
         onShowLoading(getResources().getString(R.string.please_wait));
        tvMessage.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClickAction() {
            btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextButton:
                etMobile.setError(null);
                if (etMobile.getText().toString().length()>=10) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put(Constant.MOBILE_NUMBER, etMobile.getText().toString());
                    mPresenter.onHashMapParam(0,params);
                }else {
                    etMobile.setError(getString(R.string.enter_correct_mobile_number));
                }
                break;
        }
    }

    @Override
    public void onNextActivity(int activity) {
        super.onNextActivity(activity);
        if (activity==1)
        {
            Intent intent=new Intent(getActivity(), MyAccount.class);
            intent.putExtra("TAG", Command.MOBILE_OTP);
            Bundle bundle=new Bundle();
            bundle.putString(Constant.MOBILE_NUMBER,etMobile.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            getActivity().finish();
        }
    }



}
