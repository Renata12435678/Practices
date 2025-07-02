package ru.mirea.khasanovart.mireaproject.ui;

import ru.mirea.khasanovart.mireaproject.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;

public class BackgroundTaskFragment extends Fragment {
    private Button startButton;
    private TextView statusText;
    private OneTimeWorkRequest workRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_background_task, container, false);

        startButton = view.findViewById(R.id.start_button);
        statusText = view.findViewById(R.id.status_text);

        startButton.setOnClickListener(v -> startBackgroundWork());

        return view;
    }

    private void startBackgroundWork() {
        statusText.setText("Загрузка начата...");

        workRequest = new OneTimeWorkRequest.Builder(BackgroundWorker.class).build();

        WorkManager.getInstance(requireContext())
                .enqueue(workRequest);

        WorkManager.getInstance(requireContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo == null) return;

                        switch (workInfo.getState()) {
                            case ENQUEUED:
                                statusText.setText("Задача поставлена в очередь");
                                break;
                            case RUNNING:
                                statusText.setText("Задача выполняется");
                                break;
                            case SUCCEEDED:
                                statusText.setText("Задача успешно завершена");
                                break;
                            case FAILED:
                                statusText.setText("Задача завершилась с ошибкой");
                                break;
                            case CANCELLED:
                                statusText.setText("Задача отменена");
                                break;
                            default:
                                statusText.setText("Статус: " + workInfo.getState().name());
                        }
                    }
                });
    }
}
