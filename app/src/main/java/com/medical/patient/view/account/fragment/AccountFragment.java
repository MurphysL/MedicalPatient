package com.medical.patient.view.account.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medical.patient.R;
import com.medical.patient.view.account.activity.AccountDetailsActivity;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by chenjun on 2016/10/7.
 */
public class AccountFragment extends Fragment {
    private View view;
    private Toolbar toolbar;
    private RelativeLayout layout_account;
    private ImageView avatar;
    private TextView name;
    private TagGroup mTagGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();
        initViews();
    }

    private void bindViews() {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        layout_account = (RelativeLayout) view.findViewById(R.id.layout_account);
        mTagGroup = (TagGroup) view.findViewById(R.id.account_desc);
    }

    private void initViews() {
        toolbar.setTitle("");

        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AccountDetailsActivity.class));
            }
        });

        mTagGroup.setTags("男 23岁");
    }

}
