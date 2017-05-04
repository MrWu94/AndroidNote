线程只要分为：主线程和子线程，主线程主要处理和界面相关的事情，而子线程则往往用于执行耗时的操作，由于Android的特性，如果在主线程中执行耗时操作那么就会导致程序无法及时响应，因此耗时操作必须方法子线程中去执行.
###AsyncTask
AsyncTask是一种轻量级的异步任务类，它可以在线程池中执行后台任务，然后把执行的进度和最终结果传递到主线程并在主线程中更新UI.从实现上来说，AsyncTask封装了Thread和Handler，通过AsyncTask可以更加方便地执行后台任务以及在主线程中访问UI,但是AsyncTask并不适合执行特别耗时的后台任务。
AsyncTask是一个**抽象的泛型类**，所以如果我们想要使用它，就必须创建一个子类去继承它，在继承时我们可以为AsyncTask类指定三个泛型类。
```
public abstract class AsyncTask<Params, Progress, Result>
```
1. Params
在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。
2. Progress
后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
3. Result
当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。
一个简单的自定义AsyncTask可以写成如下：
```
  class MyAsyncTask extends AsyncTask<Void,Integer,Boolean>{
        //这个方法会在子线程中运行，我们在这里执行耗时的操作，任务一旦完成，就通过return语句将任务执行的结果进行返回
        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }
        //这个方法会在后台执行之间调用，用于初始化一些界面上的一些操作，比如一个进度条显示框
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
        //当后台任务执行完毕并通过return语句返回时，这个方法就很快就会被调用，返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行Ui操作，比如说提醒任务执行的结果，以及关闭对话框
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
new MyAsyncTask.execute();//执行任务
```
AsyncTask的工作原理
为了分析AsyncTask的工作原理，我们从他的execute方法开始分析，execute()方法会调用executeOnExecutor方法
``` 
public final AsyncTask<params,Progress,Result> execute(Params...params){
      return executeOnExecutor(sDefaultExecutor,params);
}
public final AsyncTask<Params,Progress,Result>executeOnEcecutor(Executor exec,Params...parsms){
        if(mStatus!=Status.PENDING){
                switch(mStatus){
                          case RUNNING:
                                 throw new IllegalStateException("cannot execute task");
                           case FINISHED:
                                 throw new IllegalStateException("cannot execute task"+"the task has aleadly been ececuted")+"a task can be executed only once";                  
                  }
        }
}
```
从上面的代码中，sDefaultExecutor实际上是一个串行的线程池，一个进程中所有的AsyncTask全部在这个串行的线程中排队执行，这个排队执行的过程后面再进行分析。在executeOnExecutor方法中，AsyncTask的onpreExecute方法最先执行，然后线程池开始执行。



