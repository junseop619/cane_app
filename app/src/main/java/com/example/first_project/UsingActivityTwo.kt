package com.example.first_project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.text.buildSpannedString
import com.example.first_project.databinding.ActivityUsingTwoBinding


class UsingActivityTwo : AppCompatActivity() {

    val list : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityUsingTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_sample)

        binding.companyText2.setVisibility(View.INVISIBLE)
        binding.boxText.setVisibility(View.INVISIBLE)

        //read company name from using1_page(spinner)
        if(intent.hasExtra("company")){
            val companyName = intent.getStringExtra("company")


            /*binding.companyText.text = companyName*/
            var spannableString = SpannableString(companyName)
            spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
            binding.companyText.text =spannableString

            list.add(companyName.toString())


            //chat_bot link code (not fact check. so this is temporary)
            //this is other1
            when(companyName) {
                "SAMSUNG" -> {
                    binding.other1.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.samsungsvc.co.kr/consult/chatbot"))
                        startActivity(intent)
                    }
                }

                "LG" -> {
                        binding.other1.setOnClickListener {
                            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lg.co.kr/"))
                            startActivity(intent)
                        }
                    }

                "쿠쿠" -> {
                    binding.other1.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.kakao.com/login/?continue=https%3A%2F%2Fbizmessage.kakao.com%2Fchat%2FzCNc6Z2Deu6qTmsdNnqEyA%3Frf%3Dhttps%3A%2F%2Fapi.happytalk.io%2F"))
                        startActivity(intent)
                    }
                }

                "코웨이" -> {
                    binding.other1.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_jErHd/chat"))
                        startActivity(intent)
                    }
                }

                "필립스" -> {
                    binding.other1.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_KSKxgC"))
                        startActivity(intent)
                    }
                }

                "레노버" -> {  //얘는 없으니깐 dialog를 하던 알아서
                    binding.other1.setOnClickListener {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("죄송합니다")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }
                }

                "캐리어" -> {  //얘도 없음
                    binding.other1.setOnClickListener {
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("피드백")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }
                }

                else -> {
                    binding.other1.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
                        startActivity(intent)
                    }
                }
            }
        }

        //read product kind from using1_page(second spinner)
        if(intent.hasExtra("product")) {
            val productKind = intent.getStringExtra("product")
            binding.companyText2.text = productKind

            list.add(productKind.toString())
        }

        //read product name from using1_page(edit_text value)
        if(intent.hasExtra("edit_text")){
            val productName = intent.getStringExtra("edit_text")

            var spannableString = SpannableString(productName)
            spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
            binding.productName.text =spannableString

            list.add(productName.toString())
        } else {
            val str = "제품 이름을 모릅니다."
            var spannableString = SpannableString(str)
            spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
            binding.productName.text = spannableString
        }

        /*check box check code(아직 임시임 button에 해당 site or skill no auto linked)*/
        if(intent.hasExtra("check_is")){
            val boxChecked = intent.getStringExtra("check_is")
            binding.boxText.text = boxChecked

            if(boxChecked == "체크박스 클릭상태"){
                binding.best1.text = "전화 상담"

                binding.best1.setOnClickListener { // is checked == best1 = ars
                    if(list[0] == "SAMSUNG"){
                        val shopPhone = "1588-3366"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "LG"){
                        val shopPhone = "1544-7777"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "쿠쿠"){
                        val shopPhone = "1588-8899"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "코웨이"){
                        val shopPhone = "1588-5200"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "필립스"){
                        val shopPhone = "080-600-6600"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "레노버"){
                        val shopPhone = "1670-0088"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "캐리어"){
                        val shopPhone = "1588-8866"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }

                }
                binding.boxText.setVisibility(View.INVISIBLE)

                binding.other2.text = "메일 문의"  //test code1

                binding.other2.setOnClickListener { //is checked but this is mail system

                    if(list[0] == "필립스"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("죄송합니다")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else if(list[0] == "레노버"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("죄송합니다")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else {
                        val intent = Intent(this,ComMailActivity::class.java)
                        val checkMail = "이것은" +list[0].toString() +"의" + list[1].toString()+"입니다." + "제품명은" + list[2].toString()+"입니다."
                        intent.putExtra("list",checkMail.toString())
                        intent.putExtra("companyName",list[0].toString())

                        startActivity(intent)
                    }
                }
            } else { // if none check = i don't know product name = best1 is mail system
                binding.best1.text = "메일 문의" //test code2

                binding.best1.setOnClickListener {
                    if(list[0] == "필립스"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("죄송합니다")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else if(list[0] == "레노버"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.error_check, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("죄송합니다")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.error_check_close_btn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else {
                        val noCheckMail = "이것은" + list[0].toString() + "의" + list[1].toString() + "입니다"
                        val intent = Intent(this,ComMailActivity::class.java)
                        intent.putExtra("list",noCheckMail.toString())
                        intent.putExtra("companyName",list[0].toString())
                        startActivity(intent)
                    }
                }

                binding.other2.text = "전화 상담"
                binding.other2.setOnClickListener {
                    if(list[0] == "SAMSUNG"){
                        val shopPhone = "1588-3366"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "LG"){
                        val shopPhone = "1544-7777"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "쿠쿠"){
                        val shopPhone = "1588-8899"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "코웨이"){
                        val shopPhone = "1588-5200"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "필립스"){
                        val shopPhone = "080-600-6600"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "레노버"){
                        val shopPhone = "1670-0088"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                    if(list[0] == "캐리어"){
                        val shopPhone = "1588-8866"
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$shopPhone")
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                //빠꾸
                finish()
                return true
            }
            R.id.help_sample_icon -> {
                //도우미
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.using_2page_question, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        도움말")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.question2_closeButton)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}