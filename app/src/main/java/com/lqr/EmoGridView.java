package com.lqr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * @创建者 CSDN_LQR
 * @描述 表情专用的GridView
 */
public class EmoGridView extends GridView implements AdapterView.OnItemClickListener {

    private Context mContext;
    private EmoGridViewAdapter mAdapter;
    private int mCurrentPage;

    public EmoGridView(Context context) {
        this(context, null);
    }

    public EmoGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmoGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        //设置为7列
        setNumColumns(7);
        //设置行间距
        setVerticalSpacing((int) (5 * mContext.getResources().getDisplayMetrics().density));
        //设置条目点击监听，由当前控件实现
        setOnItemClickListener(this);
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;

        //设置适配器
        mAdapter = new EmoGridViewAdapter(mContext, currentPage);
        setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //1、得到当前被点击的表情资源id
        int emoResId = mAdapter.getEmoResId(position);
        //2、得到当前被点击的表情文件名
        int index = mCurrentPage * 20 + position;
        String emoName = "smiley_" + (index > 10 ? index : "0" + index);
        //3、通过回调，交给MainActivity处理
        if (mOnEmoItemClickListener != null) {
            if (position < 20) {
                mOnEmoItemClickListener.onEmoItemClick(emoResId, emoName);
            } else {
                mOnEmoItemClickListener.onDelItemClick();
            }
        }
    }


    /*================== 条目接口回调 ==================*/
    OnEmoItemClickListener mOnEmoItemClickListener;

    public void setOnEmoItemClickListener(OnEmoItemClickListener onEmoItemClickListener) {
        mOnEmoItemClickListener = onEmoItemClickListener;
    }

    public interface OnEmoItemClickListener {
        void onEmoItemClick(int emoResId, String emoName);

        void onDelItemClick();
    }
}
