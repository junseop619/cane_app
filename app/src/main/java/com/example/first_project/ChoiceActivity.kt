package com.example.first_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.first_project.databinding.ActivityChoiceBinding
import org.w3c.dom.Text
import java.util.*

class ChoiceActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding : ActivityChoiceBinding
    private var tts : TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(this,this)


        binding.noneTrouble.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.pagerTest.setOnClickListener {
            startTTS2()
            val intent = Intent(this,PagerActivity::class.java)
            startActivity(intent)
        }


        binding.openVoice.setVisibility(View.INVISIBLE)
        binding.choiceVoice.setVisibility(View.INVISIBLE)


    }

    override fun onStart() {
        super.onStart()
        tts = TextToSpeech(this,this)
        startTTS()
    }

    //아무 화면이나 touch시 tts 음성이 흘러나옴
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                startTTS()
            }
        }
        return super.onTouchEvent(event)
    }

    //TTS 예제
    private fun startTTS() {
        val toSpeak = binding.openVoice.text.toString()
        tts!!.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    private fun startTTS2() {
        val checkSpeak = binding.choiceVoice.text.toString()
        tts!!.speak(checkSpeak, TextToSpeech.QUEUE_FLUSH, null,"")
    }


    // TextToSpeech override 함수
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.KOREA)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //doSomething
            } else {
                //doSomething
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