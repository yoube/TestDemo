package com.wg.myviews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.wg.myviews.R;

/**
 * Ô²ÐÎÍ·Ïñ
 * @author EXP
 *
 */
public class CirImageView extends View{

	private Bitmap bitmap;
	private Context mContext;
	private int width;
	private Paint paint;
	
	public CirImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}
	private void initView() {
		
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.title);
		width = 200;
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(width,width);
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawBitmap(toRoundBitmap(bitmap),0,0, paint);
		
		
	}
	public Bitmap toRoundBitmap(Bitmap b){
		int w = b.getWidth();
		int h = b.getHeight();
		
		
		Bitmap btmap = Bitmap.createBitmap(width,width, Config.ARGB_8888);
		
		Canvas canvas = new Canvas(btmap);
		
		
		Paint p = new Paint();
		p.setAntiAlias(true);
		RectF rect = new RectF(0,0,width,width);  
        canvas.drawRoundRect(rect,width/2,width/2,p);  
        
        p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        canvas.drawBitmap(b,null,rect,p);  
		
		return btmap;
	}

}
