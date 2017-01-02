package com.example.administrator.viewfilpperdemo;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.GestureDetector;
import android.view.MotionEvent;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private int[] images = {R.drawable.icon1, R.drawable.icon2,
            R.drawable.icon3, R.drawable.icon4, R.drawable.icon5};
    private GestureDetector mGestureDetector = null;
    private ViewFlipper mViewFlipper = null;
    private static final int FLING_MIN_DISTANCE = 100;
    private static final int FLING_MIN_VELOCITY = 200;
    private Animation rInAnim, rOutAnim, lInAnim, lOutAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mGestureDetector = new GestureDetector(this, this);
        rInAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_in);
        rOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_right_out);
        lInAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
        lOutAnim = AnimationUtils.loadAnimation(this, R.anim.push_left_out);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewFlipper.addView(imageView, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewFlipper.stopFlipping();
        mViewFlipper.setAutoStart(false);
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() - motionEvent.getX() > FLING_MIN_DISTANCE &&
                Math.abs(v) > FLING_MIN_VELOCITY) {
            mViewFlipper.setInAnimation(lInAnim);
            mViewFlipper.setOutAnimation(rOutAnim);
            mViewFlipper.showPrevious();
            setTitle("相片编号：" + mViewFlipper.getDisplayedChild());
            return true;
        } else if (motionEvent.getX() - motionEvent1.getX() > FLING_MIN_DISTANCE &&
                Math.abs(v) > FLING_MIN_VELOCITY) {
            mViewFlipper.setInAnimation(rInAnim);
            mViewFlipper.setOutAnimation(lOutAnim);
            mViewFlipper.showNext();
            setTitle("相片编号：" + mViewFlipper.getDisplayedChild());
            return true;
        }
        return true;
    }
}

