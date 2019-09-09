package com.giussep.ricardo.napoleonsystems.detail;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.giussep.ricardo.napoleonsystems.R;
import com.giussep.ricardo.napoleonsystems.model.UserApi;
import com.giussep.ricardo.napoleonsystems.root.ApplicationClass;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements DetailContract.View {

    @Inject
    DetailContract.Presenter presenter;
    @BindView(R.id.textView_name)
    TextView textViewName;
    @BindView(R.id.textView_username)
    TextView textViewUsername;
    @BindView(R.id.textView_email)
    TextView textViewEmail;
    @BindView(R.id.textView_phone)
    TextView textViewPhone;
    @BindView(R.id.textView_website)
    TextView textViewWebsite;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.textView_street)
    TextView textViewStreet;
    @BindView(R.id.textView_suite)
    TextView textViewSuite;
    @BindView(R.id.textView_city)
    TextView textViewCity;
    @BindView(R.id.textView_zip_code)
    TextView textViewZipCode;
    @BindView(R.id.textView_latitude)
    TextView textViewLatitude;
    @BindView(R.id.textView_longitude)
    TextView textViewLongitude;
    @BindView(R.id.textView_company_name)
    TextView textViewCompanyName;
    @BindView(R.id.textView_catch_phrase)
    TextView textViewCatchPhrase;
    @BindView(R.id.textView_bs)
    TextView textViewBs;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        ((ApplicationClass) context.getApplicationContext()).getComponent().inject(this);
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.getUserInfo(DetailFragmentArgs.fromBundle(getArguments()).getUserId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //region Implementation DetailContract.View

    @Override
    public void showDialogProgress() {
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideDialogProgress() {
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserInfo(UserApi userApi) {
        textViewName.setText(userApi.getName());
        textViewUsername.setText(userApi.getUsername());
        textViewEmail.setText(userApi.getEmail());
        textViewPhone.setText(userApi.getPhone());
        textViewWebsite.setText(userApi.getWebsite());

        textViewStreet.setText(userApi.getAddress().getStreet());
        textViewSuite.setText(userApi.getAddress().getSuite());
        textViewCity.setText(userApi.getAddress().getCity());
        textViewZipCode.setText(userApi.getAddress().getZipcode());
        textViewLatitude.setText(userApi.getAddress().getGeo().getLat());
        textViewLongitude.setText(userApi.getAddress().getGeo().getLng());

        textViewCompanyName.setText(userApi.getCompany().getName());
        textViewCatchPhrase.setText(userApi.getCompany().getCatchPhrase());
        textViewBs.setText(userApi.getCompany().getBs());

    }

    @Override
    public void showErrorLoadingUserInfo(String message) {

    }


    //endregion

}
