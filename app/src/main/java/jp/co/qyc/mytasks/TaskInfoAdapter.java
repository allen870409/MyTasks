package jp.co.qyc.mytasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by A13054 on 2015/07/23.
 */
public class TaskInfoAdapter extends RecyclerView.Adapter<TaskInfoAdapter.TaskInfoHolder> {
    private final Context mContext;
    private List<Task> taskList;
    private final LayoutInflater mLayoutInflater;
    public TaskInfoAdapter(List<Task> taskList, Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.taskList = taskList;
    }

    @Override
    public TaskInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.task_card_view, parent, false);
        return new TaskInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskInfoHolder holder, int position) {
        Task task = taskList.get(position);
        holder.titleView.setText(task.getTitle());
        holder.dateView.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskInfoHolder extends RecyclerView.ViewHolder{
        protected TextView titleView;
        protected TextView dateView;
        public TaskInfoHolder(View v) {
            super(v);
            titleView = (TextView) v.findViewById(R.id.task_title);
            dateView = (TextView) v.findViewById(R.id.task_date);
        }
    }
}
