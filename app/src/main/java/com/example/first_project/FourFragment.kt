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
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_project.databinding.FragmentFourBinding
import com.example.first_project.databinding.FragmentOneBinding
import com.example.first_project.databinding.FragmentThreeBinding
import com.example.first_project.databinding.ItemRecyclerviewBinding
import java.util.*

class FourFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding : FragmentFourBinding
    private var tts : TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFourBinding.inflate(inflater,container,false)

        tts = TextToSpeech(activity,this)




        binding.fragment4Btn.setOnClickListener {
            startTTS()
            val shopPhone = "1588-5200"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$shopPhone")
            startActivity(intent)


        }

        return binding.root

    }


    private fun startTTS() {
        val str = "코웨이 에이알에스연결하기"  //mention
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
                val str = "코웨이 에이알에스연결하기"  //mention
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


