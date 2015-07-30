package jp.co.qyc.mytasks;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import static jp.co.qyc.mytasks.IntentCode.TASK_ADD;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyTasksActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    private MyDatabaseHandler dbHandler;


    @OnClick(R.id.add_task)
    void onClickAddTask() {
        System.out.println("#2222");
        startActivityForResult(TaskCreateActivity.createIntent(this), TASK_ADD);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tasks_layout);
        dbHandler = new MyDatabaseHandler(this);
        initView();
        ButterKnife.inject(this);
    }

    private void initView() {

        toolbar = findView(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findView(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskInfoAdapter taskInfoAdapter = new TaskInfoAdapter(dbHandler.getALllContact(), this);
        recyclerView.setAdapter(taskInfoAdapter);
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
