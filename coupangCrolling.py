import sqlite3
import oracledb
import pandas as pd
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

# SQLite 데이터베이스 연결
oracledb.init_oracle_client(lib_dir="C:\\project\\cook\\instantclient-basic-windows.x64-11.2.0.4.0\\instantclient_11_2")#lib_dir: 오라클 라이브러리 경로
connect = oracledb.connect(user='lhr', password='1234',dsn='localhost')
c=connect.cursor() #오라클 DB 쿼리문
conn = sqlite3.connect('ingredient.db')
#########################################

# 크롬 옵션 설정
options = Options()
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/192.168.0.39 Safari/537.36")
options.add_argument("Accept-Language: en-US,en;q=0.9")
options.add_argument("Accept-Encoding: gzip, deflate, br")
options.add_argument("Connection: keep-alive")

# 웹 드라이버 실행
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
wait = WebDriverWait(driver, 10)

try:
    driver.get('https://www.coupang.com/')  # 쿠팡 메인 페이지

    # 검색창 요소 찾기
    search_box = wait.until(EC.presence_of_element_located((By.ID, "headerSearchKeyword")))

    # 검색어 입력 및 검색 실행
    search_box.clear()
    search_box.send_keys('음식 재료')
    search_box.send_keys(Keys.RETURN)

    # 페이지 로딩 대기
    time.sleep(10)  # 추가 대기 시간

    # 동적으로 상품 목록이 로딩될 때까지 대기
    WebDriverWait(driver, 60).until(
        EC.presence_of_all_elements_located((By.CLASS_NAME, 'search-product'))
    )

    # BeautifulSoup으로 결과 크롤링
    soup = BeautifulSoup(driver.page_source, 'html.parser')

    # 상품 항목 찾기
    items = soup.find_all('li', class_='search-product')

    if not items:
        print("상품을 찾을 수 없습니다.")  # 항목이 없으면 메시지 출력

    # 데이터 저장
    for item in items:
        title = item.find('div', class_='name')  # 상품명
        price = item.find('strong', class_='price-value')  # 가격

        if title and price:
            numeric_price = re.sub(r'[^0-9]', '', price.text.strip())
            
            # INSERT 문 수정 (순서에 맞게)
            c.execute('INSERT INTO INGREDIENT (name, price) VALUES (:1, :2)', 
                           (title.text.strip(), numeric_price))
            #c.execute('INSERT INTO INGREDIENT (price,name) VALUES(:1,:2)', (price.text.strip(),title.text.strip()))
            connect.commit()
            print(f"상품명: {title.text.strip()}")
            print(f"가격: {price.text.strip()}원")
            print("-" * 50)  # 구분선
            print(f"저장됨: {title.text.strip()} - {price.text.strip()}원")


finally:
    print("크롤링 완료 및 데이터베이스 저장 완료")
    driver.quit()
    conn.close()
