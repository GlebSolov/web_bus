# Система информации об автобусных рейсах и билетах

![Alt text](visual.png?raw=true "visual_scheme")

**Use Cases для Web-приложения**

**Страницы:** 

**Домашняя страница(пользователя)**

На нее попадает человек как только зашел на сайт/приложение. 
+ На ней по центру располгаются для ввода откуда/куда (нужно если человек хочет просто посмотреть есть ли автобусы и их цену и расписание)
+ Сверху справа находится кнопка входа(открывается pop-up с выбором регистрации или входа)
+ есть переход в страницу истории заказов

При поиске по фильтрам открывается табло расписаний маршрутов и цены, кликнув на который можно узнать о автобусе и купить билеты(только после авторизации)
После выбора начальной и конечной остановки появляется список рейсов с датой, временем отправления, стоимостью билета и количеством свободных мест. этот список будет представлен в виде таблицы, выбрав в ней строку мы выбираем рейс. начальную и конечную остановку выбираем поиском в списке

**Домашняя страница(админа)**

Сюда попадает админ, после входа по правильному логину и паролю
+ Собраны все действия по изменению любой информации пользователей, добавлении/удалении рейсов, остановок и тд. все это оформлено в виде списков, представленных таблицами. Дальше просто выбираем нужную секцию и нас перекидывает на другую страницу
+ есть переход в историю заказов, домашнюю страницу пользователя и информацию о конкретном рейсе

**Страница входа:**

На эту страницу попадают люди, уже зарегистрированные.
Вбивают
+ логин
+ пароль

Нажимают кнопку "Войти"
При успехе вылетают на домашнюю страницу, где вместо кнопки входа появляется кнопка кабинет
		
**Страница регистрации:**

На эту страницу попадают люди, еще незарегистрированные.
Поля: 
+ Логин
+ Пароль
+ Фамилия
+ Имя
+ Email(чтобы пройти верификацию)
(Если же это админ от компании, то у них свой уникальный ник, который в БД будет помечен как администратор, и по умолчанию будут предоставляться более расширенный контент)

После успешной регистрации они вылетают на домашнюю страницу, где также появляется кнопка кабинет

**Изменение информации**

Страница, схожая по полям со страницей регистрации + адрес + телефон

**История покупок** 

На ней открывается просто открывается инфо данного клиента по всем его покупкам в базе данных в хронологическом порядке


**Новый рейс**

Эта страница предоставляется админам, для заполнения нового маршрута

Поля:
+ Номер маршрута
+ Откуда
+ Куда
+ время
+ Цена билета
+ Кол-во мест в одном автобусе

После всего заполнения появляется кнопка добавить маршрут и выкидывает обратно на страницу "Домашнюю страницу".


**"Коррекция Маршрута"**
Эта страница предоставляется админам, для изменения маршрута

Поля:
+ Номер маршрута
+ Откуда
+ Куда
+ время
+ Цена билета
+ Кол-во мест в одном автобусе

После всего заполнения появляется кнопка добавить маршрут и выкидывает обратно на страницу "Домашнюю страницу".

**История маршрута** 

Открывается просто история покупок пассажира

**Страница Маршруты**

Фильтр по полям откуда, куда, и сам маршрут

# Действия, доступные в этом приложении после авторизации:

**заказ билетов на рейс

пользователь выбирает начальную и конечную остановки
из появившейся таблицы с датами-временем и количеством мест на рейсе пользователь выбирает рейс на который хочет зарегистрироваться. Пользователь выбирает количество нужных билетов и нажимает кнопку оформления билетов (при посадке на рейс он оплачивает проезд тем, кто поедет по оформленным билетам, то есть пользователь не обязательно должен ехать сам)

**просмотр истории заказов

Пользователь нажимает на кнопку "история" и переходит на страницу истории своих заказов где может смотреть какие билеты он когда-либо оформлял

**админский просмотр истории

Пользователь-администратор переходит на страницу истории из домашней страницы или из админской домашней страницы
по одному из полей пользователь сортирует/фильтрует данные, которые хочет увидеть и смотрит

**админский просмотр информации о рейсе

Пользователь из домашней админской страницы переходит на страницу информации о рейсе где по каким-то полям фильтрует/сортирует все рейсы
после выбора рейса ему отображается информация о рейсе: кто на нем ехал, по какому маршруту и тп

**админские действия по изменению данных

пользователь на домашней странице админа может просматривать и изменять информацию пользователей в специальном форме, где сначала можно найти пользователя а потом открыть на просмотр/редактирование его профиль
также там можно добавлять рейсы, маршруты или же менять о них информацию аналогично информации о пользователе


# Строение базы данных для этого

![Alt text](bd_scheme.png?raw=true "bd")
