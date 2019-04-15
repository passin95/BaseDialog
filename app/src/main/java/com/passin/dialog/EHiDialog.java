package com.passin.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * <pre>
 * @author : passin
 * @Date: 2019/1/7 16:38
 * </pre>
 * --------------------------------
 * 1. 适用于大部分情况下的 Dialog 使用情况。
 * 2. 不加判 null或类型转换try catch，是希望问题能在测试阶段就复现。
 * 3. 先 build 后才能操纵视图，原因是出于性能考虑。
 */
public class EHiDialog extends AppCompatDialog {

    private SparseArray<View> views;
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    private EHiDialog(@NonNull Context context, @LayoutRes int contentId, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(contentId);
        views = new SparseArray<>();
    }


    /**
     * Will set the text of a TextView.
     *
     * @param viewId The view id.
     * @param value The text to put in the text view.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId The view id.
     * @param strId The string resource id.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setText(@IdRes int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     *
     * @param viewId The view id.
     * @param imageResId The image resource id.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set background color of a view.
     *
     * @param viewId The view id.
     * @param color A color, not a resource id.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     *
     * @param viewId The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId The view id.
     * @param textColor The text color (not a resource id).
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Set the enabled state of this view.
     *
     * @param viewId The view id.
     * @param enable The enable
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setEnable(@IdRes int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId The view id.
     * @param drawable The image drawable.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public EHiDialog setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public EHiDialog setAlpha(@IdRes int viewId, @FloatRange(from = 0, to = 1) float value) {
        View view = getView(viewId);
        view.setAlpha(value);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE or INVISIBLE or GONE.
     *
     * @param viewId The view id.
     * @param visibility VISIBLE or INVISIBLE or GONE
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setVisible(@IdRes int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog linkify(@IdRes int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     */
    public EHiDialog setTypeface(@IdRes int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId The view id.
     * @param progress The progress.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId The view id.
     * @param progress The progress.
     * @param max The max value of a ProgressBar.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     *
     * @param viewId The view id.
     * @param max The max value of a ProgressBar.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setRating(@IdRes int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max The range of the RatingBar to 0...max.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the adapter of a view.
     *
     * @param viewId The view id.
     * @param adapter The adapter;
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setAdapter(@IdRes int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId The view id.
     * @param checked The checked status;
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setChecked(@IdRes int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param tag The tag;
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the listview or gridview's item selected click listener of the view
     *
     * @param viewId The view id.
     * @param listener The item selected click listener;
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setOnItemSelectedClickListener(@IdRes int viewId,
            AdapterView.OnItemSelectedListener listener) {
        AdapterView view = getView(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }

    /**
     * Sets the on checked change listener of the view.
     *
     * @param viewId The view id.
     * @param listener The checked change listener of compound button.
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setOnCheckedChangeListener(@IdRes int viewId,
            CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param key The key of tag;
     * @param tag The tag;
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * add onClick view id.
     *
     * @param viewId add the view id
     * @return The EHiDialog for chaining.
     */
    public EHiDialog addOnClickListener(@IdRes final int... viewId) {
        for (int id : viewId) {
            final View view = getView(id);
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickListener != null) {
                            onClickListener
                                    .onClick(EHiDialog.this, v);
                        }
                    }
                });
            }
        }
        return this;
    }

    /**
     * Setting Click Listener Separately
     *
     * @param viewId add the view id
     * @param listener OnClickListener
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        final View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * add onLongClick view id.
     *
     * @param viewId add the view id
     * @return The EHiDialog for chaining.
     */
    public EHiDialog addOnLongClickListener(@IdRes final int... viewId) {
        for (int id : viewId) {
            final View view = getView(id);
            if (view != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (onLongClickListener != null) {
                            return onLongClickListener.onLongClick(EHiDialog.this, v);
                        }
                        return false;
                    }
                });
            }
        }
        return this;
    }

    /**
     * Setting Click Listener Separately
     *
     * @param viewId add the view id
     * @param listener OnLongClickListener
     * @return The EHiDialog for chaining.
     */
    public EHiDialog setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        final View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


    /**
     * from findViewById get View
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (null == view) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public static Builder newBuilder(Context context, @StyleRes int themeId) {
        return new Builder(context, themeId);
    }

    public static class Builder {

        private Context context;
        private int themeId;
        private int contentId;
        private int animationId;
        // 是否可以取消，判断优先级比 cancelOnTouchOutside 高
        private boolean cancelable = true;
        private boolean cancelOnTouchOutside;
        private int gravity = Gravity.CENTER;
        private int width = ViewGroup.LayoutParams.MATCH_PARENT;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;

        private DialogInterface.OnCancelListener onCancelListener;
        private DialogInterface.OnDismissListener onDismissListener;
        private DialogInterface.OnKeyListener onKeyListener;
        private DialogInterface.OnShowListener onShowListener;


        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, @StyleRes int themeId) {
            this.context = context;
            this.themeId = themeId;
        }

        public EHiDialog.Builder themeId(@StyleRes int themeId) {
            this.themeId = themeId;
            return this;
        }

        public EHiDialog.Builder contentId(@LayoutRes int contentId) {
            this.contentId = contentId;
            return this;
        }

        /**
         * @see Gravity
         */
        public EHiDialog.Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * 设置宽度全屏显示
         */
        public EHiDialog.Builder fullWidth() {
            width = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置宽度全屏显示
         */
        public EHiDialog.Builder fullHeight() {
            height = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置全屏显示
         */
        public EHiDialog.Builder fullScreen() {
            width = ViewGroup.LayoutParams.MATCH_PARENT;
            height = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置屏幕宽占比
         */
        public EHiDialog.Builder screenWidthPercent(@FloatRange(from = 0, to = 1.0) float percent) {
            width = (int) (context.getResources().getDisplayMetrics().widthPixels * percent);
            return this;
        }

        /**
         * 设置屏幕高占比
         */
        public EHiDialog.Builder screenHeightPercent(@FloatRange(from = 0, to = 1.0) float percent) {
            height = (int) (context.getResources().getDisplayMetrics().heightPixels * percent);
            return this;
        }

        /**
         * 设置宽高
         */
        public EHiDialog.Builder setWidthAndHeight(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        /**
         * 设置动画
         */
        public EHiDialog.Builder animationId(@StyleRes int animtionId) {
            this.animationId = animtionId;
            return this;
        }

        /**
         * 设置点击外部是否可以取消,优先级高于 setCancelOnTouchOutside.
         */
        public EHiDialog.Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 设置点击外部是否可以取消
         */
        public EHiDialog.Builder cancelOnTouchOutside(boolean cancelable) {
            this.cancelOnTouchOutside = cancelable;
            return this;
        }

        public EHiDialog.Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public EHiDialog.Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public EHiDialog.Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.onKeyListener = onKeyListener;
            return this;
        }

        public EHiDialog.Builder setOnShowListener(OnShowListener onShowListener) {
            this.onShowListener = onShowListener;
            return this;
        }

        /**
         * 出于性能考虑，不在 Builder 时候利用容器储存具体的视图操作，
         * 调用 build() 之后才能操作视图。
         */
        public EHiDialog build() {
            if (contentId == 0) {
                throw new IllegalArgumentException("please set contentId");
            }
            final EHiDialog dialog = new EHiDialog(context, contentId, themeId);
            dialog.setCancelable(cancelable);
            // cancelable 优先级比 setCanceledOnTouchOutside 高
            if (cancelable) {
                dialog.setCanceledOnTouchOutside(cancelOnTouchOutside);
            }
            dialog.setOnCancelListener(onCancelListener);
            dialog.setOnDismissListener(onDismissListener);
            dialog.setOnKeyListener(onKeyListener);
            dialog.setOnShowListener(onShowListener);

            Window window = dialog.getWindow();
            window.setWindowAnimations(animationId);
            window.setGravity(gravity);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = width;
            lp.height = height;
            window.setAttributes(lp);
            return dialog;
        }
    }

    public EHiDialog showAndGet() {
        super.show();
        return this;
    }

    public EHiDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public EHiDialog setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
        return this;
    }

    public interface OnClickListener {

        void onClick(EHiDialog dialog, View view);
    }

    public interface OnLongClickListener {

        boolean onLongClick(EHiDialog dialog, View view);
    }
}
