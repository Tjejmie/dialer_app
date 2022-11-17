package se.miun.jasv2000.dt031g.dialer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatTextView;



public class DialpadButton extends ConstraintLayout {

    public DialpadButton(@NonNull Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        View.inflate(getContext(), R.layout.view_dialpad_button, this);
        @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(R.styleable.DialpadButton);
        CharSequence title = a.getString(R.styleable.DialpadButton_title);
        CharSequence message = a.getString(R.styleable.DialpadButton_message);
        if (title != null)
            setTitle(title.toString());
        if (title != null)
            setMessage(message.toString());
    }


    public DialpadButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View.inflate(getContext(), R.layout.view_dialpad_button, this);
        @SuppressLint("Recycle") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DialpadButton);
        CharSequence title = a.getString(R.styleable.DialpadButton_title);
        CharSequence message = a.getString(R.styleable.DialpadButton_message);
        if (title != null)
            setTitle(title.toString());
        if (title != null)
            setMessage(message.toString());
    }

    public void setTitle(String s) {
        // Char options for title
        String titleOptions = "0123456789*#";
        // Return only one char
        s = s.substring(0,1);
        // Check if char exist in titleOption, if not 1 will be default value
        int result = titleOptions.indexOf(s);
        if (result == -1){
            s = "1";
        }
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
}
