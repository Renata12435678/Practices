package ru.mirea.khasanovart.mireaproject.ui.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import ru.mirea.khasanovart.mireaproject.R;

public class WebViewFragment extends Fragment {

    private WebView webView;
    private static final String DEFAULT_URL = "https://www.mirea.ru/";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_webview, container, false);

        webView = root.findViewById(R.id.webView);
        setupWebView();

        return root;
    }

    private void setupWebView() {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(DEFAULT_URL);
    }

    @Override
    public void onDestroyView() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroyView();
    }
}