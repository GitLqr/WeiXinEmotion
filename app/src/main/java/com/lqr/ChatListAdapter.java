package com.lqr;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 聊天列表的适配器
 */
public class ChatListAdapter extends BaseAdapter {

    private List<Message> mMsgList;

    public ChatListAdapter(List<Message> msgList) {
        mMsgList = msgList;
    }

    @Override
    public int getCount() {
        return mMsgList.size();
    }

    @Override
    public Message getItem(int i) {
        return mMsgList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_chat_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Message msg = getItem(i);
        String msgContent = msg.getContent();
        SpannableStringBuilder ssb = EmoParser.parseContent(viewGroup.getContext(), msgContent);
        if (msg.isFromMe()) {
            holder.mRlMe.setVisibility(View.VISIBLE);
            holder.mRlFriend.setVisibility(View.GONE);
            holder.mTvMe.setText(ssb);
        } else {
            holder.mRlMe.setVisibility(View.GONE);
            holder.mRlFriend.setVisibility(View.VISIBLE);
            holder.mTvFriend.setText(ssb);
        }

        return view;
    }

    class ViewHolder {
        RelativeLayout mRlMe;
        RelativeLayout mRlFriend;
        TextView mTvMe;
        TextView mTvFriend;

        public ViewHolder(View view) {
            mRlMe = (RelativeLayout) view.findViewById(R.id.rlMe);
            mRlFriend = (RelativeLayout) view.findViewById(R.id.rlFriend);
            mTvMe = (TextView) view.findViewById(R.id.tvMe);
            mTvFriend = (TextView) view.findViewById(R.id.tvFriend);
        }
    }
}
