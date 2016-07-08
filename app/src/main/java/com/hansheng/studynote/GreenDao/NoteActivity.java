package com.hansheng.studynote.GreenDao;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.hansheng.greendao.Note;
import com.hansheng.greendao.NoteDao;
import com.hansheng.studynote.R;
import com.hansheng.studynote.StudyApplication;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;


/**
 * Created by hansheng on 2016/7/6.
 */
public class NoteActivity extends ListActivity {

//    private SQLiteDatabase db;
    private EditText editText;
//    private DaoMaster daoMaster;
//    private DaoSession daoSession;
    private Cursor cursor;
    public static final String TAG="NoteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
//    // 官方推荐将获取 DaoMaster 对象的方法放到 Application 层，这样将避免多次创建生成 Session 对象
//        setupDatabase();

        getNoteDao();

        String textcolumn=NoteDao.Properties.Text.columnName;
        String orderBy=textcolumn;
        cursor=getDb().query(getNoteDao().getTablename(),getNoteDao().getAllColumns(),null,null,null,null,orderBy);
        String[] from={textcolumn,NoteDao.Properties.Comment.columnName};
        int[] to={android.R.id.text1,android.R.id.text2};

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,android.R.layout.activity_list_item,cursor,from,to);

        setListAdapter(adapter);
        editText= (EditText) findViewById(R.id.editTextNote);
    }

//    private void setupDatabase() {
//
//        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"note-db",null);
//        db=helper.getWritableDatabase();
//        daoMaster=new DaoMaster(db);
//        daoSession=daoMaster.newSession();
//    }

    private NoteDao getNoteDao(){
        return ((StudyApplication)this.getApplicationContext()).getDaoSession().getNoteDao();
    }

    private SQLiteDatabase getDb(){
        return ((StudyApplication)this.getApplicationContext()).getDb();
    }
    /**
     * Button 点击的监听事件
     *
     * @param view
     */
    public void onMyButtonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                addNote();
                break;
            case R.id.buttonSearch:
                search();
                break;
            default:
                Log.d(TAG, "what has gone wrong ?");
                break;
        }
    }

    private void search() {
        String noteText = editText.getText().toString();
        editText.setText("");

        if (noteText == null || noteText.equals("")) {
            Toast.makeText(NoteActivity.this,"Please enter a note to query",Toast.LENGTH_SHORT).show();
        } else {
            // Query 类代表了一个可以被重复执行的查询
            Query query = getNoteDao().queryBuilder()
                    .where(NoteDao.Properties.Text.eq(noteText))
                    .orderAsc(NoteDao.Properties.Date)
                    .build();
            // 查询结果以 List 返回
            List notes = query.list();
            Toast.makeText(NoteActivity.this,"There have " + notes.size() + " records",Toast.LENGTH_SHORT).show();

        }
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        if (noteText == null || noteText.equals("")) {
        } else {
            // 插入操作，简单到只要你创建一个 Java 对象
            Note note = new Note(null, noteText, comment, new Date());
            getNoteDao().insert(note);
            Log.d(TAG, "Inserted new note, ID: " + note.getId());
            cursor.requery();
        }
    }

    /**
     * ListView 的监听事件，用于删除一个 Item
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // 删除操作，你可以通过「id」也可以一次性删除所有
        getNoteDao().deleteByKey(id);
//        getNoteDao().deleteAll();
        Log.d(TAG, "Deleted note, ID: " + id);
        cursor.requery();
    }
}
