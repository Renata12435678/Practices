# Отчёт по 4 практике
## Хасанова Рената БСБО-07-22

**Music Player**

Создан музыкальный плеер с использованием View Binding:

•	View Binding активирован через buildFeatures { viewBinding = true } в build.gradle.

•	В MainActivity:

o	Разметка подключена через ActivityMainBinding.inflate().

o	Установка интерфейса через setContentView(binding.getRoot()).

o	Получение доступа к компонентам UI напрямую через binding.

•	Реализованы обработчики событий для кнопок управления.

•	Поддерживается адаптивная верстка: отдельные layout-файлы для портретной и альбомной ориентаций.

 ![image](https://github.com/user-attachments/assets/0a8981eb-ee34-4a13-87b1-fc2ae360049c)

![image](https://github.com/user-attachments/assets/b48b0130-703d-471f-a917-8e6ce92d80ac)

 ![image](https://github.com/user-attachments/assets/8d69d628-3dfa-42c4-bb87-8b5a8a7a66d0)
 
**Thread**

Модуль демонстрирует работу с главным и фоновыми потоками:

•	View Binding подключен.

•	Компоненты:

o	EditText для ввода количества пар и учебных дней.

o	TextView для вывода результата и информации о потоке.

o	Button для запуска вычислений.

•	Основной поток:

o	Получение и переименование потока.

o	Вывод группы и стека вызовов в лог.

•	Фоновый поток:

o	Запускается вручную через new Thread(...).

o	Выполняет расчеты и имитирует длительную задачу (через Thread.sleep()).

o	Результаты безопасно передаются в UI через runOnUiThread().
 
![image](https://github.com/user-attachments/assets/20529dde-1412-47ef-9ed4-4daee3b24780)

![image](https://github.com/user-attachments/assets/405de3e2-1ac2-45d2-a738-4f042f5f7143)

**Data Thread**

Пример сравнения трёх способов обновления UI из фонового потока:

•	runOnUiThread()

•	post()

•	postDelayed()

После запуска фонового потока, все три метода вызываются с пояснениями и отображаются в TextView.

Модуль помогает понять различия между методами по приоритетности и времени выполнения задач в UI-потоке.

 ![image](https://github.com/user-attachments/assets/82dabb93-793f-445c-949a-dd056f932f84)

 ![image](https://github.com/user-attachments/assets/b2743748-4ec7-4a89-b29b-e40eb42e77bf)

**Looper**

Модуль демонстрирует взаимодействие между потоками через Looper и Handler:

•	Класс MyLooper:

o	Создает собственный Looper и Handler.

o	Обрабатывает сообщения с задержкой (время и профессия).

o	Отправляет результат обратно в основной поток.

•	В MainActivity:

o	Инициализация и отправка сообщений в MyLooper.

o	Главный Handler принимает и отображает результат.

 ![image](https://github.com/user-attachments/assets/66548ec4-508d-4ee3-bba6-d69a0da02414)

 ![image](https://github.com/user-attachments/assets/12bb1679-4166-4222-8fae-67ebbd51aa26)

**Crypto Loader**

Асинхронная дешифровка текста с использованием AsyncTaskLoader:

•	В MainActivity:

o	Шифрование текста.

o	Запуск Loader с передачей шифртекста и ключа.

•	Класс MyLoader:

o	Дешифрует данные в фоне.

o	Использует CryptoUtils для AES-операций.

•	CryptoUtils:

o	Генерация ключа (generateKey()).

o	Шифрование и дешифрование (encryptMsg(), decryptMsg()).

•	Все операции происходят вне UI-потока для обеспечения отзывчивости.
 
 ![image](https://github.com/user-attachments/assets/7cae7185-77d3-4bbd-afbd-fcd2b14a98da)

![image](https://github.com/user-attachments/assets/5f58cab7-48fd-4441-9345-4e7c6b4bfd90)

![image](https://github.com/user-attachments/assets/c820ddcb-841f-4df3-b42b-e94b366256d6)

**Service App**

Пример работы с Foreground Service:

•	Сервис PlayerService:

o	Использует MediaPlayer для воспроизведения музыки.

o	Запускается как foreground с уведомлением (название трека).

•	В MainActivity:

o	Кнопки управления: запуск и остановка сервиса.

o	Запуск через startForegroundService().

•	Добавлены необходимые разрешения и mp3-файл в ресурсы.

 ![image](https://github.com/user-attachments/assets/14b7e2d8-b325-4945-bbec-c7f187d1fda7)

 ![image](https://github.com/user-attachments/assets/693dbc06-623b-458c-9545-02d1041138c0)

**Work Manager**

Модуль демонстрирует работу с WorkManager:

•	Класс UploadWorker:

o	Выполняет 10-секундную фоновую задачу.

o	Принимает и возвращает данные через Data.

•	В MainActivity:

o	Создание ограничений (например, подключение к сети).

o	Создание и запуск WorkRequest.

o	Наблюдение за статусом через LiveData.

•	Задача выполняется автоматически при соблюдении условий, UI обновляется динамически.
 
 ![image](https://github.com/user-attachments/assets/ecdacf68-9726-4baa-9428-83200d1fffe0)

 ![image](https://github.com/user-attachments/assets/52216c87-0e4a-4d68-8895-3e273bf7fd3b)

![image](https://github.com/user-attachments/assets/31f514c1-f085-48df-bd47-b0c0661f737f)

**Mirea Project: WorkManager**

Интеграция WorkManager в основной проект:

•	Класс MyWorker:

o	Выполняет имитацию загрузки (5 секунд).

o	Возвращает результат: "Данные отправлены".

•	Фрагмент WorkFragment:

o	Кнопка запуска фоновой задачи.

o	TextView для отображения статуса и результата.

•	Логика:

o	Создание OneTimeWorkRequest с ограничениями (только при наличии интернета).

o	Наблюдение за статусом через LiveData.

o	Гибкая и отказоустойчивая реализация фоновых задач в соответствии с современными рекомендациями Android.

![image](https://github.com/user-attachments/assets/85bb3ddd-e225-42a6-8150-5df5ff86168b)

![image](https://github.com/user-attachments/assets/9f65d07b-9035-4ca5-8437-f98b1635a6ac)

![image](https://github.com/user-attachments/assets/d7ab4619-5cfb-46f0-ac87-02e3ccb88cd4)

![image](https://github.com/user-attachments/assets/2e8b2454-a700-496f-ae36-980463d57bcf)

