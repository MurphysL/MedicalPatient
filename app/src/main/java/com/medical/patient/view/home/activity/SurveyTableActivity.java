package com.medical.patient.view.home.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.medical.patient.R;
import com.medical.patient.controller.ProTableController;
import com.medical.patient.model.AnswerItemBean;
import com.medical.patient.model.TableItemBean;
import com.medical.patient.widget.answer.AnswerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SurveyTableActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Context mContext;
    private TextView survey_answers_progress;
    private ScrollView survey_main;
    private TextView survey_question;
    private LinearLayout survey_answers;
    private TextView next_table;

    public static SurveyTableActivity instance;

    // 当前题目的题号
    private int count = 1;
    // 所有的题目数
    private int number = 0;

    // 题目名称
    private String tableItemTitle;
    // 题目序号
    private int tableItemNum;

    private Map<Integer, TableItemBean> tables;
    private List<AnswerItem> answerItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_table);

        mContext = this;
        instance = this;
        tables = ProTableController.proTable.getMap();
        answerItemList = new ArrayList<>();

        bindViews();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        count = 1;
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        survey_answers_progress = (TextView) findViewById(R.id.survey_answers_progress);
        survey_main = (ScrollView) findViewById(R.id.survey_main);
        survey_question = (TextView) findViewById(R.id.survey_question);
        survey_answers = (LinearLayout) findViewById(R.id.survey_answers);
        next_table = (TextView) findViewById(R.id.next_table);
    }

    private void initViews() {
        toolbar.setTitle(ProTableController.proTable.getJibing());
        toolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white));
        toolbar.setNavigationIcon(R.mipmap.mplib_back);

        number = tables.size();

        setListeners();
        initTableItem(count);
    }

    private void setListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 点击下一题
        next_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 答案这儿要将之前添加的选项去掉
                survey_answers.removeAllViews();
                answerItemList.clear();
                count++;
                if (count > number) {

                } else {
                    initTableItem(count);
                }

            }
        });
    }

    // 初始化选项
    private void initChoice(String content) {
        AnswerItem answer = new AnswerItem(mContext);
        answer.setChoiceContent(content);

        // 为了实现点击高亮的效果 需要将survey_answers的背景重置
        survey_answers.setBackgroundColor(ContextCompat.getColor(mContext, R.color.grey_200));

        // 将选项加到list中
        answerItemList.add(answer);

        answer.setOnClickListener(new AnswerItemClick(answer));
        survey_answers.addView(answer);
    }

    private class AnswerItemClick implements View.OnClickListener {

        private AnswerItem answerItem;

        public AnswerItemClick(AnswerItem answerItem) {
            this.answerItem = answerItem;
        }

        @Override
        public void onClick(View v) {
            answerItem.click();
            for (int i = 0; i < answerItemList.size(); i++) {
                if (v != answerItemList.get(i))
                    answerItem.resetChoice(mContext);
            }
        }
    }

    // 加载题目
    private void initTableItem(int key) {
        TableItemBean tableItem = tables.get(key);
        tableItemTitle = tableItem.getName();
        tableItemNum = tableItem.getXuhao();

        survey_question.setText(tableItemTitle);
        survey_answers_progress.setText(tableItemNum + " / " + number);

        List<AnswerItemBean> answerItems = tableItem.getList();
        for (int i = 0; i < answerItems.size(); i++) {
            initChoice(answerItems.get(i).getXuanxiang());
        }
    }
}
