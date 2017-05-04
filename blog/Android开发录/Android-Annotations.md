自定义注解
通过阅读注解类的源码可以发现，任何一个注解类都有如下特征：

注解类会被@interface标记；

注解类的顶部会被@Documented、@Retention、@Target、@Inherited这四个注解标记（@Documented、@Inherited可选，@Retention、@Target必须要有）；


注解的作用
**格式检查：** 告诉编译器信息，比如被@Override标记的方法如果不是父类的某个方法，IDE会报错；

**减少配置：** 运行时动态处理，得到注解信息，实现代替配置文件的功能；

**减少重复工作：** 比如第三方框架xUtils，通过注解@ViewInject减少对findViewById的调用，类似的还有（JUnit、ActiveAndroid等）；


AndroidAnnotions是一个能够让你快速进行Android开发的开源框架，它能让你专注于真正重要的地方。是代码更加精简，是项目更加容易维护，它的目标是“Fast Android Development.Easy maintainance”
Here is a simple example of how your code can dramatically shrink, and become much easier to understand
Before
```java
public class BookmarksToClipboardActivity extends Activity {
 
 BookmarkAdapter adapter;
 
 ListView bookmarkList;
 
 EditText search;
 
 BookmarkApplication application;
 
 Animation fadeIn;
  //获取系统service的方法(取代原来的clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);)
 ClipboardManager clipboardManager;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 
 requestWindowFeature(Window.FEATURE_NO_TITLE);
 getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
 
 setContentView(R.layout.bookmarks);
 
 bookmarkList = (ListView) findViewById(R.id.bookmarkList);
 search = (EditText) findViewById(R.id.search);
 application = (BookmarkApplication) getApplication();
 fadeIn = AnimationUtils.loadAnimation(this, anim.fade_in);
 clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
 
 View updateBookmarksButton1 = findViewById(R.id.updateBookmarksButton1);
 updateBookmarksButton1.setOnClickListener(new OnClickListener() {
 
 @Override
 public void onClick(View v) {
 updateBookmarksClicked();
 }
 });
 
 View updateBookmarksButton2 = findViewById(R.id.updateBookmarksButton2);
 updateBookmarksButton2.setOnClickListener(new OnClickListener() {
 
 @Override
 public void onClick(View v) {
 updateBookmarksClicked();
 }
 });
 
 bookmarkList.setOnItemClickListener(new OnItemClickListener() {
 
 @Override
 public void onItemClick(AdapterView<?> p, View v, int pos, long id) {
 Bookmark selectedBookmark = (Bookmark) p.getAdapter().getItem(pos);
 bookmarkListItemClicked(selectedBookmark);
 }
 });
 
 initBookmarkList();
 }
 
 void initBookmarkList() {
 adapter = new BookmarkAdapter(this);
 bookmarkList.setAdapter(adapter);
 }
 
 void updateBookmarksClicked() {
 UpdateBookmarksTask task = new UpdateBookmarksTask();
 
 task.execute(search.getText().toString(), application.getUserId());
 }
 
 private static final String BOOKMARK_URL = //
 "http://www.bookmarks.com/bookmarks/{userId}?search={search}";
 
 
 class UpdateBookmarksTask extends AsyncTask<String, Void, Bookmarks> {
 
 @Override
 protected Bookmarks doInBackground(String... params) {
 String searchString = params[0];
 String userId = params[1];
 
 RestTemplate client = new RestTemplate();
 HashMap<String, Object> args = new HashMap<String, Object>();
 args.put("search", searchString);
 args.put("userId", userId);
 HttpHeaders httpHeaders = new HttpHeaders();
 HttpEntity<Bookmarks> request = new HttpEntity<Bookmarks>(httpHeaders);
 ResponseEntity<Bookmarks> response = client.exchange( //
 BOOKMARK_URL, HttpMethod.GET, request, Bookmarks.class, args);
 Bookmarks bookmarks = response.getBody();
 
 return bookmarks;
 }
 
 @Override
 protected void onPostExecute(Bookmarks result) {
 adapter.updateBookmarks(result);
 bookmarkList.startAnimation(fadeIn);
 }
 
 }
 
 void bookmarkListItemClicked(Bookmark selectedBookmark) {
 clipboardManager.setText(selectedBookmark.getUrl());
 }
 
}
```

after
```java
@Fullscreen//全屏
@EActivity(R.layout.bookmarks)// Sets content view to R.layout.translate
@WindowFeature(Window.FEATURE_NO_TITLE)
public class BookmarksToClipboardActivity extends Activity {
 
 BookmarkAdapter adapter;
 
 @ViewById
 ListView bookmarkList;
 
 @ViewById  
 EditText search;
 
 @App
 BookmarkApplication application;
 
 @RestService
 BookmarkClient restClient;
 
 @AnimationRes  // Injects android.R.anim.fade_in
 Animation fadeIn;
 
 @SystemService
 ClipboardManager clipboardManager;
 //因为在onCreate()被调用的时候，@ViewById还没有被set，也就是都为null
  //所以如果你要对组件进行一定的初始化，那么你要用@AfterViews注解
 @AfterViews
 void initBookmarkList() {
 adapter = new BookmarkAdapter(this);
 bookmarkList.setAdapter(adapter);
 }
 
 @Click({R.id.updateBookmarksButton1,  R.id.updateBookmarksButton2}) // When R.id.doTranslate button is clicked 
 void updateBookmarksClicked() {
 searchAsync(search.getText().toString(), application.getUserId());
 }
 
 @Background  // Executed in a background thread
 void searchAsync(String searchString, String userId) {
 Bookmarks bookmarks = restClient.getBookmarks(searchString, userId);
 updateBookmarks(bookmarks);
 }
 
 @UiThread    // Executed in the ui thread
 void updateBookmarks(Bookmarks bookmarks) {
 adapter.updateBookmarks(bookmarks);
 bookmarkList.startAnimation(fadeIn);
 }
 
 @ItemClick
 void bookmarkListItemClicked(Bookmark selectedBookmark) {
 clipboardManager.setText(selectedBookmark.getUrl());
 }
 
}

// private static final String BOOKMARK_URL = "http://www.bookmarks.com/bookmarks/{userId}?search={search}";

@Rest("http://www.bookmarks.com")
public interface BookmarkClient {
 
 @Get("/bookmarks/{userId}?search={search}")
 Bookmarks getBookmarks(@Path String search, @Path String userId);
 
}
```
