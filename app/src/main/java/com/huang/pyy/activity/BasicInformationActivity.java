package com.huang.pyy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.huang.pyy.R;
import com.huang.pyy.bean.CardBean;
import com.huang.pyy.bean.JsonBean;
import com.huang.pyy.util.GsonParseUtil;
import com.huang.pyy.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicInformationActivity extends BasicActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.et_wechat)
    EditText etWechat;
    @BindView(R.id.ll_wchat)
    LinearLayout llWchat;
    @BindView(R.id.et_address_now)
    EditText etAddressNow;
    @BindView(R.id.ll_address_now)
    LinearLayout llAddressNow;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.et_relation_with_me)
    TextView etRelationWithMe;
    @BindView(R.id.ll_relation_with_me)
    LinearLayout llRelationWithMe;
    @BindView(R.id.et_relation_person_name)
    EditText etRelationPersonName;
    @BindView(R.id.et_relation_person_phone)
    EditText etRelationPersonPhone;
    //关系选择器
    private OptionsPickerView mRelationOption;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    public static void open(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BasicInformationActivity.class);
        context.startActivity(intent);
    }

    /**
     * 地区选择器
     */
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private boolean isCanShow = true;

    @Override
    void setRootView() {
        setContentView(R.layout.activity_basic_information);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initBottomBtn();
        setTitle(getString(R.string.my_information));
        setBottomBtnText(getString(R.string.next));

        new GsonParseUtil(this, new GsonParseUtil.ParseCallback() {
            @Override
            public void parseSuccess(ArrayList<JsonBean> list, ArrayList<ArrayList<String>> list2, ArrayList<ArrayList<ArrayList<String>>> list3) {
                options1Items.addAll(list);
                options2Items.addAll(list2);
                options3Items.addAll(list3);
            }

            @Override
            public void parseFailed() {
                isCanShow = false;
            }
        });
    }

    @OnClick({R.id.ll_address_now,R.id.ll_relation_with_me,R.id.et_relation_with_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address_now: {
                if (!isCanShow) {
                    ToastUtil.showMsg(BasicInformationActivity.this, "地区文件解析错误");
                    return;
                }
                showPickerView();
                break;
            }
            case R.id.et_relation_with_me:
            case R.id.ll_relation_with_me:{
                initCustomOptionPicker();
                break;
            }
            default:
                break;
        }
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        cardItem.add(new CardBean(1,"父母"));
        cardItem.add(new CardBean(2,"配偶"));
        cardItem.add(new CardBean(3,"子女"));
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        mRelationOption = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                etRelationWithMe.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mRelationOption.returnData();
                                mRelationOption.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mRelationOption.dismiss();
                            }
                        });

//                        tvAdd.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                getCardData();
//                                mRelationOption.setPicker(cardItem);
//                            }
//                        });

                    }
                })
                .build();

        mRelationOption.setPicker(cardItem);//添加数据
        mRelationOption.show();


    }

    private void showPickerView() {// 弹出地区选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                etAddressNow.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
