package jp.co.qyc.mytasks;

/**
 * Created by A13054 on 2015/07/23.
 */
public class Task {
    protected int id;
    protected String title;
    protected int priority;
    protected String date;

    public Task(int id, String title, int priority,  String date) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.date = date;
    }

    public Task() {
        super();
    }
}
