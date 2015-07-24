package jp.co.qyc.mytasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by A13054 on 2015/07/23.
 */
public class TaskInfoHolder extends RecyclerView.ViewHolder{

    protected TextView titleView;
    protected TextView dateView;
    public TaskInfoHolder(View itemView) {
        super(itemView);
        titleView = findView(R.id.task_title);
        dateView = findView(R.id.task_date);
    }

    protected <T extends View> T findView(int id) {
        return (T) findView(id);
    }
}
