package se.miun.jasv2000.dt031g.dialer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatTextView;



public class DialpadButton extends ConstraintLayout {

    String title = "asd";

    public DialpadButton(@NonNull Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        inflate(context, R.layout.view_dialpad_button, this);
    }


    public DialpadButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        inflate(getContext(), R.layout.view_dialpad_button, this);
        @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DialpadButton);
        CharSequence title = a.getString(R.styleable.DialpadButton_title);
        CharSequence message = a.getString(R.styleable.DialpadButton_message);
        if (title != null)
            setTitle(title.toString());
        if (message != null)
            setMessage(message.toString());



        setOnClickListener(view -> animateClick());
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
        //SoundPlayer soundplayer = se.miun.jasv2000.dt031g.dialer.SoundPlayer.getInstance(getContext());
        SoundPlayer.playSound(this);


        animate().rotationBy(360).start();
    }

    public String getTitle(){
        return title;
    }
}
