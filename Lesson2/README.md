# Отчёт по практике 2
## Хасанова Рената БСБО-07-22

**Жизненный цикл Activity**

Создание модуля

Был создан модуль LifecycleActivity, в котором были переопределены ключевые методы жизненного цикла Activity, такие как onCreate, onStart, onResume, onPause, onStop, onDestroy и другие.
Проверка последовательности их вызовов производилась через LogCat в режиме Debug.

Ответы на вопросы по жизненному циклу:

•	Будет ли вызван метод onCreate после нажатия на кнопку «Home» и возврата в приложение?

Ответ: Нет, метод onCreate не вызывается, так как Activity не уничтожается, а просто приостанавливается. 

•	Сохраняется ли значение в поле EditText после перехода по кнопке «Home» и возврата?

Ответ: Да, значение сохраняется, поскольку Activity остаётся в памяти. 

•	Сохраняется ли значение поля EditText после выхода по кнопке «Back» и повторного запуска?

Ответ: Нет, значение теряется, так как при нажатии «Back» вызывается onDestroy, а при новом запуске — onCreate.
 
 ![image](https://github.com/user-attachments/assets/bb260119-3d0e-4741-9f75-c07951fe8352)

![image](https://github.com/user-attachments/assets/7db53394-adde-4032-84f0-45d4c3bd916f)

 ![image](https://github.com/user-attachments/assets/b7567254-b8be-4dcf-90b1-2082db46cb92)

![image](https://github.com/user-attachments/assets/0b493c3e-0d82-4411-8f4a-a55ee0939b88)

 
**Создание и вызов Activity**

Создан модуль MultiActivity, в котором реализованы две активности: MainActivity и SecondActivity.
На главной активности размещены элементы интерфейса, включая EditText. Значение из поля передаётся во вторую активность с помощью явного Intent.
Также была рассмотрена работа жизненного цикла во время перехода между активностями.

 ![image](https://github.com/user-attachments/assets/090db618-6425-443a-998a-8c08828570a4)

 ![image](https://github.com/user-attachments/assets/d7138a74-93f3-4f32-a517-9be85ebdc859)

![image](https://github.com/user-attachments/assets/65cf41e5-4730-4ce7-b948-6050e2340475)

 
**Неявные Intent**

Создан отдельный модуль IntentFilter, в котором реализована работа с неявными намерениями.
С помощью Intent.ACTION_VIEW осуществляется переход на веб-страницу.
Дополнительно реализована возможность передачи данных в другие приложения — например, текстовое сообщение или ссылка.

![image](https://github.com/user-attachments/assets/5aaee1e9-a3de-4823-9e29-d242ace83136)

![image](https://github.com/user-attachments/assets/541f6362-81a8-4dea-910f-6ad247fac892)

![image](https://github.com/user-attachments/assets/25fc0a63-083d-4332-9b28-4a7554260f64)

**Диалоговые окна**

Выполнена реализация всплывающего уведомления (Toast) с выводом количества введённых символов из EditText.

![image](https://github.com/user-attachments/assets/0a6771b6-8d26-44b9-ab85-6bd84622fcd6)

**Notification**

Реализовано системное уведомление (Notification) с использованием канала уведомлений для Android 8.0+.

 ![image](https://github.com/user-attachments/assets/e358e843-2305-4fb9-99a8-2662e84b8ed0)

 ![image](https://github.com/user-attachments/assets/90ac9a20-b8dc-4ba7-b17a-03a0ffd909fb)

![image](https://github.com/user-attachments/assets/7026f63e-ab45-440d-b6e8-49dc2ea666d9)
 
**AlertDialog и диалоговые фрагменты**

Создано стандартное AlertDialog-окно с подтверждением действия. Дополнительно добавлены три диалоговых фрагмента:

•	MyTimeDialogFragment — выбор времени

•	MyDateDialogFragment — выбор даты

•	MyProgressDialogFragment — диалог с прогрессом выполнения

Для отображения обратной связи использовался Snackbar, передающий информацию о выборе пользователя.

![image](https://github.com/user-attachments/assets/5edbbd58-12a2-43a1-8329-b56595b42948)

![image](https://github.com/user-attachments/assets/4b900303-221b-495c-aadc-9abd9a70f886)

![image](https://github.com/user-attachments/assets/2c286e3b-e139-445a-8895-1ed5ef9f195e)



