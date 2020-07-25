package com.gtxreme.customviewpractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class MatrixEffect extends View {
    private int width=1000;
    private int height=100;
    private Canvas canvas=null;
    private Bitmap canvasBitmap;
    private int fontSize=15;
    private int columnSize = width/fontSize;
   // private int parentWidth;//Stores the parent width ..pointless since it's match parent
    private String text = "MATRIXRAIN";
    private char[] textChar = text.toCharArray();
    private int textLength = textChar.length;
    private Random rand = new Random();
    private int[]  textPosition;
    private Paint paint = new Paint();

    public MatrixEffect(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       // Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        canvas.drawBitmap(canvasBitmap,0,0,paint); //draw the bitmap to canvas

        canvasDraw(); // call the draw command
        //Redraw the canvas
        invalidate();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width= w;
        height = h;
        super.onSizeChanged(w, h, oldw, oldh);
        //create a Bitmap
        canvasBitmap =  Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitmap);

        //Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(255); //set the alpha
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, width, height, paint);

        columnSize = width/fontSize;
        textPosition = new int[columnSize+1];
        for(int x = 0; x < columnSize; x++)
            textPosition[x] = 1;
    }

    public void canvasDraw()
    {
        //set the paint for the canvas
        //Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(5);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, width, height, paint);//draw rect to clear the canvas

        drawText(); // draw the canvas

    }
    void drawText() {
        //Set up the paint
       // Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setTextSize(15);
        for (int i = 0; i < textPosition.length; i++) {

            canvas.drawText("" + textChar[rand.nextInt(textLength)], i *fontSize, textPosition[i] *fontSize, paint);
            if (textPosition[i] * fontSize > height && Math.random() > 0.975)
                textPosition[i] = 0;   // change text position to zero when 0 when text is at the bottom
            textPosition[i]++; //increment the position array
        }
    }


}
