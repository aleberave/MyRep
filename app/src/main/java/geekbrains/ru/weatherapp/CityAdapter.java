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

    CityAdapter(String[] data, FragmentActivity activity) {
        this.activity = activity;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new String[1];
            data[0] = "wow";
        }
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
        cityViewHolder.textView.setText(data[i]);

        cityViewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //в какую то переменную запоминаете позицию...
                Toast.makeText(v.getContext(), i, Toast.LENGTH_SHORT).show();
                return false;
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
