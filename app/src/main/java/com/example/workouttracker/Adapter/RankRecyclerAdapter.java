package com.example.workouttracker.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.R;
import com.example.workouttracker.user.model.User;

import java.util.List;

public class RankRecyclerAdapter extends RecyclerView.Adapter<RankRecyclerAdapter.ViewHolder> {
    List<User> AllUser;
    List<Integer> calorieBurnedList;
    private Context context;
    int yourPosition;

    public RankRecyclerAdapter(List<User> AllUser, List<Integer> calorieBurnedList, Context context, int yourPosition) {
        this.AllUser = AllUser;
        this.calorieBurnedList = calorieBurnedList;
        this.context = context;
        this.yourPosition = yourPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_layout, parent, false);
        RankRecyclerAdapter.ViewHolder viewHolder = new RankRecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.txtView_userName.setText(AllUser.get(position).getUserName());
        holder.txtView_calorieBurned.setText(calorieBurnedList.get(position) + "");
        if (position == 0) {
            holder.txtView_rank.setBackground(new ColorDrawable(0xFFFFD700));
            holder.txtView_userName.setBackground(new ColorDrawable(0xFFFFD700));
            holder.txtView_calorieBurned.setBackground(new ColorDrawable(0xFFFFD700));
        } else if (position == 1) {
            holder.txtView_rank.setBackground(new ColorDrawable(0xFFC0C0C0));
            holder.txtView_userName.setBackground(new ColorDrawable(0xFFC0C0C0));
            holder.txtView_calorieBurned.setBackground(new ColorDrawable(0xFFC0C0C0));
        } else if (position == 2) {
            holder.txtView_rank.setBackground(new ColorDrawable(0xFFCD7F32));
            holder.txtView_userName.setBackground(new ColorDrawable(0xFFCD7F32));
            holder.txtView_calorieBurned.setBackground(new ColorDrawable(0xFFCD7F32));
        }

        if (position == yourPosition) {
            holder.txtView_rank.setText("*" + (position + 1) + "");
            holder.txtView_rank.setBackground(new ColorDrawable(0xFF00FF00));
            holder.txtView_userName.setBackground(new ColorDrawable(0xFF00FF00));
            holder.txtView_calorieBurned.setBackground(new ColorDrawable(0xFF00FF00));
            holder.txtView_rank.setTextColor(0xFF000000);
            holder.txtView_userName.setTextColor(0xFF000000);
            holder.txtView_calorieBurned.setTextColor(0xFF000000);
        } else {
            holder.txtView_rank.setText((position + 1) + "");
        }
    }

    @Override
    public int getItemCount() {
        return calorieBurnedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView_rank, txtView_userName, txtView_calorieBurned;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView_rank = itemView.findViewById(R.id.txtView_rank);
            txtView_userName = itemView.findViewById(R.id.txtView_userName);
            txtView_calorieBurned = itemView.findViewById(R.id.txtView_calorieBurned);
        }
    }
}
