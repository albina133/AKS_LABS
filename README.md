# Выполнение практических заданий по дисциплине "Архитектура корпоративных систем" <br>
## Практическая работа №1 <br> Приложение с типовой архитектурой JakartaEE <br>
**Общее задание**<br>
Вам необходимо разработать приложение, используя типовую архитектуру и технологии JakartaEE. Оно должно иметь три слоя (данные, логика, представление) и предоставлять средства для работы с базой данных (включая добавление, редактирование и удаление данных).<br>
Настоятельно рекомендуется использовать систему контроля версий и управления исходным кодом (например, GitHub).<br>
Вы можете выбрать любую IDE.<br>
Но вы не можете использовать IDE для развёртывания проекта на сервере приложений. Развёртывание следует выполнять через консоль администратора или конфигурационные файлы сервера приложений.<br>
**Задание 1**<br>
Скачайте сервер приложений GlassFish или иной доступный вам сервер приложений.<br>
Установите его.<br>
Запустите сервер приложений.<br>
Откройте консоль администратора и ознакомьтесь с её элементами управления.<br>
**Задание 2**<br>
Если у вас не установлена СУБД, установите её. Вы можете выбрать любую реляционную СУБД, но предпочтительна PostgreSQL.<br>
**Задание 3**<br>
Выберите любую предметную область и разработайте модель, содержащую как минимум две сущности с несколькими свойствами. Сформируйте скрипт для создания и заполнения базы данных для вашей модели.<br>
**Задание 4**<br>
Разработайте слой данных в виде Java Beans для вашей модели и подготовьте их к использованию с Entity Persistence.<br>
**Задание 5**<br>
Реализуйте бизнес-слой на основе сессионных EJB-компонентов для доступа к данным и их редактирования.<br>
**Задание 6**<br>
Реализуйте слой представления, используя любую подходящую технологию на ваш выбор.<br>
**Задание 7**<br>
Добейтесь того, чтобы всё это вместе заработало…<br>
## Описание проделанной работы: <br>
Административная консоль сервера приложений GlassFish: <br>
<img width="1919" height="1041" alt="image" src="https://github.com/user-attachments/assets/f6fc8358-79e5-4ebc-ae18-f0ddaecc408a" /> <br>
Развернутое на сервере GlassFish веб-приложение jakarta_lab1_Volkova_Kandalov: <br>
<img width="1417" height="177" alt="image" src="https://github.com/user-attachments/assets/f9b63d70-c1a7-476c-977b-3f8e7c4fdb6c" /> <br>
Настроенный пул соединений PostgresPool и успешная проверка подключения к СУБД: <br>
<img width="1919" height="884" alt="image" src="https://github.com/user-attachments/assets/d5cdf427-e1be-4cbf-9578-bb58de387e84" /> <br>
JDBC-ресурс jdbc/jakarta_lab1DS, использующий пул соединений PostgresPool: <br>
<img width="1917" height="553" alt="image" src="https://github.com/user-attachments/assets/5e951a5d-16d8-44ff-ad3f-77cbe21eeaf5" /> <br>
Структура слоя данных и бизнес-слоя приложения (JPA-сущности Student и Course, EJB-компоненты StudentService и CourseService): <br>
<img width="424" height="782" alt="image" src="https://github.com/user-attachments/assets/343601e0-cf82-4fa2-9672-836f40086aed" /> <br>
Конфигурация persistence-unit jakarta_lab1PU с привязкой к JDBC-ресурсу jdbc/jakarta_lab1DS: <br>
<img width="956" height="286" alt="image" src="https://github.com/user-attachments/assets/139ca595-df9a-4a00-8431-0723669034c1" /> <br>
Реализация слоя представления на основе сервлетов (пример StudentServlet с вызовом EJB-сервиса StudentService): <br>
<img width="716" height="871" alt="image" src="https://github.com/user-attachments/assets/f79e95a3-cbfa-4d20-a91e-0579f0278871" /> <br>
JSP-страница students.jsp для отображения списка студентов и формы добавления новых записей: <br>
<img width="885" height="1009" alt="image" src="https://github.com/user-attachments/assets/5bc7196c-3302-4806-b667-95c1638015b4" /> <br>
Список студентов, получаемый из базы данных PostgreSQL через EJB и JPA (http://localhost:8080/jakarta_lab1_Volkova_Kandalov/students): <br>
<img width="844" height="417" alt="image" src="https://github.com/user-attachments/assets/aded8748-74d3-4492-a03c-734495cac204" /> <br>
Добавим нового студента для проверки работы приложения: <br>
<img width="851" height="448" alt="image" src="https://github.com/user-attachments/assets/4d01b94d-0800-4a90-a3b0-027b76a81f40" /> <br>
Страница отображения курсов (сущность Course) и форма добавления новых курсов с привязкой к студенту (http://localhost:8080/jakarta_lab1_Volkova_Kandalov/courses): <br>
<img width="983" height="445" alt="image" src="https://github.com/user-attachments/assets/905ccc12-9d62-4833-96e8-337ac4ff7919" /> <br>
Содержимое таблиц student (после добавления нового студента через приложение) и course в СУБД PostgreSQL после работы веб-приложения: <br>
<img width="748" height="441" alt="image" src="https://github.com/user-attachments/assets/bdfcbbbf-c6c6-4e41-a377-3163edce56d9" /> <br>
<img width="879" height="410" alt="image" src="https://github.com/user-attachments/assets/622ebe8a-93e3-4708-a0d5-d01242c3aa45" /> <br>

## Практическая работа №2 <br> Приложение на Spring
