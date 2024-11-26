import sys
import sqlite3
import oracledb
import time
import re
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup

# 인자로 받은 ingredient 값
ingredient = sys.argv[1]

# 오라클 라이브러리 경로 설정
oracledb.init_oracle_client(lib_dir="C:\\project\\cook\\instantclient_11_2")

# 오라클 데이터베이스 연결
connect = oracledb.connect(user='kkr', password='1234', dsn='localhost')
c = connect.cursor()  # 커서 생성

# 크롬 옵션 설정
options = Options()
options.add_argument("--headless=new")  # 최신 Headless 모드 사용
options.add_argument("--disable-blink-features=AutomationControlled")  # 자동화 감지 방지
options.add_argument("--disable-gpu")  # GPU 비활성화
options.add_argument("--disable-dev-shm-usage")  # 메모리 부족 문제 해결
options.add_argument("--no-sandbox")  # 샌드박스 모드 비활성화 (리눅스 환경에서 권장)
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/192.168.0.12 Safari/537.36")

# WebDriver 설정
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
wait = WebDriverWait(driver, 10)

try:
    # 쿠팡 메인 페이지 접속
    driver.get('https://www.coupang.com/')
    
    # 검색창 로드 대기 후 입력
    search_box = wait.until(EC.presence_of_element_located((By.ID, "headerSearchKeyword")))
    search_box.clear()
    search_box.send_keys(f'{ingredient} 재료')
    search_box.send_keys(Keys.RETURN)

    # 페이지 로딩 대기
    WebDriverWait(driver, 20).until(
        EC.presence_of_element_located((By.CLASS_NAME, "search-product"))
    )

    # BeautifulSoup으로 HTML 파싱
    soup = BeautifulSoup(driver.page_source, 'html.parser')

    # 상품 항목 가져오기
    items = soup.find_all('li', class_='search-product')

    if not items:
        print("상품을 찾을 수 없습니다.")
    else:
        # 데이터 저장
        for item in items:
            title = item.find('div', class_='name')  # 상품명
            price = item.find('strong', class_='price-value')  # 가격

            if title and price:
                numeric_price = re.sub(r'[^0-9]', '', price.text.strip())

                # 데이터베이스에 저장
                c.execute('INSERT INTO INGREDIENT (ingre_seq, name, price, keyword) VALUES (ingre_seq.NEXTVAL, :1, :2, :3)', 
                           (title.text.strip(), numeric_price, ingredient))
                connect.commit()
finally:
    print("크롤링 완료 및 데이터베이스 저장 완료")
    driver.quit()
