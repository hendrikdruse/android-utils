package de.hddesign.androidutils.androidutils.utils;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

public class LableSliderSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

    public interface SeekbarCallback {
        void onProgressChanged(long id, int value, boolean fromUser);

        void onStopTrackingTouch(long id, int value);
    }

    private Context context;
    private TextView label;
    private int resourceId;
    private long id;
    private int offset;

    private SeekbarCallback seekbarCallback;

    public void setSeekbarCallback(SeekbarCallback seekbarCallback) {
        this.seekbarCallback = seekbarCallback;
    }

    public LableSliderSeekBarChangeListener(Context context, TextView label, int resourceId, int initValue, long id) {
        this(context, label, resourceId, initValue, id, 0);
    }

    public LableSliderSeekBarChangeListener(Context context, TextView label, int resourceId, int initValue, long id, int offset) {
        this.context = context;
        this.label = label;
        this.resourceId = resourceId;
        this.id = id;
        this.offset = offset;

        label.setText(String.format(context.getString(resourceId), initValue + offset));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (label != null)
            label.setText(String.format(context.getString(resourceId), progress + offset));

        if (seekbarCallback != null)
            seekbarCallback.onProgressChanged(id, progress + offset, fromUser);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (label != null)
            label.setText(String.format(context.getString(resourceId), (seekBar.getProgress() + offset)));
        if (seekbarCallback != null)
            seekbarCallback.onStopTrackingTouch(id, (seekBar.getProgress() + offset));
    }
}
