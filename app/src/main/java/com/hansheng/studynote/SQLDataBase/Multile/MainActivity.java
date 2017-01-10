package com.hansheng.studynote.SQLDataBase.Multile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hansheng.studynote.R;
import com.hansheng.studynote.SQLDataBase.Multile.model.Tag;
import com.hansheng.studynote.SQLDataBase.Multile.model.Todo;

import java.util.List;


public class MainActivity extends Activity {

    // Database Helper
    DatabaseHelper db;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_main);

        db = new DatabaseHelper(this);

        // Creating tags
        Tag tag1 = new Tag("Shopping");
        Tag tag2 = new Tag("Important");
        Tag tag3 = new Tag("Watchlist");
        Tag tag4 = new Tag("Androidhive");

        // Inserting tags in db
        long tag1_id = db.createTag(tag1);
        long tag2_id = db.createTag(tag2);
        long tag3_id = db.createTag(tag3);
        long tag4_id = db.createTag(tag4);

        Log.d(TAG, "Tag Count: " + db.getAllTags().size());

        // Creating ToDos
        Todo todo1 = new Todo("iPhone 5S", 0);
        Todo todo2 = new Todo("Galaxy Note II", 0);
        Todo todo3 = new Todo("Whiteboard", 0);

        Todo todo4 = new Todo("Riddick", 0);
        Todo todo5 = new Todo("Prisoners", 0);
        Todo todo6 = new Todo("The Croods", 0);
        Todo todo7 = new Todo("Insidious: Chapter 2", 0);

        Todo todo8 = new Todo("Don't forget to call MOM", 0);
        Todo todo9 = new Todo("Collect money from John", 0);

        Todo todo10 = new Todo("Post new Article", 0);
        Todo todo11 = new Todo("Take database backup", 0);

        // Inserting todos in db
        // Inserting todos under "Shopping" Tag
        long todo1_id = db.createToDo(todo1, new long[]{tag1_id});
        long todo2_id = db.createToDo(todo2, new long[]{tag1_id});
        long todo3_id = db.createToDo(todo3, new long[]{tag1_id});

        // Inserting todos under "Watchlist" Tag
        long todo4_id = db.createToDo(todo4, new long[]{tag3_id});
        long todo5_id = db.createToDo(todo5, new long[]{tag3_id});
        long todo6_id = db.createToDo(todo6, new long[]{tag3_id});
        long todo7_id = db.createToDo(todo7, new long[]{tag3_id});

        // Inserting todos under "Important" Tag
        long todo8_id = db.createToDo(todo8, new long[]{tag2_id});
        long todo9_id = db.createToDo(todo9, new long[]{tag2_id});

        // Inserting todos under "Androidhive" Tag
        long todo10_id = db.createToDo(todo10, new long[]{tag4_id});
        long todo11_id = db.createToDo(todo11, new long[]{tag4_id});

        Log.e(TAG, "Todo count: " + db.getToDoCount());

        // "Post new Article" - assigning this under "Important" Tag
        // Now this will have - "Androidhive" and "Important" Tags
        db.createTodoTag(todo10_id, tag2_id);

        // Getting all tag names
        Log.d(TAG, "Getting All Tags");

        List<Tag> allTags = db.getAllTags();
        for (Tag tag : allTags) {
            Log.d(TAG, "Tag Name" + tag.getTagName());
        }

        // Getting all Todos
        Log.d(TAG, "Getting All ToDos");

        List<Todo> allToDos = db.getAllToDos();
        for (Todo todo : allToDos) {
            Log.d(TAG, todo.getNote());
        }

        // Getting todos under "Watchlist" tag name
        Log.d(TAG, "Get todos under single Tag name");

        List<Todo> tagsWatchList = db.getAllToDosByTag(tag3.getTagName());
        for (Todo todo : tagsWatchList) {
            Log.d(TAG, "ToDo Watchlist" + todo.getNote());
        }

        // Deleting a ToDo
        Log.d(TAG, "Deleting a Todo");
        Log.d(TAG, "Tag Count Before Deleting: " + db.getToDoCount());

        db.deleteToDo(todo8_id);

        Log.d(TAG, "Tag Count After Deleting: " + db.getToDoCount());

        // Deleting all Todos under "Shopping" tag
        Log.d(TAG,
                "Tag Count Before Deleting 'Shopping' Todos: "
                        + db.getToDoCount());

        db.deleteTag(tag1, true);

        Log.d(TAG,
                "Tag Count After Deleting 'Shopping' Todos: "
                        + db.getToDoCount());

        // Updating tag name
        tag3.setTagName("Movies to watch");
        db.updateTag(tag3);

        // Don't forget to close database connection
        db.closeDB();

    }
}
