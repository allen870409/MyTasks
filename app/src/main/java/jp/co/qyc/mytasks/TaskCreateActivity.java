package jp.co.qyc.mytasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static jp.co.qyc.mytasks.IntentCode.EXTRA_ID;

public class TaskCreateActivity extends AppCompatActivity {

    public static final String TAG = AppCompatActivity.class.getSimpleName();

    private Task task;
    private MyDatabaseHandler myDatabaseHandler;
    @InjectView(R.id.edit_title)
    protected EditText editTitle;

    private MenuItem mDoneMenuItem;

    @OnClick(R.menu.task_add_button)
    protected void onClickDone(){

    }

    public static Intent createIntent(Context context) {
        return new Intent(context, TaskCreateActivity.class);
    }

    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, MyTasksActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_add);
        ButterKnife.inject(this);

        myDatabaseHandler = new MyDatabaseHandler(this);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.task_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int id = (extras.containsKey(EXTRA_ID)) ? extras.getInt(EXTRA_ID) : -1;
            if (id != -1) {
                task = myDatabaseHandler.find(id + "");
            }else{
                task = new Task();
            }
            editTitle.setText(task.title);
            getSupportActionBar().setTitle(R.string.task_update);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    private void saveTask() {
        if (task == null) {
            myDatabaseHandler.insert(editTitle.getText().toString(), "111222211");
        } else {
            myDatabaseHandler.update(task.id + "", editTitle.getText().toString(), "11111");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m_done:
                saveTask();
            case android.R.id.home:
                finishTodoCreateActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void finishTodoCreateActivity() {
        finish();
        Intent intent = new Intent(this, MyTasksActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_add_button, menu);
        mDoneMenuItem = (MenuItem) menu.findItem(R.id.m_done);
        updateDoneMenuItem(editTitle.getText().toString());
        return true;
    }

    private void updateDoneMenuItem(String string) {
        if (string.length() > 0) {
            mDoneMenuItem.setEnabled(true);
            mDoneMenuItem.getIcon().setAlpha(255);
        } else {
            mDoneMenuItem.setEnabled(false);
            mDoneMenuItem.getIcon().setAlpha(127);
        }
    }
}
