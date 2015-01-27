package com.example.listviewrefresh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.listviewrefresh.view.XListView;
import com.example.listviewrefresh.view.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity implements IXListViewListener {
	XListView listView;
	private List<HashMap<String, String>> list;
	private ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (XListView) findViewById(R.id.listview);
		listView.setXListViewListener(this);
		listView.setPullLoadEnable(true);
		list = getListDate();
		adapter = new ListViewAdapter(this, list);
		listView.setAdapter(adapter);
	}

	private List<HashMap<String, String>> getListDate() {
		list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 15; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", "��" + i + "item");
			list.add(map);
		}
		return list;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onRefresh() {
		myThread(0);
	}

	@Override
	public void onLoadMore() {
		myThread(1);
	}

	/**
	 * 
	 * @param msg
	 *            0Ϊ����ˢ�� 1Ϊ���ظ���
	 */
	private void myThread(final int msg) {
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					handler.sendEmptyMessage(msg);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

			}
		}.start();
	}

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				listView.stopRefresh();
				listView.stopLoadMore();
				listView.setRefreshTime(getDate());

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", "ˢ�µõ���item");
//				list.add(map);
				list.add(0, map);
				adapter.notifyDataSetChanged();
				break;
			case 1:
				listView.stopRefresh();
				listView.stopLoadMore();
				listView.setRefreshTime(getDate());

				HashMap<String, String> map1 = new HashMap<String, String>();
				map1.put("name", "���ظ���õ���item");
				list.add(map1);
				
				adapter.notifyDataSetChanged();
				break;
			}
		};
	};

	/**
	 * �õ�ˢ��ʱ��
	 * 
	 * @return
	 */
	public static String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
}
