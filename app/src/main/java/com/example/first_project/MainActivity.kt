package com.example.first_project

import android.content.Intent
import  android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

import com.example.first_project.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened,
            R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        navigationView = binding.mainDrawerView
        navigationView.setNavigationItemSelectedListener(this)


        //go to the using1_page(사용법 및 고장)
        binding.goUsing.setOnClickListener {
            val intent = Intent(this,UsingActivity::class.java)
            startActivity(intent)
        }

        //go to the kiosk1_page(키오스크)
        binding.goKiosk.setOnClickListener {
            val intent = Intent(this,KioskActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    //tool bar item choice
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // tool_bar to navigation_button
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId) {
            R.id.help_sample_icon -> {

                //tool_bar light_help_button
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.main_question, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        도움말")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.question_main_closeButton)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    //navigation item choice
    override fun onNavigationItemSelected(item:MenuItem):Boolean{
        when(item.itemId){
            R.id.menu_help ->{ //도움말(activity type, seeingAI navigaion 도움말 참고)
                val intent = Intent(this,HelperActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.menu_person ->{ //제작자 (dialog type)
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.menu_person, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        제작자")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.menu_person_close_btn)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }

            R.id.menu_feedback ->{ //피드백(dialog type)
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.menu_feedback, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("                        피드백")

                val mAlertDialog = mBuilder.show()

                val noButton = mDialogView.findViewById<Button>(R.id.menu_feedback_close_btn)
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                return true
            }
        }
        return false
    }



}