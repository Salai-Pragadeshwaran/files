package com.example.files

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {
   // var text = ""
    lateinit var filesRecycler: RecyclerView
    var files = ArrayList<FileName>()
    val filesList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //check for storage permission
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                //permission already given
            }
            else -> {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
            }
        }
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED){
            val sd_card = Environment.getExternalStorageDirectory().toString()
            var file = File(sd_card)
            val list: Array<File> = file.listFiles()
            for (i in list.indices) {
                files.add(FileName(name = list[i].name, file = list[i]))
            }
            setRecyclerView(this)
        }
        //ListDir(file)
        //text1.text = text
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 101){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val sd_card = Environment.getExternalStorageDirectory().toString()
                var file = File(sd_card)
                val list: Array<File> = file.listFiles()
                for (i in list.indices) {
                    files.add(FileName(name = list[i].name, file = list[i]))
                }
                setRecyclerView(this)
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun setRecyclerView(mainActivity: MainActivity) {
        filesRecycler = findViewById(R.id.filesRecycler)
        filesRecycler.layoutManager = LinearLayoutManager(mainActivity)
        var FAdapter = FileAdapter(files, mainActivity)
        filesRecycler.adapter = FAdapter
    }

//    private fun ListDir(file: File) {
//        val list: Array<File> = file.listFiles()
//        for (i in list.indices) {
//            filesList.add(list[i].name)
//            text += "\n"
//            text += list[i].name
//            if(list[i].listFiles()!=null){
//                ListDir(list[i])
//            }
//        }
//    }
}