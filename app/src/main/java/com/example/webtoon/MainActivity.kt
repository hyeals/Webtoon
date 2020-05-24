package com.example.webtoon

import Webtoon
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_webtoon_page.*

class MainActivity : AppCompatActivity() {

    var WebtoonList = arrayListOf<Webtoon>(
        Webtoon("naver", "네이버 웹툰"),
        Webtoon("daum", "다음 웹툰"),
        Webtoon("kakao", "카카오 페이지"),
        Webtoon("lezhin", "레진 코믹스"),
        Webtoon("bomtoon", "봄툰"),
        Webtoon("bufftoon", "버프툰"),
        Webtoon("comico", "코미코"),
        Webtoon("toomics", "투믹스"),
        Webtoon("toptoon", "탑툰"),
        Webtoon("ktoon", "케이툰"),
        Webtoon("mrblue", "미스터 블루"),
        Webtoon("peanutoon", "피너툰"),
        Webtoon("battlecomics", "배틀 코믹스")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, WebtoonPage::class.java)

        recyclerview.adapter = MainAdapter(this, WebtoonList){ webtoon ->

            // intent에 이름: i, 값: 리사이클러뷰에서 선택한 아이템의  Webtoon클래스의 title값
            intent.putExtra("i", "${webtoon.title}")

            // intent 실행
            startActivity(intent)
        }

        recyclerview.layoutManager = LinearLayoutManager(this)

    }

    // (클릭 이벤트 처리-1) Adapter의 파라미터에 람다식 itemClick을 넣음
    inner class MainAdapter(var context: Context, var webtoon_list: ArrayList<Webtoon>, var itemClick: (Webtoon) -> Unit) :
        RecyclerView.Adapter<MainAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            var view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            // (클릭 이벤트 처리-4) MainHolder에 itemClick을 넘겨줌
            return MainHolder(view, itemClick)
        }

        override fun getItemCount(): Int {
            return webtoon_list.size
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder?.bind(webtoon_list[position], context)
        }

        inner class MainHolder(var view: View, var itemClick: (Webtoon) -> Unit ) : RecyclerView.ViewHolder(view) {
            // (클릭 이벤트 처리-2) MainHolder에서 클릭시 이벤트 처리를 할 것이기 때문에 파라미터에 람다식 itemClick을 넣음

            var webtoon_icon = view.findViewById<ImageView>(R.id.icon)
            var webtoon_title = view.findViewById<TextView>(R.id.title)

            fun bind(webtoon: Webtoon, context: Context) {
                // webtoon.icon의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
                // 이미지가 없는 경우 안드로이드 기본 아이콘을 표시
                if (webtoon.icon != "") {
                    val resourceId = context.resources.getIdentifier(webtoon.icon, "drawable", context.packageName)
                    webtoon_icon?.setImageResource(resourceId)
                } else {
                    webtoon_icon?.setImageResource(R.mipmap.ic_launcher)
                }

                // 나머지 TextView와 String데이터를 연결
                webtoon_title?.text = webtoon.title

                // (클릭 이벤트 처리-3) 아이템 클릭시 처리할 일을 itemClick으로 설정
                view.setOnClickListener { itemClick(webtoon) }
            }
        }
    }
}
