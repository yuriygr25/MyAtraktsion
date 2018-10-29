package com.example.yura.myatraktsion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class TestActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new GraphicsView(this));
    }

    static public class GraphicsView extends View {
        private RotateAnimation rotate;

        public GraphicsView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // creates the animation the first time
            if (rotate == null) {
                createAnimation(canvas);
            }

            // Настраиваем окружность
            Paint cPaint = new Paint();
            cPaint.setColor(Color.BLUE); // цвет круга
            cPaint.setStyle(Paint.Style.STROKE); // делаем окантовку вместо круга
            cPaint.setStrokeWidth(2.0f); // толщина окантовки
            cPaint.setAntiAlias(true); // сглаживание

            // Работаем с текстом
            Paint tPaint = new Paint();
            tPaint.setTextSize(46); // размер текста
            tPaint.setAntiAlias(true); // сглаживание
            tPaint.setColor(Color.BLUE); // цвет текста

            Path circle = new Path();

            int centerX = canvas.getWidth() / 2;
            int centerY = canvas.getHeight() / 2;
            circle.addCircle(centerX, centerY, 150, Path.Direction.CW);

            canvas.drawPath(circle, cPaint);
            canvas.drawTextOnPath("Коты и кошки всех стран, соединяйтесь! * ",
                    circle, 0, 32, tPaint);
        }

        private void createAnimation(Canvas canvas) {
            rotate = new RotateAnimation(0, 180, canvas.getWidth() / 2, canvas.getHeight() / 2);
            rotate.setRepeatMode(Animation.REVERSE);
            rotate.setRepeatCount(Animation.INFINITE);
            rotate.setDuration(10000L);
            rotate.setInterpolator(new AccelerateDecelerateInterpolator());

            startAnimation(rotate);
        }
    }
}