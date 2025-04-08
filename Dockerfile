FROM maven:3.9.6-eclipse-temurin-17

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY . .


 #Устанавливаем зависимости для UI-тестов
RUN apt-get update && \
    apt-get install -y curl && \
    curl -sSL https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb -o chrome.deb && \
    apt-get install -y ./chrome.deb && \
    rm chrome.deb && \
    curl -sSL https://chromedriver.storage.googleapis.com/`curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_linux64.zip -o chromedriver.zip && \
    unzip chromedriver.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver



# Запуск тестов (сначала API, затем UI)
CMD ["sh", "-c", "\
  echo '=== Запуск API тестов ===' && \
  mvn test -Dtest=api.** && \
  echo '=== Запуск UI тестов ===' && \
  mvn test -Dtest=ui.** -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver"]