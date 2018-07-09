package carvellwakeman.shoppingapp.utils;


import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import carvellwakeman.shoppingapp.R;


/*
 * Java code adapted from https://github.com/kitek/android-rv-swipe-delete/
 */
public abstract class SwipeDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private Drawable deleteIcon;
    private int intrinsicWidth;
    private int intrinsicHeight;
    private ColorDrawable background;
    private int backgroundColor;
    private Paint clearPaint;

    public SwipeDeleteCallback(Context context, int dir) {
        super(0, dir);

        this.deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete_white_36);
        this.intrinsicWidth = deleteIcon.getIntrinsicWidth();
        this.intrinsicHeight = deleteIcon.getIntrinsicHeight();
        this.background = new ColorDrawable();
        this.backgroundColor = Color.parseColor("#f44336");
        this.clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getBottom() - itemView.getTop();

        boolean isCanceled = dX == 0f && !isCurrentlyActive;

        if (isCanceled) {
            clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        } else {
            // Draw the red delete background
            background.setColor(backgroundColor);
            background.setBounds(
                    itemView.getRight() + (int)dX,
                    itemView.getTop(),
                    itemView.getRight(),
                    itemView.getBottom()
            );
            background.draw(c);

            // Calculate position of delete icon
            int iconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
            int iconMargin = (itemHeight - intrinsicHeight) / 2;
            int iconLeft = itemView.getRight() - iconMargin - intrinsicWidth;
            int iconRight = itemView.getRight() - iconMargin;
            int iconBottom = iconTop + intrinsicHeight;

            // Draw the delete icon
            deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            deleteIcon.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    private void clearCanvas(Canvas c, float left, float top, float right, float bottom) {
        c.drawRect(left, top, right, bottom, clearPaint);
    }

}
