package ir.satintech.isfuni.utils;

import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.flaviofaria.kenburnsview.Transition;
import com.flaviofaria.kenburnsview.TransitionGenerator;

import java.util.Random;

public class MyTransitionGenerator implements TransitionGenerator {


    float myfloat = (float)0.1;

    /**
     * Default value for the transition duration in milliseconds.
     */
    public static final int DEFAULT_TRANSITION_DURATION = 10000;

    /**
     * Minimum rect dimension factor, according to the maximum one.
     */
    private static final float MIN_RECT_FACTOR = 0.75f;

    /**
     * Random object used to generate arbitrary rects.
     */
    private final Random mRandom = new Random(System.currentTimeMillis());

    /**
     * The duration, in milliseconds, of each transition.
     */
    private long mTransitionDuration;

    /**
     * The {@link Interpolator} to be used to create transitions.
     */
    private Interpolator mTransitionInterpolator;

    /**
     * The last generated transition.
     */
    private Transition mLastGenTrans;

    /**
     * The bounds of the drawable when the last transition was generated.
     */
    private RectF mLastDrawableBounds;


    public MyTransitionGenerator() {
        this(DEFAULT_TRANSITION_DURATION, new AccelerateDecelerateInterpolator());
    }


    public MyTransitionGenerator(long transitionDuration, Interpolator transitionInterpolator) {
        setTransitionDuration(transitionDuration);
        setTransitionInterpolator(transitionInterpolator);
    }


    @Override
    public Transition generateNextTransition(RectF drawableBounds, RectF viewport) {
        boolean firstTransition = mLastGenTrans == null;
        boolean drawableBoundsChanged = true;
        boolean viewportRatioChanged = true;

        RectF srcRect = null;
        RectF dstRect = null;

        if (!firstTransition) {
            dstRect = mLastGenTrans.getDestinyRect();
            drawableBoundsChanged = !drawableBounds.equals(mLastDrawableBounds);
            viewportRatioChanged = !MyMath.haveSameAspectRatio(dstRect, viewport);
        }

        if (dstRect == null || drawableBoundsChanged || viewportRatioChanged) {
            srcRect = generateRandomRect(drawableBounds, viewport);
        } else {
            /* Sets the destiny rect of the last transition as the source one
             if the current drawable has the same dimensions as the one of
             the last transition. */
            srcRect = dstRect;
        }
        dstRect = generateRandomRect(drawableBounds, viewport);

        mLastGenTrans = new Transition(srcRect, dstRect, mTransitionDuration,
                mTransitionInterpolator);

        mLastDrawableBounds = new RectF(drawableBounds);

        return mLastGenTrans;
    }


    /**
     * Generates a random rect that can be fully contained within {@code drawableBounds} and
     * has the same aspect ratio of {@code viewportRect}. The dimensions of this random rect
     * won't be higher than the largest rect with the same aspect ratio of {@code viewportRect}
     * that {@code drawableBounds} can contain. They also won't be lower than the dimensions
     * of this upper rect limit weighted by {@code MIN_RECT_FACTOR}.
     *
     * @param drawableBounds the bounds of the drawable that will be zoomed and panned.
     * @param viewportRect   the bounds of the view that the drawable will be shown.
     * @return an arbitrary generated rect with the same aspect ratio of {@code viewportRect}
     * that will be contained within {@code drawableBounds}.
     */
    private RectF generateRandomRect(RectF drawableBounds, RectF viewportRect) {
        float drawableRatio = MyMath.getRectRatio(drawableBounds);
        float viewportRectRatio = MyMath.getRectRatio(viewportRect);
        RectF maxCrop;

        if (drawableRatio > viewportRectRatio) {
            float r = (drawableBounds.height() / viewportRect.height()) * viewportRect.width();
            float b = drawableBounds.height();
            maxCrop = new RectF(0, 0, r, b);
        } else {
            float r = drawableBounds.width();
            float b = (drawableBounds.width() / viewportRect.width()) * viewportRect.height();
            maxCrop = new RectF(0, 0, r, b);
        }

        float randomFloat = MyMath.truncate((float) (myfloat + 0.2), 2);

        myfloat = (float) (myfloat + 0.2);


        float factor = MIN_RECT_FACTOR + ((1 - MIN_RECT_FACTOR) * randomFloat);

        float width = factor * maxCrop.width();
        float height = factor * maxCrop.height();


        int widthDiff = (int) (drawableBounds.width() - width);
        int heightDiff = (int) (drawableBounds.height() - height);


        int left = widthDiff > 0 ? widthDiff/2 : 0;
        int top = heightDiff > 0 ? heightDiff/5 : 0;


        return new RectF(left, top, left + width, top + height);


    }


    /**
     * Sets the duration, in milliseconds, for each transition generated.
     *
     * @param transitionDuration the transition duration.
     */
    public void setTransitionDuration(long transitionDuration) {
        mTransitionDuration = transitionDuration;
    }


    /**
     * Sets the {@link Interpolator} for each transition generated.
     *
     * @param interpolator the transition interpolator.
     */
    public void setTransitionInterpolator(Interpolator interpolator) {
        mTransitionInterpolator = interpolator;
    }
}