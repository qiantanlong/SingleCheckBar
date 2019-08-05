package hongzhen.com.singlecheckbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
     * 开发者：hongzhen
     * 创建时间： 2019/8/5 10:16
     * 公司名称：
     * 类名：SingleCheckView.java
     * 描述：单选条形选择器
     */
public class SingleCheckView extends LinearLayout {
    /**
     * 左侧按钮的文本
     */
    private String tvLeftText;
    /**
     * 右侧按钮的文本
     */
    private String tvRightText;
    /**
     * 按钮文本的大小
     */
    private int textSize;
    /**
     * 左侧按钮
     */
    private TextView tvLeft;
    /**
     * 右侧按钮
     */
    private TextView tvRight;

    /**
     * 左侧默认状态下的背景
     *
     * @param context
     */
    private Drawable drawableLeftNormal;
    /**
     * 左侧选择后的背景
     */
    private Drawable drawableLeftPress;

    /**
     * 右侧默认状态下的背景
     */
    private Drawable drawableRightNormal;

    /**
     * 右侧选择后的背景
     */
    private Drawable drawableRightPress;

    /**
     * 默认状态下文本颜色
     * @param context
     */
    private int tvColorNormal;

    /**
     * 选择后的文本颜色
     * @param context
     *
     */
    private int tvColorPress;
    /**
     * 左侧按钮标识
     */
    public static final int ITEM_LEFT=1001;
    /**
     * 右侧按钮标识
     */
    public static final int ITEM_RIGHT=1002;
    /**
     * 条目点击监听
     */
    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public SingleCheckView(Context context) {
        this(context, null);
    }

    public SingleCheckView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //属性读取
        initAttrs(context,attrs,defStyleAttr);
        //初始化控件
        initView(context);
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineBarCheckView, defStyleAttr, 0);
        tvLeftText = typedArray.getString(R.styleable.LineBarCheckView_text_left_line_bar);
        tvRightText = typedArray.getString(R.styleable.LineBarCheckView_text_right_line_bar);
        textSize = (int) typedArray.getDimension(R.styleable.LineBarCheckView_text_size_line_bar, 7);
        drawableLeftNormal = typedArray.getDrawable(R.styleable.LineBarCheckView_text_bg_color_left_normal);
        drawableLeftPress=typedArray.getDrawable(R.styleable.LineBarCheckView_text_bg_color_left_press);
        drawableRightNormal=typedArray.getDrawable(R.styleable.LineBarCheckView_text_bg_color_right_normal);
        drawableRightPress=typedArray.getDrawable(R.styleable.LineBarCheckView_text_bg_color_right_press);
        tvColorNormal=typedArray.getColor(R.styleable.LineBarCheckView_text_color_normal,getResources().getColor(R.color.col_white));
        tvColorPress=typedArray.getColor(R.styleable.LineBarCheckView_text_color_press,getResources().getColor(R.color.main_color));
        if (drawableLeftNormal==null){
            drawableLeftNormal=getResources().getDrawable(R.drawable.blue_rect_corner_left_normal);
        }
        if (drawableLeftPress==null){
            drawableLeftPress=getResources().getDrawable(R.drawable.blue_rect_corner_left_press);
        }
        if (drawableRightNormal==null){
            drawableRightNormal=getResources().getDrawable(R.drawable.blue_rect_corner_right_normal);
        }
        if (drawableRightPress==null){
            drawableRightPress=getResources().getDrawable(R.drawable.blue_rect_corner_right_press);
        }
    }

    /**
     * 初始化view控件
     *
     * @param context
     */
    private void initView(Context context) {
        View inflate = inflate(context, R.layout.view_line_bar_check, null);
        tvLeft = inflate.findViewById(R.id.tv_left);
        tvRight = inflate.findViewById(R.id.tv_right);
        tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if (!TextUtils.isEmpty(tvLeftText)) {
            tvLeft.setText(tvLeftText);
        }
        if (!TextUtils.isEmpty(tvRightText)) {
            tvRight.setText(tvRightText);
        }
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(ITEM_LEFT);
                }
                setchecked(v.getId());
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(ITEM_RIGHT);
                }
                setchecked(v.getId());
            }
        });
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(inflate, layoutParams);
    }

    /**
     * 根据点击事件更新按钮状态
     *
     * @param id
     */
    private void setchecked(int id) {
        if (id == R.id.tv_left) {
            tvLeft.setBackground(getResources().getDrawable(R.drawable.blue_rect_corner_left_press));
            tvLeft.setTextColor(getResources().getColor(R.color.col_white));
            tvRight.setBackground(getResources().getDrawable(R.drawable.blue_rect_corner_right_normal));
            tvRight.setTextColor(getResources().getColor(R.color.main_color));
        } else {
            tvLeft.setBackground(getResources().getDrawable(R.drawable.blue_rect_corner_left_normal));
            tvLeft.setTextColor(getResources().getColor(R.color.main_color));
            tvRight.setBackground(getResources().getDrawable(R.drawable.blue_rect_corner_right_press));
            tvRight.setTextColor(getResources().getColor(R.color.col_white));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 条目点击监听
     */
    public interface onItemClickListener{
        void onItemClick(int item);
    }
}
