package goo.TeaTimer.Animation;

import goo.TeaTimer.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.util.Log;

class Teapot implements TimerAnimation.TimerDrawing
{	
	private Bitmap mCupBitmap;

	private int mWidth = 0;
	private int mHeight = 0;
	
	private Paint mProgressPaint = null;

	private Bitmap mBitmap = null;

	public Teapot(Resources resources)
	{
		mProgressPaint = new Paint();
		mProgressPaint.setColor(Color.GRAY);
		mProgressPaint.setAlpha(135);
		mProgressPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		
		mCupBitmap = BitmapFactory.decodeResource(resources, R.drawable.teapot);	
		mHeight = mCupBitmap.getHeight();
		mWidth = mCupBitmap.getWidth();

		mBitmap = Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888);
	}
	
	/**
	 * Updates the image to be in sync with the current time
	 * @param time in milliseconds
	 * @param max the original time set in milliseconds
	 */
	public Bitmap updateImage(int time,int max)
	{	
		mBitmap.eraseColor(Color.TRANSPARENT);
		
		Canvas canvas = new Canvas(mBitmap);
		canvas.drawBitmap(mCupBitmap, 0, 0,null);
		
		float p = (max != 0) ? (time/(float)max) : 0;
		
		if(p == 0) p = 1;
		
		Log.v("Ba","m: " + max + " t " + time + " p " + p);
		
		RectF fill = new RectF(0,mHeight*(p),mWidth,mHeight);
		canvas.drawRect(fill,mProgressPaint);
		
		return mBitmap;	
	}
}