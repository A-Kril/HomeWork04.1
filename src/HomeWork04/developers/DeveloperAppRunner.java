/*Aleksandr Krilevets
Задание по Java IO

Дана сущность Developer

Необходимо реализовать консольное приложение со следующим функционалом:

1. Создание разработчика
2. Редактирование
3. Получение данных (чтение)
4. Удаление

В качестве хранилища использовать текстовый файл

Необходимо реализовать следующие классы

Developer - POJO класс, который содержит данные о разработчике
Класс DeveloperDAO - содержит методы для работы с текстовым файлом
Класс DeveloperView - который содержит логику работы с консолью
Класс DeveloperController - экземпляр которого создаётся в DeveloperView для обработки запросов из консоли и содержит DeveloperDAO.
Программа должна прекращать работу только по указанию пользователя
Выход из программы в результате исключения недопустим.
 */

package HomeWork04.developers;

import HomeWork04.developers.view.DeveloperView;

public class DeveloperAppRunner {
    public static void main(String[] args) throws Exception{

        DeveloperView view = new DeveloperView();
        view.startMenu();
    }
}
