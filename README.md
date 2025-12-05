# AngularBuilder

Плагин для IntelliJ IDEA, который позволяет создавать новые Angular проекты прямо из IDE.

## Описание

AngularBuilder - это плагин для IntelliJ IDEA, написанный на Kotlin и Java, который упрощает создание новых Angular проектов. Плагин интегрируется с Angular CLI и предоставляет удобный графический интерфейс для настройки параметров проекта.

## Функциональность

- ✅ Создание нового Angular проекта через контекстное меню
- ✅ Настройка параметров проекта (имя, директория, стили)
- ✅ Опция включения Angular Routing
- ✅ Выбор формата стилей (CSS, SCSS, SASS, Less, Stylus)
- ✅ Интеграция с Angular CLI

## Требования

- IntelliJ IDEA 2023.2 или новее
- Node.js и npm установлены в системе
- Angular CLI установлен глобально: `npm install -g @angular/cli`

## Установка

### Из исходников

1. Клонируйте репозиторий или скопируйте проект
2. Откройте проект в IntelliJ IDEA
3. Запустите Gradle задачу `buildPlugin`
4. В меню `Run` → `Run 'Plugin'` для тестирования
5. Для установки: `Run` → `Prepare Plugin Module 'AngularBuilder' for Deployment`

### Сборка плагина

```bash
./gradlew buildPlugin
```

Собранный плагин будет находиться в `build/distributions/AngularBuilder-1.0.0.zip`

## Использование

1. Откройте IntelliJ IDEA
2. В меню `File` → `Create Angular Project` или используйте горячую клавишу `Ctrl+Alt+A`
3. Также доступно через контекстное меню в Project View
4. Заполните параметры проекта:
   - Название проекта
   - Директория для создания
   - Включить ли Angular Routing
   - Формат стилей
5. Нажмите OK для создания проекта

## Структура проекта

```
AngularBuilder/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/example/angularbuilder/
│   │   │       ├── actions/
│   │   │       │   └── CreateAngularProjectAction.kt
│   │   │       └── dialogs/
│   │   │           └── CreateAngularProjectDialog.kt
│   │   ├── java/
│   │   │   └── com/example/angularbuilder/
│   │   │       └── utils/
│   │   │           └── AngularProjectCreator.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── plugin.xml
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Разработка

### Технологии

- **Kotlin** - основной язык для UI компонентов
- **Java** - утилиты для работы с Angular CLI
- **Gradle** - система сборки
- **IntelliJ Platform SDK** - API для разработки плагинов

### Запуск в режиме разработки

1. Откройте проект в IntelliJ IDEA
2. Запустите конфигурацию `Run Plugin` (создается автоматически)
3. Откроется новая инстанция IDEA с установленным плагином

### Отладка

- Установите breakpoints в коде
- Запустите плагин в режиме отладки
- Используйте стандартные инструменты отладки IntelliJ IDEA

## Лицензия

MIT License

## Автор

Example

## Версия

1.0.0

