package com.example.listviewrefresh;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	Context mContext;
	private List<HashMap<String, String>> mlist;

	public ListViewAdapter(Context context, List<HashMap<String, String>> list) {
		mContext = context;
		mlist = list;
	}

	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO �Զ����ɵķ������
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_item, null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.textView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.textView.setText(mlist.get(position).get("name"));
		return convertView;
	}

	public class Holder {
		TextView textView;
	}
}
