package se.miun.jasv2000.dt031g.dialer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.DefaultLifecycleObserver;


public class DialpadButton extends ConstraintLayout {

    String title;
    private TextView phoneNumberTxt;

    public DialpadButton(@NonNull Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {

        inflate(context, R.layout.view_dialpad_button, this);
        setOnClickListener(view -> animateClick());
    }

    public DialpadButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs){

        inflate(getContext(), R.layout.view_dialpad_button, this);
        @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DialpadButton);
        String title = a.getString(R.styleable.DialpadButton_title);
        CharSequence message = a.getString(R.styleable.DialpadButton_message);
        if (title != null)
            setTitle(title.toString());
        if (message != null)
            setMessage(message.toString());


        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialActivity.addTitleToPhoneNumber(title);
                animateClick();
            }
        });
    }


    public void setTitle(String s) {

        // Return only one char
        s = s.substring(0, 1);

        title = s;
        // Set value for title
        TextView myTextView = findViewById(R.id.dialpad_title);
        myTextView.setText(s);
    }

    public void setMessage(String s) {
        if(s.length() > 4){
            s = s.substring(0,4);
        }
        TextView myTextView = findViewById(R.id.dialpad_message);
        myTextView.setText(s.toUpperCase());
    }

    private void animateClick(){

        SoundPlayer.getInstance(getContext()).playSound(this);

        animate().rotationBy(360);
    }

    public String getTitle(){
        return title;
    }


}
