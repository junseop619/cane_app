package com.example.first_project

import android.content.ClipData
import android.content.ClipboardManager
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
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.first_project.databinding.ActivityComMailBinding

class ComMailActivity : AppCompatActivity() {

    private lateinit var mManager : ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityComMailBinding.inflate(layoutInflater)

        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_sample)


        if(intent.hasExtra("list")) {
            val lastText = intent.getStringExtra("list")
            val clip = ClipData.newPlainText("label",lastText)

            var spannableString = SpannableString(lastText)
            spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
            binding.mailMessage.text =spannableString


            binding.copy.setOnClickListener {
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this,"클립보드에 복사되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }
        if(intent.hasExtra("companyName")){
            val name = intent.getStringExtra("companyName")
            when(name) {
                "SAMSUNG" -> {
                    binding.goToMail.setOnClickListener {
                        Log.d("test log", "this is samsung") /*check code*/
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.samsungsvc.co.kr/login?returnURL=https%253A%252F%252Fwww.samsungsvc.co.kr%252Fconsult%252Femail&type=AE"))
                        startActivity(intent)
                    }
                }

                "LG" -> {
                    binding.goToMail.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lge.co.kr/support/email-inquiry"))
                        startActivity(intent)
                    }
                }

                "쿠쿠" -> {
                    binding.goToMail.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cuckoo.co.kr/mycuckoo/qnaWrite"))
                        startActivity(intent)
                    }
                }

                "코웨이" -> {
                    binding.goToMail.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coway.com/cs/contact/write"))
                        startActivity(intent)
                    }
                }

                "캐리어" -> {
                    binding.goToMail.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.carriermall.co.kr/board/write?id=mbqna"))
                        startActivity(intent)
                    }
                }


                else -> {
                    binding.goToMail.setOnClickListener {
                        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
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
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.com_mail_question, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        도움말")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.mail_closeButton)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}