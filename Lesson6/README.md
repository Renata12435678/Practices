# Отчёт по 6 практике
## Хасанова Рената БСБО-07-22

**App (SharedPreferences)**

Создан проект Lesson6, демонстрирующий базовое использование SharedPreferences:

•	Сохраняет значения из EditText: номер группы, номер по списку, любимый фильм.

•	Используется getSharedPreferences() для загрузки данных при старте приложения.

•	Файл настроек найден через Device Explorer, скриншот сохранён в res/raw.

![image](https://github.com/user-attachments/assets/4789c343-8594-4952-97a4-5d833d4b1dbd)
 
**Secured Shared Preferences**

Модуль SecuredSharedPreferences реализует защищённое хранение данных:

•	Используется EncryptedSharedPreferences из библиотеки security-crypto.

•	Хранит зашифрованное имя любимого поэта, отображает его вместе с изображением.

•	Настроена инициализация через MasterKey для шифрования/дешифрования.

•	Проверено, что данные в SharedPreferences действительно зашифрованы.

•	Скриншот содержимого файла настроек — в res/raw.
 
![image](https://github.com/user-attachments/assets/6b226845-8248-4fe2-99aa-9dee5a1d477e)

![image](https://github.com/user-attachments/assets/af0837f0-d7db-46f0-8447-dcb2884003ff)

**Internal File Storage**

Модуль InternalFileStorage демонстрирует работу с внутренним хранилищем:

•	Сохраняет и загружает текстовые данные (дата + описание события).

•	Применяет FileOutputStream и FileInputStream для работы с файлом historical_date.txt в приватной директории.

•	Обеспечена обработка ошибок и визуальные уведомления (Toast).

•	Содержимое файла получено через Device Explorer и добавлено в res/raw.

![image](https://github.com/user-attachments/assets/93829370-01cb-4680-a381-50e0211c0bb4)

![image](https://github.com/user-attachments/assets/879b24f1-f176-4b2b-88b6-5585296c9345)

**Notebook (External Storage)**

Модуль Notebook работает с внешним хранилищем (Environment.DIRECTORY_DOCUMENTS):

•	Позволяет сохранять/загружать цитаты по имени файла.

•	Используется Java I/O (BufferedReader, FileInputStream, OutputStreamWriter).

•	Созданы и протестированы два текстовых файла с цитатами.

•	Скопированы в res/raw для демонстрации содержимого.

![image](https://github.com/user-attachments/assets/c323939e-1b98-4271-95f2-48a48e947231)

 ![image](https://github.com/user-attachments/assets/26bbfd03-c6c7-4888-9f2b-27f5a68faafb)

 ![image](https://github.com/user-attachments/assets/01abe541-b8fc-4e15-96b4-953bd946e32f)

**EmployeeDB (Room)**

Модуль EmployeeDB реализует полноценную работу с базой данных на основе Room:

Структура базы данных:

•	Hero (@Entity): поля id, name, superpower, strength.

•	HeroDao (@Dao): методы добавления, удаления, обновления и выборки.

•	AppDatabase (@Database): точка доступа к БД.

Особенности:

•	Реализация паттерна Singleton (getInstance()).

•	Разрешена работа с БД на главном потоке для упрощения примера (allowMainThreadQueries).

•	Обновление UI через LiveData и RecyclerView.

•	Валидация данных и логирование операций.

UI:

•	Форма ввода данных о супергерое.

•	RecyclerView с кнопками удаления и редактирования.
 
 ![image](https://github.com/user-attachments/assets/4ef6e30b-178c-459b-9d35-7a4a3547c29f)

![image](https://github.com/user-attachments/assets/727f8354-be63-43ba-b296-ada73f85668d)

![image](https://github.com/user-attachments/assets/c68907ac-1e7a-4dbf-856d-7d874048f05f)

**Mirea Project**

Добавлены два новых фрагмента в раздел "Profile & Storage" в проекте MireaProject.

Фрагмент "Профиль"

•	Сохраняет данные: имя, возраст, email, телефон.

•	Использует файл настроек profile_prefs (SharedPreferences).

•	Загружает сохранённые данные при открытии.

•	Кнопка сохранения и Toast-оповещения.

Фрагмент "Работа с файлами"

•	Поддержка создания, шифрования (XOR) и сохранения заметок.

•	Работа во внутреннем хранилище (в приватной директории).

•	Добавление заметок через AlertDialog и FAB-кнопку.

•	Обработка многострочного текста.

•	Простейшее шифрование/дешифрование с фиксированным ключом.

•	Обработка ошибок и сохранение/загрузка через потоки.

![image](https://github.com/user-attachments/assets/4b836ae4-a29c-4e5c-9602-c22898af955a)

![image](https://github.com/user-attachments/assets/d37e2ec8-421b-4a6b-884d-3b35476d6281)

![image](https://github.com/user-attachments/assets/ddce9722-bc12-4f31-bf17-a538d8d52cc3)

![image](https://github.com/user-attachments/assets/0e8f4867-061b-4bf3-9f82-aae1fe7f0815)

