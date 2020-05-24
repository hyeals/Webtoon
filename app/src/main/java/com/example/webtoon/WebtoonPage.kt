package com.example.webtoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import kotlinx.android.synthetic.main.activity_webtoon_page.*

class WebtoonPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtoon_page)

        // intent로 부터 데이터 받아오기
        // name이 i인 데이터를 받아와서 kind에 넣음
        var kind = intent.getStringExtra("i")

        // WebView에서 자바스크립트 사용이 안되도록 디폴트 설정되어있어서 이를 true(자바 스크립트 허용)로 바꿔줌
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // WebView 실행
        when(kind){
            "네이버 웹툰" -> webView.loadUrl("https://m.comic.naver.com/")

            "다음 웹툰" -> webView.loadUrl("http://m.webtoon.daum.net/m")

            "카카오 페이지" -> webView.loadUrl("https://page.kakao.com/main")

            "레진 코믹스" -> webView.loadUrl("https://www.lezhin.com/")

            "버프툰" -> webView.loadUrl("https://bufftoon.plaync.com/")

            "코미코" -> webView.loadUrl("https://www.comico.kr/")

            "봄툰" -> webView.loadUrl("https://www.bomtoon.com/")

            "투믹스" -> webView.loadUrl("https://m.toomics.com/")

            "탑툰" -> webView.loadUrl("https://toptoon.com/")

            "케이툰" -> webView.loadUrl("https://www.myktoon.com/mw/homescreen/main.kt")

            "미스터 블루" -> webView.loadUrl("http://m.mrblue.com/")

            "피너툰" -> webView.loadUrl("https://www.peanutoon.com/ko")

            "배틀 코믹스" -> webView.loadUrl("http://www.battlecomics.co.kr/")
        }

    }

    override fun onBackPressed() {
        // webView가 이전 페이지로 갈 수 있다면
        if(webView.canGoBack()){
            // 이전 페이지로 이동
            webView.goBack()
        }else{
            // 그렇지 않다면 액티비티 종료
            super.onBackPressed()
        }
    }
}
