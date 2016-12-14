package com.lqr;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @创建者 CSDN_LQR
 * @描述 表情ViewPager的适配器
 */
public class EmoViewPagerAdaper extends PagerAdapter {

    /*------------------ 这里主要是传送表情的点击事件（因为MainActivity与EmoGridView中间隔了一个ViewPager）begin ------------------*/

    private EmoGridView.OnEmoItemClickListener mOnEmoItemClickListener;

    public EmoGridView.OnEmoItemClickListener getOnEmoItemClickListener() {
        return mOnEmoItemClickListener;
    }

    public void setOnEmoItemClickListener(EmoGridView.OnEmoItemClickListener onEmoItemClickListener) {
        mOnEmoItemClickListener = onEmoItemClickListener;
    }

    /*------------------ 这里主要是传送表情的点击事件（因为MainActivity与EmoGridView中间隔了一个ViewPager）end ------------------*/
    @Override
    public int getCount() {
        //表情共5页
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        EmoGridView emoGridView = new EmoGridView(container.getContext());
        emoGridView.setCurrentPage(position);
        //传递由MainActivity设置的表情点击监听
        emoGridView.setOnEmoItemClickListener(getOnEmoItemClickListener());
        container.addView(emoGridView);
        return emoGridView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
