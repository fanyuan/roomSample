package com.room.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.room.sample.entrys.User;

public class MyAdapter extends PagedListAdapter<User, MyAdapter.MyViewHolder> {

	public MyAdapter() {
		super(new DiffUtil.ItemCallback<User>() {
			@Override
			public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
				return oldItem.getName() == newItem.getName();
			}

			@Override
			public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
				return oldItem.getAge() == newItem.getAge();
			}
		});
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater from = LayoutInflater.from(parent.getContext());

		View view = from.inflate(R.layout.item, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

		User item = getItem(position);
		if (item == null) {
			holder.textView.setText("加载中 ...");
		} else {
			holder.textView.setText(item.getName() + " ... " + item.getAge());
		}
	}

	static class MyViewHolder extends RecyclerView.ViewHolder {

		TextView textView;

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);
			textView = itemView.findViewById(R.id.tv_display);
		}

	}
}
