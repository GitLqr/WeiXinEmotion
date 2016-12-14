package com.lqr;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmoGridView.OnEmoItemClickListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private List<Message> mMsgList;

    private ListView mLvChat;
    private EditText mEtContent;
    private ImageView mIvEmo;
    private Button mBtnSend;
    private ViewPager mVpEmo;
    private EmoViewPagerAdaper mVpEmoAdaper;
    private DotView mDv;
    private ChatListAdapter mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsgList = new ArrayList<>();
        initView();
        initVpEmo();

    }

    private void initView() {
        mLvChat = (ListView) findViewById(R.id.lvChat);
        mChatListAdapter = new ChatListAdapter(mMsgList);
        mLvChat.setAdapter(mChatListAdapter);
        mEtContent = (EditText) findViewById(R.id.etContent);
        mIvEmo = (ImageView) findViewById(R.id.ivEmo);
        mBtnSend = (Button) findViewById(R.id.btnSend);
        mBtnSend.setOnClickListener(this);
        mVpEmo = (ViewPager) findViewById(R.id.vpEmo);
        mDv = (DotView) findViewById(R.id.dv);
        mDv.initData(5, 0);
    }

    private void initVpEmo() {
        mVpEmoAdaper = new EmoViewPagerAdaper();
        mVpEmo.setAdapter(mVpEmoAdaper);
        mVpEmo.setOnPageChangeListener(this);
        mVpEmoAdaper.setOnEmoItemClickListener(this);
    }

    @Override
    public void onEmoItemClick(int emoResId, String emoName) {

        emoName = "[" + emoName + "]";
        //创建可以图文混排的类
        SpannableString ssb = new SpannableString(emoName);
        //得到图片
        Drawable drawable = getResources().getDrawable(emoResId);
        int size = (int) (25 * getResources().getDisplayMetrics().density);
        drawable.setBounds(0, 0, size, size);
        //创建ImageSpan
        ImageSpan is = new ImageSpan(drawable);
        //对文字进行图片替换（Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 表示删除时一个个删）
        ssb.setSpan(is, 0, emoName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //得到当前输入框中的内容
        Editable originalContent = mEtContent.getText();
        //得到当前输入框中的光标位置
        int selectionEnd = mEtContent.getSelectionEnd();

        //在光标处插入内容
        originalContent.insert(selectionEnd, ssb);
//        if (selectionEnd < originalContent.length()) {
//            originalContent.insert(selectionEnd, ssb);
//        } else {
//            originalContent.append(ssb);
//        }

    }

    @Override
    public void onDelItemClick() {
        //因为不知道具体表情文字内容的长度，所以模拟系统的删除键功能
        mEtContent.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        mEtContent.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //当ViewPage页面切换时，设置下方小圆点的显示
        mDv.changeCurrentPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend://发送内容
                mMsgList.add(new Message(mEtContent.getText().toString(), true));
                mEtContent.setText("");
                receiveFromFriend();
                mChatListAdapter.notifyDataSetChanged();
                mLvChat.setSelection(mMsgList.size());
                break;
        }
    }

    /**
     * 收到来自朋友的的消息
     */
    private void receiveFromFriend() {
        mMsgList.add(new Message("呵呵", false));
    }
}
