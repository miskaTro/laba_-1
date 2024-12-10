# Задание по коллекциям Java

## Описание

Это приложение представляет собой библиотеку, где можно добавлять книги, авторов и жанры. Программа использует коллекции Java, такие как `ArrayList`, `HashSet`, и `HashMap`, для хранения информации о книгах, авторах и жанрах. Основная цель приложения — демонстрация работы с коллекциями Java на практике.

## Требования

Программа должна выполнять следующие функции:
- Хранить список книг с помощью коллекции `ArrayList<Book>`.
- Хранить уникальных авторов с помощью коллекции `HashSet<Author>`.
- Хранить книги по жанрам с помощью коллекции `HashMap<String, List<Book>>`, где ключ — это жанр, а значение — список книг этого жанра.
- Предоставлять возможность добавлять новые книги и выводить список книг, авторов и жанров.

## Структура проекта

### 1. **Класс `Author`**

Этот класс представляет автора книги. В нем хранится имя автора, а также реализованы методы `equals()`, `hashCode()` и `toString()` для правильного сравнения и вывода информации.

```java
public class Author {
    private String name;  // Поле для хранения имени автора

    // Конструктор для инициализации объекта Author с именем
    public Author(String name) {
        this.name = name;
    }

    // Геттер для получения имени автора
    public String getName() {
        return name;
    }

    // Переопределение метода equals для сравнения объектов Author по имени
    @Override
    public boolean equals(Object o) {
        // Если два объекта ссылаются на один и тот же экземпляр, возвращаем true
        if (this == o) return true;

        // Если объект o равен null или они принадлежат разным классам, возвращаем false
        if (o == null || getClass() != o.getClass()) return false;

        // Приводим объект o к типу Author и сравниваем имена
        Author author = (Author) o;
        return name.equals(author.name);  // Сравниваем имена авторов
    }

    // Переопределение метода hashCode для корректного вычисления хэш-кода на основе имени
    @Override
    public int hashCode() {
        return name.hashCode();  // Возвращаем хэш-код имени автора
    }

    // Переопределение метода toString для удобного представления объекта Author в строковом виде
    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';  // Возвращаем строковое представление объекта с именем автора
    }
}
```
### 2. **Класс `Book`**

Этот класс представляет книгу с полями `title` (название), `author` (автор) и `genre` (жанр). Класс также содержит методы геттеров и `toString()`.

```java
public class Book {

    // Поле для хранения названия книги
    private String title;

    // Поле для хранения имени автора книги
    private String author;

    // Поле для хранения жанра книги
    private String genre;

    // Конструктор для инициализации объекта книги
    // Принимает название, автора и жанр книги
    public Book(String title, String author, String genre) {
        this.title = title;    // Инициализация названия книги
        this.author = author;  // Инициализация автора книги
        this.genre = genre;    // Инициализация жанра книги
    }

    // Геттер для получения названия книги
    public String getTitle() {
        return title;
    }

    // Геттер для получения имени автора книги
    public String getAuthor() {
        return author;
    }

    // Геттер для получения жанра книги
    public String getGenre() {
        return genre;
    }

    // Переопределённый метод toString для красивого вывода информации о книге
    // Возвращает строковое представление объекта Book в виде:
    // "Book{title='название', author='автор', genre='жанр'}"
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +    // Добавляем название книги
                ", author='" + author + '\'' +  // Добавляем автора книги
                ", genre='" + genre + '\'' +    // Добавляем жанр книги
                '}';
    }
}
```
### 3. **Класс `Library`**

Класс `Library` управляет коллекциями книг, авторов и жанров. Он позволяет добавлять книги в библиотеку и хранит информацию о жанрах и авторах.

```java
/**
 * Класс, представляющий библиотеку.
 * В библиотеке хранятся книги, авторы и книги по жанрам.
 */
public class Library {

    // Список для хранения всех книг библиотеки
    private ArrayList<Book> books;

    // Множество для хранения уникальных авторов
    private HashSet<Author> authors;

    // Словарь для хранения книг по жанрам
    private HashMap<String, List<Book>> booksByGenre;

    /**
     * Конструктор для инициализации коллекций.
     */
    public Library() {
        books = new ArrayList<>(); // Инициализация списка книг
        authors = new HashSet<>(); // Инициализация множества авторов
        booksByGenre = new HashMap<>(); // Инициализация мапы для жанров
    }

    /**
     * Метод для добавления книги в библиотеку.
     * Книга добавляется в список книг, в множество авторов (если автора нет),
     * и в мапу книг по жанрам.
     *
     * @param book книга, которую нужно добавить
     */
    public void addBook(Book book) {
        books.add(book); // Добавляем книгу в список всех книг

        // Добавляем автора в множество (если его там нет)
        authors.add(new Author(book.getAuthor()));

        // Если жанр книги еще не существует в мапе, создаем для него новый список
        booksByGenre.putIfAbsent(book.getGenre(), new ArrayList<>());

        // Добавляем книгу в список книг по жанру
        booksByGenre.get(book.getGenre()).add(book);
    }

    /**
     * Метод для получения списка всех книг в библиотеке.
     *
     * @return список всех книг
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Метод для получения множества уникальных авторов.
     *
     * @return множество авторов
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * Метод для получения книг по жанрам.
     *
     * @return мапа, где ключ — жанр, а значение — список книг этого жанра
     */
    public Map<String, List<Book>> getBooksByGenre() {
        return booksByGenre;
    }
}
```
### 4. **Класс `BookAdapter`**

Этот адаптер используется для отображения списка книг в `RecyclerView`. Он связывает данные о книгах с соответствующими элементами интерфейса.

```java
// Адаптер для отображения списка книг в RecyclerView
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList; // Список книг, который будет отображаться в RecyclerView

    // Конструктор адаптера, принимающий список книг
    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    // Метод для создания и возврата нового ViewHolder
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Инфлейтинг макета для каждого элемента списка (item_book.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        // Возвращаем новый объект ViewHolder, который будет держать элементы интерфейса
        return new BookViewHolder(view);
    }

    // Метод для связывания данных (книги) с элементами интерфейса
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Получаем книгу из списка по текущей позиции
        Book book = bookList.get(position);

        // Устанавливаем текст для каждого элемента интерфейса (название, автор, жанр)
        holder.title.setText("Название: " + book.getTitle());
        holder.author.setText("Автор: " + book.getAuthor());
        holder.genre.setText("Жанр: " + book.getGenre());
    }

    // Метод для получения количества элементов в списке
    @Override
    public int getItemCount() {
        return bookList.size(); // Возвращаем размер списка книг
    }

    // Внутренний класс для хранения элементов интерфейса, представляющих отдельную книгу
    static class BookViewHolder extends RecyclerView.ViewHolder {
        // Элементы интерфейса для отображения данных книги
        TextView title, author, genre;

        // Конструктор ViewHolder, инициализирующий элементы интерфейса
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            // Привязываем элементы из макета (item_book.xml) к переменным
            title = itemView.findViewById(R.id.textTitle);
            author = itemView.findViewById(R.id.textAuthor);
            genre = itemView.findViewById(R.id.textGenre);
        }
    }
}
```
### 5. **Главная активность `MainActivity`**

Главная активность отвечает за взаимодействие с пользователем, позволяя добавлять книги и отображать их в `RecyclerView`. Она также выводит информацию о жанрах и авторах.

```java
public class MainActivity extends AppCompatActivity {

    // Объявление переменных для хранения информации о библиотеке, полях ввода и адаптере для отображения книг
    private Library library;
    private EditText editTitle, editAuthor, editGenre;
    private RecyclerView recyclerViewBooks;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливаем layout для этой активности
        setContentView(R.layout.activity_main);

        // Инициализация объекта библиотеки
        library = new Library();

        // Получаем ссылки на элементы UI для ввода данных (название, автор, жанр)
        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        editGenre = findViewById(R.id.editGenre);

        // Настройка RecyclerView для отображения списка книг
        recyclerViewBooks = findViewById(R.id.recyclerViewBooks);

        // Устанавливаем менеджер компоновки для RecyclerView, чтобы он отображал элементы вертикально
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));

        // Создаем и устанавливаем адаптер для RecyclerView. Адаптер будет получать книги из библиотеки
        bookAdapter = new BookAdapter(library.getBooks());
        recyclerViewBooks.setAdapter(bookAdapter);

        // Получаем ссылку на кнопку "Добавить" и настраиваем обработчик клика
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем введенные данные
                String title = editTitle.getText().toString();
                String author = editAuthor.getText().toString();
                String genre = editGenre.getText().toString();

                // Проверяем, что все поля заполнены
                if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                    // Добавляем новую книгу в библиотеку
                    library.addBook(new Book(title, author, genre));
                    // Обновляем вывод
                    updateOutput();
                }
            }
        });
    }

    // Метод для обновления данных в RecyclerView и вывода информации о жанрах и книгах
    private void updateOutput() {
        // Обновляем отображаемые данные в адаптере
        bookAdapter.notifyDataSetChanged();

        // Строим строку для вывода информации о книгах по жанрам
        StringBuilder output = new StringBuilder("\nАвторы и жанры:\n");
        // Проходим по всем жанрам и добавляем информацию о книгах этого жанра в строку
        for (String genre : library.getBooksByGenre().keySet()) {
            output.append(genre).append(": ")
                    .append(library.getBooksByGenre().get(genre))
                    .append("\n");
        }

        // Пока результат не выводится на экран, но его можно использовать или логировать
    }
}

