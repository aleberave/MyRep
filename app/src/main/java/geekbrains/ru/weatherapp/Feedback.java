package geekbrains.ru.weatherapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Feedback extends Fragment {

    private String textKey = "text_key";
    private EditText etEnterYourMessage;
    private EditText etEnterYourName;
    private EditText etYourNameMessage;
    private Button btnSend;
    private Button btnGet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSend = view.findViewById(R.id.btnSend);
        btnGet = view.findViewById(R.id.btnGet);
        etEnterYourMessage = view.findViewById(R.id.etEnterYourMessage);
        etEnterYourName = view.findViewById(R.id.etEnterYourName);
        etYourNameMessage = view.findViewById(R.id.etYourNameMessage);

        etEnterYourMessage.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем файл настроек по умолчанию
                SharedPreferences sharedPref =
                        Objects.requireNonNull(getActivity()).getPreferences(MODE_PRIVATE);
                // Сохранить настройки
                savePreferences(sharedPref);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем файл настроек по умолчанию
                SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getPreferences(MODE_PRIVATE);
                // Загрузить настройки
                loadPreferences(sharedPref);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // Сохраняем настройки
    private void savePreferences(SharedPreferences sharedPref) {
        textKey = etEnterYourName.getText().toString();
        String values = etEnterYourMessage.getText().toString();
        // Для сохранения настроек надо воспользоваться классом Editor
        SharedPreferences.Editor editor = sharedPref.edit();
        // Установим в Editor значения
        editor.putString(textKey, values);
        // И сохраним файл настроек
        editor.apply();
    }

    private void loadPreferences(SharedPreferences sharedPref) {
        // Получаем настройки прямо из SharedPreferences
        String value = sharedPref.getString(textKey, "value");
        etYourNameMessage.setText(value);
    }

}