# a.gavrilova
## Описание задачи
UI, API, DB тестирование проекта развернутого на Docker

1. Получение токена запросом к апи
2. Переход на сайт. Прохождение авторизации. Передача токена. Обновление страницы
3. Переход на страницу проекта Nexage. Запросом к БД получение списка тестов проекта
4. Тесты на странице отсортированы по убыванию даты
5. Тесты с веб-страницы соответствуют тестам полученным с БД
6. Возврат на предыдущую страницу. Нажатие на +Add. Ввод названия проекта, и сохранение. Закрытие алерта с информацией о сохранённом проекте. Обновление страницы
7. Проект сохранился. Окно добавления закрылось. Проект появился в списке
8. Переход на страницу созданного проекта. Добавления теста через БД(вместе с логом и скриншотом текущей страницы)
9. Тест отображается на странице
10. Переход на страницу созданного теста
11. Страница с информацией о тесте открылась
12. Информация с теста на странице соответствует информации с создания теста
   

## Структура проекта
* **src**
    * **Framework/java**
        * **apiutil** - Утилита для тестирования API
        * **database** - Модели и утилиты для тестирования БД
    * **Project** - Часть, связанная с проектом, с объектами страницы, моделями и утилитами
        * **java**
            * **consts** - Константы (Пути к конфигурационным данным, тестовым данным, Endpoint, параметры для запросов API)
            * **models** - Модели
            * **elements** - Кастомные элементы
            * **steps** - Шаги
            * **utils** - Утилиты (Тестовые и API)
            * **tests** - Тесты
            * **pages** - Страницы
    * **resources** - Файлы ресурсов (конфигурационные и тестовые данные)

## Настройка проекта
&emsp;Настройка проекта происходит в файле [settings.json](https://github.com/aquality-automation/aquality-selenium-java-template/blob/master/aquality-selenium-template/src/main/resources/settings.json), который содержит в себе настройки Aquality Selenium библиотеки. Дополнительная информация [здесь](https://github.com/aquality-automation/aquality-selenium-java/wiki/Overview-(Russian)). \
&emsp;В файле resources/configData необходимо заполнить данные **password** и **login**. \
&emsp;Также в данном файле необходимо заменить **HOST** и **PORT** на необходимые. \
&emsp;Настройка данных для БД происходит в файле resources/configDb, необходимо заполнить пустые поля и заменить **HOST** и **PORT** на необходимые значения.

## Запуск тестов
```bash
mvn clean test
```