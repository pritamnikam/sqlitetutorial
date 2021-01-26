package edu.tutorials.sqlitetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import edu.tutorials.sqlitetutorial.databinding.ActivityMainBinding


/**
 * Application Name: SQLiteTutorial
 * Company name: edu.tutorials.sqlitetutorial
 * Minimum SDK:	API 21: Android 5.0 (Lollipop)
 * Activity: Empty Activity
 *
 */

class MainActivity : AppCompatActivity() {

    lateinit var usersDBHelper : UsersDBHelper
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        usersDBHelper = UsersDBHelper(this)
    }

    fun addUser(v: View){
        var userid = binding.edittextUserid.text.toString()
        var name = binding.edittextName.text.toString()
        var age = binding.edittextAge.text.toString()
        var result = usersDBHelper.insertUser(UserModel(userid = userid,name = name,age = age))

        //clear all edittext s
        binding.edittextAge.setText("")
        binding.edittextName.setText("")
        binding.edittextUserid.setText("")
        binding.textviewResult.text = "Added user : "+result
        binding.llEntries.removeAllViews()
    }

    fun deleteUser(v:View){
        var userid = binding.edittextUserid.text.toString()
        val result = usersDBHelper.deleteUser(userid)
        binding.textviewResult.text = "Deleted user : "+result
        binding.llEntries.removeAllViews()
    }

    fun showAllUsers(v:View){
        var users = usersDBHelper.readAllUsers()
        binding.llEntries.removeAllViews()
        users.forEach {
            var tv_user = TextView(this)
            tv_user.textSize = 30F
            tv_user.text = it.name.toString() + " - " + it.age.toString()
            binding.llEntries.addView(tv_user)
        }
        binding.textviewResult.text = "Fetched " + users.size + " users"
    }
}