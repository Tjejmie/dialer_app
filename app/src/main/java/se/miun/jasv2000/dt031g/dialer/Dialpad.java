package se.miun.jasv2000.dt031g.dialer;


import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Dialpad extends ConstraintLayout {

    public Dialpad(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_dialpad_button, this);
    }


    public Dialpad(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        inflate(getContext(), R.layout.dialpad, this);
    }
}
