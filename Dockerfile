FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app
COPY . .

# Устанавливаем Chrome и зависимости
RUN apt-get update && \
    apt-get install -y \
    wget \
    unzip \
    libxss1 \
    libxtst6 \
    libx11-xcb1 \
    libxcomposite1 \
    libxcursor1 \
    libxdamage1 \
    libxi6 \
    libxrandr2 \
    libgtk-3-0 \
    libasound2 \
    libnss3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libpangocairo-1.0-0 \
    fonts-liberation && \
    wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb && \
    wget -q https://chromedriver.storage.googleapis.com/$(wget -qO- https://chromedriver.storage.googleapis.com/LATEST_RELEASE)/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm chromedriver_linux64.zip

# Настройка окружения для Chrome
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROME_DRIVER=/usr/local/bin/chromedriver

CMD ["sh", "-c", "\
  echo '=== Running API tests ===' && \
  mvn test -Dtest=api.** && \
  echo '=== Running UI tests ===' && \
  mvn test -Dtest=ui.** \
    -Dwebdriver.chrome.driver=$CHROME_DRIVER \
    -Dwebdriver.chrome.binary=$CHROME_BIN \
    -Dchrome.options='--no-sandbox,--headless,--disable-dev-shm-usage,--disable-gpu'"]