from flask import Flask, jsonify, request
import sqlite3
import oracledb
import re
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup

# Oracle 데이터베이스 연결
oracledb.init_oracle_client(lib_dir="C:\\project\\cook\\instantclient-basic-windows.x64-11.2.0.4.0\\instantclient_11_2")
connect = oracledb.connect(user='lhr', password='1234', dsn='localhost')
c = connect.cursor()  # Oracle DB 쿼리문

# SQLite 연결
conn = sqlite3.connect('ingredient.db')

# 크롬 옵션 설정
options = Options()
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/0.0.0.0 Safari/537.36")
options.add_argument("Accept-Language: en-US,en;q=0.9")
options.add_argument("Accept-Encoding: gzip, deflate, br")
options.add_argument("Connection: keep-alive")
#options.add_argument("--headless")# 헤드리스 모드로 실행 (UI 없이 실행)
options.add_argument("--disable-gpu")# GPU 사용 안 함
options.add_argument("--no-sandbox")# 샌드박스 모드 비활성화
options.add_argument("--disable-dev-shm-usage")
options.add_argument("--remote-debugging-port=9222")# 디버깅 포트를 추가하여 로그를 볼 수 있도록 설정

# 로그 파일 경로 설정
options.add_argument("--log-level=3")  # 로그 레벨 설정 (3 = Error)
options.add_argument("--v=1")  # 상세한 로그 레벨

# 웹 드라이버 실행
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
wait = WebDriverWait(driver, 30)  # 최대 30초까지 기다림

app = Flask(__name__)

@app.route('/ingreSelect', methods=['POST'])
def ingre_select():
    ingredient = request.form.get('ingredient')  # POST 요청에서 'ingredient' 값 가져오기

    if not ingredient:
        ingredient = '요리 재료'

    try:
        driver.get('https://www.coupang.com/')  # 쿠팡 메인 페이지

        # 검색창 요소 찾기
        search_box = wait.until(EC.presence_of_element_located((By.ID, "headerSearchKeyword")))

        # 검색어 입력 및 검색 실행
        search_box.clear()
        search_box.send_keys(f'{ingredient} 재료')
        search_box.send_keys(Keys.RETURN)

        # 페이지 로딩 대기
        WebDriverWait(driver, 60).until(
            EC.presence_of_all_elements_located((By.CLASS_NAME, 'search-product'))
        )

        # BeautifulSoup으로 결과 크롤링
        soup = BeautifulSoup(driver.page_source, 'html.parser')

        # 상품 항목 찾기
        items = soup.find_all('li', class_='search-product')

        if not items:
            print("No items found")  # 로그 추가
            return jsonify({"message": "No items found"}), 404

        # 결과 데이터 처리
        result_data = []
        for item in items:
            title = item.find('div', class_='name')  # 상품명
            price = item.find('strong', class_='price-value')  # 가격

            if title and price:
                numeric_price = re.sub(r'[^0-9]', '', price.text.strip())
                
                # Oracle DB에 데이터 삽입
                c.execute('INSERT INTO INGREDIENT (name, price) VALUES (:1, :2)', 
                          (title.text.strip(), numeric_price))
                connect.commit()

                result_data.append({
                    'name': title.text.strip(),
                    'price': numeric_price
                })

        # 결과가 있으면 정상 처리, 아니면 메시지 반환
        if result_data:
            print("Data inserted successfully")  # 로그 추가
            return jsonify({"message": "Data inserted successfully", "data": result_data}), 200
        else:
            print("No valid data found")  # 로그 추가
            return jsonify({"message": "No valid data found"}), 404

    except Exception as e:
        print(f"Error occurred: {str(e)}")  # 서버 로그에 오류 메시지 출력
        return jsonify({"error": f"An error occurred: {str(e)}"}), 500

    finally:
        driver.quit()  # 드라이버 종료
        connect.close()  # DB 연결 종료
