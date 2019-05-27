package geekbrains.ru.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

// Фрагмент выбора города из списка
public class CitiesFragment2 extends Fragment {

    public static final String CURRENT_CITY = "CurrentCity";
    private Parsel currentParcel;
    private CityAdapter cityAdapter;

    public Parsel getCurrentParcel() {
        currentParcel = cityAdapter.getCurrentParsel();
        return currentParcel;
    }

    // При создании фрагмента, укажем его макет
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.m_fragment_list_rv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> data = getDataForCityAdapter(savedInstanceState);

        RecyclerView recyclerViewCity = view.findViewById(R.id.recViewCityM);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        cityAdapter = new CityAdapter(data, this, currentParcel);

        recyclerViewCity.setLayoutManager(layoutManager);
        recyclerViewCity.setAdapter(cityAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerViewCity, false);
    }

    private ArrayList<String> getDataForCityAdapter(@Nullable Bundle savedInstanceState) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < getResources().getTextArray(R.array.cities).length; i++) {
            data.add(String.valueOf(getResources().getTextArray(R.array.cities)[i]));
        }
        // Если это не повторное создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            currentParcel = (Parsel) savedInstanceState.getSerializable(CURRENT_CITY);
        } else
            currentParcel = new Parsel(0,
                    getResources().getTextArray(R.array.cities)[0].toString());
        return data;
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        getCurrentParcel();
        outState.putSerializable(CURRENT_CITY, getCurrentParcel());
        super.onSaveInstanceState(outState);
    }
}
