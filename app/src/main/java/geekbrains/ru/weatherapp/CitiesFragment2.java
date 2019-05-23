package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Фрагмент выбора города из списка
public class CitiesFragment2 extends Fragment {

    private boolean isExistDataOfCity;  // Можно ли расположить рядом фрагмент с данными о погоде
    private static final String CURRENT_CITY = "CurrentCity";
    private Parsel currentParcel;


    // При создании фрагмента, укажем его макет
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_rv, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] data = {"Москва", "Самара", "Саратов"};
        RecyclerView recyclerViewCity = view.findViewById(R.id.recViewCityM);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        CityAdapter cityAdapter = new CityAdapter(data, getActivity());

        recyclerViewCity.setLayoutManager(layoutManager);
        recyclerViewCity.setAdapter(cityAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerViewCity, false);
    }

    // Активити создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        // Определение, можно ли будет расположить рядом данные о погоде в другом фрагменте
        isExistDataOfCity = (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE);

        // Если это не повторное создание, то восстановим текущую позицию
        if (savedInstanceState != null)
            currentParcel = (Parsel) savedInstanceState.getSerializable(CURRENT_CITY);
        else
            currentParcel = new Parsel(0,
                    getResources().getTextArray(R.array.cities)[0].toString());

//        isExistDataOfCity = (getActivity().getSupportFragmentManager().findFragmentById(R.id.container_port_2) != null);

        // Если можно нарисовать рядом данные о погоде, то сделаем это
        if (isExistDataOfCity) {
            showDataOfCity(currentParcel);
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURRENT_CITY, currentParcel);

    }

    // Показать данные о погоде. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    private void showDataOfCity(Parsel currentParcel) {

        if (isExistDataOfCity) {
            // Выделим текущий элемент списка
//            listView.setItemChecked(currentParcel.getCityIndex(), true);

            // Проверим, что фрагмент с данными о погоде существует в activity
            SecondFragment secondFragment = (SecondFragment)
                    getFragmentManager().findFragmentById(R.id.m_container_port_2);

            // Если есть необходимость, то выведем данные о погоде
            if (secondFragment == null || secondFragment.getParcel().getCityIndex()
                    != currentParcel.getCityIndex()) {
                // Создаем новый фрагмент с текущей позицией для вывода данных о погоде
                secondFragment = SecondFragment.create(currentParcel);
                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.m_container_port_2, secondFragment);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("Some_Key");
                ft.commit();
            }
        } else {
            // Если нельзя вывести данные о погоде рядом, откроем вторую activity
            Intent intent = new Intent();
            intent.setClass(getActivity(), SecondActivity.class);
            // и передадим туда параметры
            intent.putExtra(SecondFragment.PARCEL, currentParcel);
            startActivity(intent);
        }
    }
}
