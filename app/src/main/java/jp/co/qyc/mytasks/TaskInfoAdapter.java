package jp.co.qyc.mytasks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by A13054 on 2015/07/23.
 */
public class TaskInfoAdapter extends RecyclerView.Adapter<TaskInfoHolder> {

    private List<Task> taskList;

    public TaskInfoAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("=======01=======" + taskList);
        System.out.println("=======02=======" + viewType);
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.task_card_view, null);
        return new TaskInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskInfoHolder holder, int position) {
        System.out.println("=======1=======" + taskList);
        System.out.println("=======2=======" + position);
        Task task = taskList.get(position);
        holder.titleView.setText(task.getTitle());
        holder.dateView.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
