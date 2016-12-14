package com.lqr;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @创建者 CSDN_LQR
 * @描述 表情GridView适配器
 */

public class EmoGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private int mEmoSize;//每个表情的大小
    private int mCurrentPage;//当前页数

    public EmoGridViewAdapter(Context context, int currentPage) {
        mContext = context;
        mEmoSize = (int) (25 * mContext.getResources().getDisplayMetrics().density);
        mCurrentPage = currentPage;
    }

    /**
     * 得到对应的position的表情id
     *
     * @param position
     * @return
     */
    public int getEmoResId(int position) {
        return 20 * mCurrentPage + position + R.mipmap.smiley_00;
    }

    @Override
    public int getCount() {
        //每页20个表情+1个删除按钮，共有100个表情
        return 21;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout ll = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mEmoSize, mEmoSize);
        ImageView iv = new ImageView(viewGroup.getContext());

        int emoResId;
        if (i < 20) {
            emoResId = getEmoResId(i);
        } else {
            emoResId = R.mipmap.del_btn_nor;
        }

        iv.setImageResource(emoResId);
        iv.setLayoutParams(params);
        ll.addView(iv);
        ll.setGravity(Gravity.CENTER);

        return ll;
    }


    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
