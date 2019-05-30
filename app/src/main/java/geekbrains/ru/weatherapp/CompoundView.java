package geekbrains.ru.weatherapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CompoundView extends RelativeLayout {

    TextView tvCompoundView;

    public CompoundView(Context context) {
        super(context);
        initViews(context);
    }

    public CompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public CompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_compound_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews() {
        tvCompoundView = this.findViewById(R.id.tvCompoundView);
    }

}
