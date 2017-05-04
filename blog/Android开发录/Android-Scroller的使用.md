Scroller是一个View滚动的帮助类，在使用它之前，用户需要通过startScroll来设置滚动的参数，即起始坐标和（x,y）轴上滚动的距离。Scroller它封装了滚动的距离时间，要滚动的目标x轴和y轴，以及在每个时间内View应该滚动的（x,y）轴的坐标点，这样用户就可以在有效的滚动周期内通过Scroller的getCurX和getCurY来获取当前时刻View应该滚动的位置，然后通过View的scrollTo和scrollBy方法来进行滚动。那么如何判断滚动是否结束呢？我们只需要复写View类的computeScroll方法，该方法会在View绘制时被调用，在里面调用Scroller的computeScrollOffset来判断滚动是否完成，如果返回true表明滚动未完成，否则已完成。调用目标View的postInvalidate()或者Invalidate()已实现View的重绘。View的重绘又会导致computeScroll方法被调用，从而继续真个滚动的过程，直至computeScrollOffset返回false，即滚动结束。

**ScrollTo()是让View相对于初始位置滚动某段距离**
**ScrollBy是让View相对于当前位置滚动某段距离**

下面看一个实例
```java
public class ScrollLayout extends FrameLayout{
        private String Tag=ScrollLayout.class.getSimpleName();
        Scroller mScroller;
        public ScrollerLayout(Context context){
                  super(Context);
                  mScroller=new Scroller();
        }
        //该函数会在View重绘时被调用
        @Override
        public void computeScroll(){
                //滚动到此，View应该滚动的x，y坐标上
                if(mScroller.computeScrollerOffset()){
                          this.ScrollTo(mScroller.getCurX(),mScroller.getCurrY());
                          //请求重绘该View，从而又会导致computeScroll被调用，然后继续滚动。
                          //直到computeScrollOffset返回false
                          this.postInvalidate();
                 }  
        }
        public void scrollTo(int y){
                //参数一和参数二分别为滚动的起始点水平，竖直方向的滚动的偏移量。
                mScroller.startScroll(getScrollX(),getScrollY(),0,y);
                this.invalidate(); 
        }
}


```

下拉刷新实现
```java
public abstract class RefreshLayoutBase<T extends View> extends ViewGroup implements OnScrollListener{
      protected Scroller mScroller;
      protected View mHeaderView;
      protected View mFooterView;
      protected int mYOffset;
      protected T mContentView;
      protected int mInitScrolly=0；
      protected int mLastY=0;
      public static final int STATUS_IDLE=0;
      public static final int STATUS_PULL_TO_REFERSH=1;
      public static final int STATUS_TO_REFERSH=2;
      public static final int STATUS_REFERSHING=3;
      public static final int STATUS_LOADING=4;
      protected ImageView mArrowImageView;
      private boolean isArrowUp;
      private TextView mTipsTextView;
      private TextView mTimeTextView;
      private ProgressBar mProgressBar;
      private int mScreenHeight;
      private int mHeaderHight;
      protected OnRefreshListener mOnRefreshListener;
      protected OnLoadListener  mLoadListener;
      
      public RefreshLayoutBase(Context context){
              this(context,null);
      }
      public RefreshLayoutBase(Context context,AttributeSet attrs){
                super(context attrs);
                //初始化Scroller对象
                mScroller=new Scroller(context);
                //获取屏幕高度
                mScreenHeight=context.getResources().getDisplayMetrics().heightPixels();
                //header的高度为屏幕高度的1/4
                mHeaderHeight=mScrollerHeight/4;
                initLayout(context);
      
       }
       private final void initLayout(Context context){
                  //设置header view
                  setupHeaderView(context);
                  //设置内容视图
                  setupContentView(context);
                  //设置布局参数
                  setDefaultContentLayoutPamams();
                  //添加内容视图，如ListView、GridView等
                  addView(mContentView);
                  setupFooterView(context);
        }
            //代码省略
      }
```
Header View,内容视图，Footer view
```java
protected void setupHeaderView(Context context){
        mHeaderView=LayoutInflater.from(context).inflater(R.layout.pull_to_refresh_header,this,false);
        mHeaderView.setLayoutParams(newViewGroup.LayoutParams(LayoutParams.MATCH_PARNET,mHeaderHeight));
        //header的高度为1/4的屏幕高度，但是，他只有100px是有效的显示区域
        //取余paddingTop,这样是为了达到下拉的效果
        mHeaderView.setPadding(0,mHeaderHeight-100,0,0);
        addView(mHeaderView);
        //代码省略
 }
protected abstract void setupContentView(Context context);
protected void setupFooterView(Context context){
       mFooterView=LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_footer,this,false);
       addView(mFooterview);
    
}
//是否已经到了最顶部，子类须覆写该方法，使得mContentView滑动到最顶部时返回true
protected abstract boolean isTop(){};
//是否到了最底部，子类需要覆写该方法，使得mContentView滑动到最顶端是返回true
protected abstract boolean isBottom(){}

//丈量视图的宽高，宽度为用户设置的高度，高度则为header、content view ，footer这三个子控件的高度之和
 
@Override
protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        //MeasureSpec中的值
        int width=MeasureSpec.getSize(widthMeasureSpec);
        //子视图的个数
        int childCount=getChildCount();
        //最终的高度
        int finalHeight=0;
        for(int i=0;i<childCount;i++){
                View child=getChildAt(i);
                //测量每个子视图的尺寸
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
                //所有子视图的高度和就是该下拉刷新的总高度
                finalHeight+=child.getMeasureHeight();
        }
        setMeasureDimension(width,finalHeight);
}
//布局函数，将header、content view footer这3个View从上到下布局
//布局完成后通过Scroller滚动到Scroller滚动到header的底部，即滚动距离为header+本视图padding，从而达到隐藏header的效果。
@Override
protected void onLayout(boolean changed,int l,int t,int r,int b){
      int childCount=getChildCount();
      int left=getPaddingLeft();
      int top=getPaddingTop();
      for(int i=0;i<childCount;i++){
          View child=getChildAt(i);
          child.layout(left,top,child.getMeasureWidth(),child.getMeasureHeight()+top);  
          top+=child.getMeasureHeight();
    }
    //计算初始化滑动的y轴距离
    mInitScrollY=mHeaderView.getMeasureHeight()+getPaddingTop();
    //滑动到header view高度的位置，从而达到隐藏header view 的效果
    scrollto(0,mInitScrollY);
}
//在适当的时候拦截触摸事件，这里指的适当的时候是当马ContentView滑动到顶部，并且是下拉时拦截触摸事件，否则不拦截，交由child、view来处理
@Override
public boolean onInterceptTouchEvent(MotionEvent event){
      //获取触摸事件的类型
      final int action=MotionEventCompat.getActionEvent(MotionEvent event){
              //取消事件和抬起事件则直接返回false
              if(action==MotionEvent.ACTION_CANCEL||action==MotionEvent.AVTION_UP){
                        return false;
              }  
               switch(action){
                    case MotionEvent. ACTION_UP:
                            mLastY=(int )ev.getRawY();
                            break;
                   case MotionEvent.ACTION_MOVE:
                          mYOffset=(int)ev.getRawY()-mLastY;
                           //如果拉到了顶部，并且是下拉，则拦截触摸事件
                           //从而转到onTouchEvent来处理下拉刷新事件
                          if(isTop()&&mYOffset>0){
                                  return true;
                           }
                           break;                           
              }
              return false;
    }
}
//在河里处理触摸事件以达到下拉刷新或者上拉自动加载的问题
@override
public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction){
                case  MotionEvent.ACTION_MOVE:
                         //获取手指触摸的当前y坐标
                         int currentY=(int)event.getRawY();
                          //当前坐标减去按下时的y坐标得到y轴上的偏移量
                         mYOffset=currentY-mLastY;
                         if(mCurrentStatus!=STATUS_LOADING){
                                      //在y轴上滚动该控件
                                     changeScrollY(mYOffset);
                          }
                           //旋转header中的箭头控件
                          rotateHeaderArrow();
                           //修改header中的文本信息
                           changeTips();
                           //mLastY设置为这次的y轴坐标
                           mLastY=currentY();
                           break;
                  case MotionEvent.ACTION_UP
                          doRefresh();
                          break;
   
        }
}
```
