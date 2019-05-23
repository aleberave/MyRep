package geekbrains.ru.weatherapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private String[] data;
    private Activity activity;
    public int position;

    CityAdapter(String[] data, FragmentActivity activity) {
        this.activity = activity;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new String[1];
        }
    }

    public int getPosition() {
        return position;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,
                viewGroup, false);
        return new CityViewHolder((TextView) view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, final int i) {
        String text = "Элемент номер " + data[i];
        cityViewHolder.textView.setText(text);
//        cityViewHolder.textView.setText(data[i]);

        final int position = i;
        cityViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "position" + i, Toast.LENGTH_SHORT).show();
            }
        });

        activity.registerForContextMenu(cityViewHolder.textView);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        CityViewHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView.findViewById(R.id.simple_textview);
        }
    }
}
