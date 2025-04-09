FROM maven:3.9.6-eclipse-temurin-17-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .

# Устанавливаем зависимости для Chrome и Selenium
RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    # Дополнительные зависимости для работы Chrome в Alpine
    udev \
    ttf-freefont \
    dbus

# Устанавливаем переменные окружения для Chrome
ENV CHROME_BIN="/usr/bin/chromium-browser"
ENV CHROME_PATH="/usr/lib/chromium/"
ENV CHROME_BIN="/usr/bin/chromium-browser"

# Выполняем тесты

# Запуск тестов (сначала API, затем UI)
CMD ["sh", "-c", "\
  echo '=== Запуск API тестов ===' && \
  mvn test -Dtest=api.** && \
  echo '=== Запуск UI тестов ===' && \
  mvn test -Dtest=ui.** -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver"]