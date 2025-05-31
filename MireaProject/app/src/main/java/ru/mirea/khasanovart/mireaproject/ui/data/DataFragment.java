package ru.mirea.khasanovart.mireaproject.ui.data;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.khasanovart.mireaproject.R;

public class DataFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data, container, false);

        TextView industryInfoText = root.findViewById(R.id.industryInfoText);
        industryInfoText.setText(Html.fromHtml(getString(R.string.industry_info), Html.FROM_HTML_MODE_COMPACT));

        return root;
    }
}