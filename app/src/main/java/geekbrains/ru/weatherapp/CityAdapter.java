package geekbrains.ru.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private boolean isExistDataOfCity;  // Можно ли расположить рядом фрагмент с данными о погоде
    private ArrayList<String> data;
    private CitiesFragment2 citiesF;
    private Parsel currentParsel;

    CityAdapter(ArrayList<String> data, CitiesFragment2 citiesF, Parsel currentParcel) {
        this.citiesF = citiesF;
        this.currentParsel = currentParcel;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,
                viewGroup, false);

        // Определение, можно ли будет расположить рядом данные о погоде в другом фрагменте
        isExistDataOfCity = (citiesF.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE);

        // Если можно нарисовать рядом данные о погоде, то сделаем это
        if (isExistDataOfCity) {
            showDataOfCity(currentParsel);
        }
        return new CityViewHolder((TextView) view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, @SuppressLint("RecyclerView") final int i) {
        String text = data.get(i);
        cityViewHolder.textView.setText(text);

        cityViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentParsel = new Parsel(i, data.get(i));
                showDataOfCity(currentParsel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    Parsel getCurrentParsel() {
        return currentParsel;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        CityViewHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView.findViewById(R.id.simple_textview);
        }
    }

    // Показать данные о погоде. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    private void showDataOfCity(Parsel currentParcel) {

        if (isExistDataOfCity) {
            // Проверим, что фрагмент с данными о погоде существует в activity
            SecondFragment2 secondFragment2 = (SecondFragment2) Objects.requireNonNull(citiesF.getFragmentManager()).findFragmentById(R.id.m_container_port_2);

            // Если есть необходимость, то выведем данные о погоде
            if (secondFragment2 == null || secondFragment2.getParcel().getCityIndex()
                    != currentParcel.getCityIndex()) {
                // Создаем новый фрагмент с текущей позицией для вывода данных о погоде
                secondFragment2 = SecondFragment2.create(currentParcel);
                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = (Objects.requireNonNull(citiesF.getActivity())).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.m_container_port_2, secondFragment2);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("Some_Key");
                ft.commit();
            }
        } else {
            // Если нельзя вывести данные о погоде рядом, откроем вторую activity
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(citiesF.getActivity()), SecondActivity.class);
            // и передадим туда параметры
            intent.putExtra(SecondFragment.PARCEL, currentParcel);
            citiesF.startActivity(intent);
        }
    }
}
