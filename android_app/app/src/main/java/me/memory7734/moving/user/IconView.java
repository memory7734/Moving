package me.memory7734.moving.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.File;

import me.memory7734.moving.R;

/**
 * Created by JHF on 2016/9/12.
 */
public class IconView extends ImageView {
    private Bitmap mIcon;
    private Bitmap mIconSource;
    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        findIcon();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = 0;
        if(canvas.getWidth() > canvas.getHeight()) {
            r = canvas.getHeight();
        }
        else {
            r = canvas.getWidth();
        }
        mIcon = toRoundBitmap(mIconSource, r-40);

        Paint paint = new Paint();
        Paint paint1 = new Paint();

        RadialGradient gradient = new RadialGradient(r/2, r/2, r/2,
                new int[]{0xff5d5d5d,0xff5d5d5d,0x00555555},
                new float[]{0.f,0.8f,1.0f},
                Shader.TileMode.CLAMP);
        paint1.setShader(gradient);
        paint.setColor(0xffffffff);
        canvas.drawCircle(r/2, r/2,r/2,paint1);
        canvas.drawCircle(r/2, r/2, r/2-11, paint);
        canvas.drawBitmap(mIcon, 20, 20, null);
    }

    public Bitmap toRoundBitmap(Bitmap bitmap, int r) {

        Bitmap backgroundMap = bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(backgroundMap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rect, paint);
        return backgroundMap;
    }
    private void findIcon() {
        File iconFile = new File(this.getContext().getCacheDir(),"MyHeadIcon");
        if(iconFile.exists()) {
            mIconSource = BitmapFactory.decodeFile(iconFile.getAbsolutePath());
        }
        else {
            mIconSource = BitmapFactory.decodeResource(getResources(),
                    R.drawable.user_icon_default2);
        }
    }

    public void refresh() {
        findIcon();
        invalidate();
    }
}
