package com.example.first_project

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.databinding.FragmentFiveBinding
import com.example.first_project.databinding.FragmentFourBinding
import com.example.first_project.databinding.FragmentOneBinding
import com.example.first_project.databinding.FragmentSevenBinding
import com.example.first_project.databinding.FragmentSixBinding
import com.example.first_project.databinding.FragmentThreeBinding
import com.example.first_project.databinding.ItemRecyclerviewBinding
import java.util.*

class SevenFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding : FragmentSevenBinding
    private var tts : TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSevenBinding.inflate(inflater,container,false)

        tts = TextToSpeech(activity,this)


        binding.fragment7Btn.setOnClickListener {
            startTTS()
            val shopPhone = "1588-8866"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$shopPhone")
            startActivity(intent)
        }

        return binding.root

    }

    private fun startTTS() {
        val str = "캐리어 에이알에스 연결하기"  //mention
        val toSpeak = str.toString()
        tts!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"")

    }
    private fun startTTS2() {
        val str = "캐리어 에이알에스 연결하기 해당화면이 마지막입니다.  다시 보기를 원하시면 왼쪽으로 스와이프 하세요"  //mention
        val toSpeak = str.toString()
        tts!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"")

    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.KOREA)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //doSomething
            } else {
                val str = "캐리어 에이알에스 연결하기 해당화면이 마지막입니다.  다시 보기를 원하시면 왼쪽으로 스와이프 하세요"  //mention
                val toSpeak = str.toString()
                tts!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"")
            }
        } else {
            //doSomething
        }

    }


    override fun onDestroy() {

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }
}


