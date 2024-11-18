from flask import Flask, jsonify, request
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup
import time
from flask import Response

# JSON 응답 반환
return Response(json.dumps(search_results), mimetype='application/json')
app = Flask(__name__)

@app.route('/ingreSelect', methods=['POST'])
def ingre_select():
    # Ajax에서 넘어온 데이터 받기
    ingre_val = request.form['ingredient']
    ingredients = ingre_val.split(',')  # 쉼표로 구분된 재료 목록 처리

    # Selenium 크롤링 초기화
    options = Options()
    options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/192.168.0.39 Safari/537.36")
    options.add_argument("--headless")  # GUI 없이 실행
    options.add_argument("--disable-gpu")
    options.add_argument("--no-sandbox")

    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    wait = WebDriverWait(driver, 10)

    search_results = []

    try:
        driver.get('https://www.coupang.com/')  # 쿠팡 메인 페이지

        for ingredient in ingredients:  # 각 재료별 검색
            search_box = wait.until(EC.presence_of_element_located((By.ID, "headerSearchKeyword")))
            search_box.clear()
            search_box.send_keys(f'{ingredient} 재료')
            search_box.send_keys(Keys.RETURN)

            time.sleep(5)  # 페이지 로딩 대기

            # 검색된 상품 로딩 확인
            WebDriverWait(driver, 10).until(
                EC.presence_of_all_elements_located((By.CLASS_NAME, 'search-product'))
            )

            # BeautifulSoup으로 결과 파싱
            soup = BeautifulSoup(driver.page_source, 'html.parser')
            items = soup.find_all('li', class_='search-product')

            ingredient_results = []
            for item in items:  # 모든 검색 결과 반환
                title = item.find('div', class_='name')
                price = item.find('strong', class_='price-value')
                link = item.find('a', class_='search-product-link')

                if title and price and link:
                    ingredient_results.append({
                        "title": title.text.strip(),
                        "price": price.text.strip(),
                        "link": f"https://www.coupang.com{link['href']}"
                    })

            search_results.append({
                "ingredient": ingredient,
                "items": ingredient_results if ingredient_results else [{"message": "검색된 결과 없음"}]
            })

    finally:
        driver.quit()

    return jsonify(search_results)  # JSON 응답

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9999)
