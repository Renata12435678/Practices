# Отчёт по 5 практике
## Хасанова Рената БСБО-07-22

**Sensor**

Проект Lesson5 демонстрирует работу с датчиками:

•	Вывод списка всех доступных сенсоров устройства в ListView.

•	Использование SensorManager и SensorEventListener для получения данных.

•	В режиме реального времени логируются значения акселерометра (X, Y, Z) при изменении положения устройства.
 
![image](https://github.com/user-attachments/assets/6566d8b7-d521-4584-a652-5090bed2faa2)

**Accelerometer**

Модуль отображает в TextView текущие значения акселерометра:

•	Используется SensorManager для подключения к акселерометру.

•	Реализация SensorEventListener обеспечивает обновление координат в режиме реального времени.

•	При повороте или перемещении устройства значения X, Y, Z обновляются на экране.

![image](https://github.com/user-attachments/assets/9e5f45d5-05ab-42c7-ab3e-6bacca9c2fba)

**Camera**

Модуль Camera реализует работу с системной камерой:

•	Запуск камеры осуществляется через Intent(MediaStore.ACTION_IMAGE_CAPTURE).

•	Запрос разрешений CAMERA и WRITE_EXTERNAL_STORAGE с использованием ActivityCompat.

•	Создание уникального файла изображения с временной меткой в приватной директории приложения.

•	Использование FileProvider для безопасной передачи URI.

•	После съемки изображение отображается в ImageView.

•	Реализована обработка ошибок и информирование пользователя через Toast.
 
 ![image](https://github.com/user-attachments/assets/e7dc9adc-d388-4c50-becf-5d1308468ce4)

 ![image](https://github.com/user-attachments/assets/b4604912-b86d-4db0-a672-e8a5ee699422)

![image](https://github.com/user-attachments/assets/54090bbe-679c-4e45-b56d-27472a261e17)

 ![image](https://github.com/user-attachments/assets/03021f38-7d55-4704-8643-375c481181a1)
 
**Audio Record**

Модуль AudioRecord реализует простой диктофон:

•	Запись осуществляется через MediaRecorder, воспроизведение — через MediaPlayer.

•	Фиксированное имя файла: audiorecordtest.3gp, сохраняется в getExternalFilesDir().

•	Проверка и запрос разрешений: RECORD_AUDIO, WRITE_EXTERNAL_STORAGE.

•	Управление кнопками: кнопки записи и воспроизведения блокируются во время соответствующих действий.

•	Уведомления и обработка ошибок реализованы через Toast и логгирование.

•	Автоматическое освобождение ресурсов при завершении операций.
 
 ![image](https://github.com/user-attachments/assets/e76e76ac-eb7b-46ca-b279-646b0e342dce)

 ![image](https://github.com/user-attachments/assets/b98f2200-4746-46d1-92a7-fd9f5e704ab1)

![image](https://github.com/user-attachments/assets/5d63fba2-e8cf-44ab-8eb8-9004ea256cc6)
 
**Mirea Project: Hardware**

В проект MireaProject добавлена секция "Hardware" с пятью фрагментами, демонстрирующими работу с физическими компонентами устройства:
CameraFragment

•	Запуск системной камеры с использованием FileProvider.

•	Сохранение фотографий с уникальными именами (по времени).

•	Сохраняются в приватном каталоге приложения.

•	Возможность добавления заметок к фото.

•	Используется ActivityResultLauncher для запроса разрешений.

**CompassFragment**

•	Цифровой компас с анимированной стрелкой.

•	Определение сторон света (N, E, S, W).

•	Использование акселерометра и магнитометра.

•	Реализована плавная анимация поворота на основе вычисленного азимута.

**MicrophoneFragment**

•	Измерение громкости в реальном времени.

•	Отображение амплитуды через ProgressBar.

•	Запись звука в промежуточный файл.

•	Освобождение ресурсов при паузе или завершении работы.

**SensorsFragment**

•	Определение положения устройства (горизонтально / под углом).

•	Вычисление угла наклона в градусах.

•	Визуальная индикация состояния.

•	Применение альтернативных алгоритмов распознавания наклона.

**HardwareFragment**
•	Главный экран с кнопками перехода ко всем функциям.

•	Реализован через Navigation Component.

•	Является центральной точкой входа для доступа к аппаратным возможностям устройства.
 
 ![image](https://github.com/user-attachments/assets/390a9588-2978-4221-8e83-7bbeb0d24512)

 ![image](https://github.com/user-attachments/assets/89b90cd2-de8e-4e88-ba45-f1a95fbd8cdd)

 ![image](https://github.com/user-attachments/assets/c256f9be-61cd-4813-8e8f-5c66c2e5e7a2)

 ![image](https://github.com/user-attachments/assets/8b27c7af-d09d-4498-8386-0d9ca32e88b3)

![image](https://github.com/user-attachments/assets/9ade5a55-315a-402a-ae7f-fc0887af1354)

![image](https://github.com/user-attachments/assets/7ab21f89-2efd-45a6-a133-e9b7e99da414)
