package com.example.first_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.first_project.databinding.ActivityUsingBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import java.util.logging.Logger.global


class UsingActivity : AppCompatActivity() {

    lateinit var binding: ActivityUsingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUsingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_sample)


        //product company(제조회사) (first spinner)
        val data: Array<String> = resources.getStringArray(R.array.company)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        binding.firstSpinner.adapter = adapter
        binding.firstSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position != 0) {
                    Toast.makeText(this@UsingActivity, data[position], Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        //product kind(제품종류) (second spinner)
        val data2: Array<String> = resources.getStringArray(R.array.product)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, data2)
        binding.secondSpinner.adapter = adapter2
        binding.firstSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position != 0) {
                    Toast.makeText(this@UsingActivity, data2[position], Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // edit_text enable or unable (check_box)
        binding.et1.isEnabled = false
        binding.ed1.isEnabled = false

        val str1 = "체크박스 클릭상태"
        val str2 = "체크박스 미사용상태"

        binding.cb1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){  //check box is checked
                val intent = Intent(this,UsingActivityTwo::class.java)
                intent.putExtra("check_is",str1.toString())

                binding.et1.isEnabled = true
                binding.ed1.isEnabled = true

                binding.ed1.text = binding.et1.text
                val product = binding.et1.text


                //go to the using2_page (if check box is checked)
                // 제품이름을 알고 있으므로 edit_text(et1)value를 using2_page로 보냄
                binding.usingNextButton.setOnClickListener {
                    val choice = binding.firstSpinner.selectedItem
                    val choice2 = binding.secondSpinner.selectedItem

                    if(choice.toString() == "선택하세요"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.using_error, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("                          알림")


                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.useError_closeBtn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else {
                        intent.putExtra("company",choice.toString())
                        intent.putExtra("product",choice2.toString())
                        intent.putExtra("edit_text",product.toString())
                        startActivity(intent)
                    }
                }

            } else { //check box is unchecked
                val intent = Intent(this,UsingActivityTwo::class.java)
                intent.putExtra("check_is",str2.toString())

                binding.et1.isEnabled = false
                binding.ed1.isEnabled = false

                binding.usingNextButton.setOnClickListener {
                    val choice = binding.firstSpinner.selectedItem
                    val choice2 = binding.secondSpinner.selectedItem

                    if(choice.toString() == "선택하세요"){
                        val mDialogView = LayoutInflater.from(this).inflate(R.layout.using_error, null)
                        val mBuilder = AlertDialog.Builder(this)
                            .setView(mDialogView)
                            .setTitle("                          알림")

                        val mAlertDialog = mBuilder.show()

                        val noButton = mDialogView.findViewById<Button>(R.id.useError_closeBtn)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    } else {
                        intent.putExtra("company",choice.toString())
                        intent.putExtra("product",choice2.toString())
                        startActivity(intent)
                    }
                }
            }
        }


        //go to the using2_page (if check box is unchecked)
        binding.usingNextButton.setOnClickListener {

            val intent = Intent(this,UsingActivityTwo::class.java)
            val choice = binding.firstSpinner.selectedItem
            val choice2 = binding.secondSpinner.selectedItem

            if(choice.toString() == "선택하세요"){
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.using_error, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                          알림")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.useError_closeBtn)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            } else {
                intent.putExtra("company",choice.toString())
                intent.putExtra("product",choice2.toString())
                intent.putExtra("check_is",str2.toString())
                startActivity(intent)
            }
        }

        binding.et1.setOnKeyListener { view, keyCode, event ->
            // Enter Key Action
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                // 키패드 내리기
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.et1.windowToken, 0)
                true
            }
            false
        }


    }

    //keyboard jot bug touch event clear
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView: View? = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                //back
                finish()
                return true
            }
            R.id.help_sample_icon -> {
                //helper
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.using_1page_question, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        도움말")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.question1_closeButton)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }












}