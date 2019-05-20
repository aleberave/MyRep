package geekbrains.ru.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {
    private Context context;
    private List<DataClass> dataSource;

    public RVAdapter(List<DataClass> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.element_layout, viewGroup,
                false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder rvViewHolder, int i) {
        final DataClass item = dataSource.get(i);
        rvViewHolder.imageView.setImageResource(item.getIdentifier());
        rvViewHolder.textView.setText(item.getCityName());
        rvViewHolder.checkBox.setChecked(false);

        final int position = i;
        rvViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                (Boolean) data.get(position).isChecked = isChecked;
                //...
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    class RVViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;

        RVViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(@NonNull View itemView) {
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
