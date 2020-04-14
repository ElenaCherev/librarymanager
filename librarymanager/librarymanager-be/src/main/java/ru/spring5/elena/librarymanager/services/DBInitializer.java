package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//Заполняем БД данными примера
@RequiredArgsConstructor
@Service
@Slf4j
public class DBInitializer {

    private final ReaderService readerService;

    private final SectionService sectionService;

    private final LangService langService;

    private final GenreService genreService;

    private final PublishingHouseService publishingHouseService;

    private final AuthorService authorService;

    private final EditionService editionService;

    private final BookService bookService;

    @PostConstruct
    public void initDB() {
        //logger.info("Starting database initialization...");

        try {
            //БИБЛИОТЕКАРИ
/*
            //ЧИТАТЕЛИ
            ReaderEntity reader = new ReaderEntity();
            reader.setFirstName("Иван");
            reader.setSurname("Иванов");
            reader.setPatronymic("Иванович");
            reader.setBirthdate(new Date((new GregorianCalendar(1986, 10, 22)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2019, 9, 16)).getTime().getTime()));
            reader.setTelephone("+79277290500");
            reader.setEmail("owle86@bk.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Петя");
            reader.setSurname("Морковин");
            reader.setPatronymic("");
            reader.setBirthdate(new Date((new GregorianCalendar(2010, 5, 4)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2017, 9, 1)).getTime().getTime()));
            reader.setTelephone("+79271111111");
            reader.setEmail("petia@mail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Лейсан");
            reader.setSurname("Гарипова");
            reader.setPatronymic("");
            reader.setBirthdate(new Date((new GregorianCalendar(2011, 2, 1)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2017, 9, 16)).getTime().getTime()));
            reader.setTelephone("89271111112");
            reader.setEmail("lilu@gmail.com");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Дария");
            reader.setSurname("Лилипутова");
            reader.setPatronymic("Карловна");
            reader.setBirthdate(new Date((new GregorianCalendar(1950, 4, 20)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2019, 9, 16)).getTime().getTime()));
            reader.setTelephone("89271111113");
            reader.setEmail("daria@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Апполинарий");
            reader.setSurname("Сериков");
            reader.setPatronymic("Ксенофонтович");
            reader.setBirthdate(new Date((new GregorianCalendar(1976, 11, 25)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2019, 9, 16)).getTime().getTime()));
            reader.setTelephone("89271111113");
            reader.setEmail("appol@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Диана");
            reader.setSurname("Бернвуд");
            reader.setPatronymic("Дедтрейдовна");
            reader.setBirthdate(new Date((new GregorianCalendar(1980, 7, 4)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111114");
            reader.setEmail("diana@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Василий");
            reader.setSurname("Лукин");
            reader.setPatronymic("Алексеевич");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111115");
            reader.setEmail("vasia@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Алексей");
            reader.setSurname("Букин");
            reader.setPatronymic("Ильич");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111116");
            reader.setEmail("alex@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Клара");
            reader.setSurname("Питерская");
            reader.setPatronymic("Кораловна");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111117");
            reader.setEmail("Klara@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Афродита");
            reader.setSurname("Пипякина");
            reader.setPatronymic("Павловна");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111118");
            reader.setEmail("afrodita@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Женя");
            reader.setSurname("Боровой");
            reader.setPatronymic("");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111119");
            reader.setEmail("evgen@gmail.ru");
            readerService.save(reader);

            reader = new ReaderEntity();
            reader.setFirstName("Маша");
            reader.setSurname("Лесная");
            reader.setPatronymic("");
            reader.setBirthdate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
            reader.setRegDate(new Date((new GregorianCalendar(2018, 1, 1)).getTime().getTime()));
            reader.setTelephone("89271111110");
            reader.setEmail("masha@gmail.ru");
            readerService.save(reader);

            //АВТОРЫ
            AuthorEntity julia = new AuthorEntity();
            julia.setFirstName("Юлиана");
            julia.setSurname("Козьмина");
            Author juliaTDO = authorService.save(AuthorMapper.map(julia));

            AuthorEntity rob = new AuthorEntity();
            rob.setFirstName("Роб");
            rob.setSurname("Харроп");
            rob = authorService.save(rob);

            AuthorEntity kris = new AuthorEntity();
            kris.setFirstName("Крис");
            kris.setSurname("Шефер");
            kris = authorService.save(kris);

            AuthorEntity ho = new AuthorEntity();
            ho.setFirstName("Кларенс");
            ho.setSurname("Хо");
            ho = authorService.save(ho);

            //ЯЗЫКИ
            LangEntity russianLang = new LangEntity();
            russianLang.setTitle("Русский");
            russianLang = langService.save(russianLang);

            LangEntity englishLang = new LangEntity();
            englishLang.setTitle("Английский");
            englishLang = langService.save(englishLang);

            LangEntity frenchLang = new LangEntity();
            frenchLang.setTitle("Франнцузский");
            frenchLang = langService.save(frenchLang);

            LangEntity germanLang = new LangEntity();
            germanLang.setTitle("Немецкий");
            germanLang = langService.save(germanLang);

            //ЖАНРЫ
            GenreEntity genre = new GenreEntity();
            genre.setTitle("Роман");
            genreService.save(genre);

            genre = new GenreEntity();
            genre.setTitle("Повесть");
            genreService.save(genre);

            genre = new GenreEntity();
            genre.setTitle("Поэма");
            genreService.save(genre);

            genre = new GenreEntity();
            genre.setTitle("Статья");
            genreService.save(genre);

            genre = new GenreEntity();
            genre.setTitle("Рассказы");
            genreService.save(genre);

            GenreEntity tutorialGenre = new GenreEntity();
            tutorialGenre.setTitle("Учебник");
            tutorialGenre = genreService.save(tutorialGenre);

            //КНИЖННЫЕ РАЗДЕЛЫ
            SectionEntity section = new SectionEntity();
            section.setParentSection(null);
            section.setTitle("Художественнная литература");
            SectionEntity parentSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Приключения");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Дектективы");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Фаннтастика");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Классическая литература");
            parentSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Поэзия");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(null);
            section.setTitle("Научная литература");
            parentSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Математика");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Физика");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Химия");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Биология");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Лингвистика");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("История");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Медицинская литература");
            parentSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Кардиология");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Диетология");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(null);
            section.setTitle("Компьютернная литература");
            parentSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Языки программирования");
            SectionEntity springSection = sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Администрирование сетей");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Шаблоны проектирования");
            sectionService.save(section);

            section = new SectionEntity();
            section.setParentSection(parentSection);
            section.setTitle("Базы даннных");
            sectionService.save(section);

            //ИЗДАТЕЛЬСТВА
            PublishingHouseEntity publHouse = new PublishingHouseEntity();
            publHouse.setTitle("Диалектика");
            publHouse = publishingHouseService.save(publHouse);

            EditionEntity springEdition = new EditionEntity();
            springEdition.setWorkTitle("Spring 5 для профессионалов");
            springEdition.setGenre(tutorialGenre);
            springEdition.setLang(englishLang);
            springEdition.setYear(2018);
            springEdition.setSection(springSection);
            springEdition.getAuthors().add(julia);
            springEdition.getAuthors().add(rob);
            springEdition.getAuthors().add(kris);
            springEdition.getAuthors().add(ho);
            springEdition.setAge(AgeRating._12);
            springEdition.setIllustrated(true);
            springEdition.setIsbn("978-5-907114-07-4");
            springEdition.setPublLang(russianLang);
            springEdition.setPublishingHouse(publHouse);
            springEdition.setPublishingYear(2019);
            springEdition.setTitle("Spring 5 для профессионалов");
            springEdition = editionService.save(springEdition);


            publHouse = new PublishingHouseEntity();
            publHouse.setTitle("АСТ");
            publishingHouseService.save(publHouse);

            publHouse = new PublishingHouseEntity();
            publHouse.setTitle("Нигма");
            publishingHouseService.save(publHouse);

            publHouse = new PublishingHouseEntity();
            publHouse.setTitle("Мир");
            publishingHouseService.save(publHouse);

            publHouse = new PublishingHouseEntity();
            publHouse.setTitle("Просвещение");
            publishingHouseService.save(publHouse);

            //ЭКЗЕМПЛЯРЫ КНИГ
            BookEntity book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);

            book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);

            book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);

            book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);

            book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);

            book = new BookEntity();
            book.setAvailable(true);
            book.setEdition(springEdition);
            book.setReserved(false);
            book.setShelfNumber(10);
            bookService.save(book);
*/
            //logger.info("Database initialization finished.");
        } catch (Exception ex) {
            //logger.info("Database initialization failed: ", ex);
        }

    }
}
