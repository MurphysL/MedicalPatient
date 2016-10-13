package com.medical.patient.widget.answer;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medical.patient.R;


/**
 * Created by chenjun on 2016/10/10.
 */
public class AnswerItem extends LinearLayout {

    private TextView mChoice, mChoiceContent;

    public AnswerItem(Context context) {
        this(context, null);
    }

    public AnswerItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.survey_answer_item, null);
        mChoice = (TextView) view.findViewById(R.id.tv_choice);
        mChoiceContent = (TextView) view.findViewById(R.id.tv_choice_content);
        addView(view, lp);
    }

    public void setChoiceContent(String content) {
        mChoiceContent.setText(content);
    }

    public void resetChoice(Context context) {
        mChoice.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_200));
    }

    public void click() {
        mChoice.setBackgroundResource(R.mipmap.mplib_icon_true);
        mChoice.setText("");
    }
}

