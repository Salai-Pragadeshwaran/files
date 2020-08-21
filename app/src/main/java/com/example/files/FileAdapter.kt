package com.example.files

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.file_item.view.*
import java.io.File

class FileAdapter(val files: ArrayList<FileName>, private val mcontext: Context):
RecyclerView.Adapter<FileAdapter.ViewHolder>(){


    class ViewHolder( itemView: View): RecyclerView.ViewHolder(itemView){
        internal var fileTextView: TextView
        internal var fileRecyclerView: RecyclerView
        internal var icon: ImageView
        init{

            fileTextView = itemView.fileName
            fileRecyclerView = itemView.filesRecycler2
            icon = itemView.expandIcon
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fileTextView.text = files[position].name
        if (files[position].file.listFiles()==null) {
            holder.icon.setImageResource(R.drawable.ic_outline_arrow_right_24)
        }
        holder.fileRecyclerView.setPadding(65, 0, 0, 0)
        holder.fileTextView.setOnClickListener {
            var files2 = ArrayList<FileName>()
            if (files[position].file.listFiles()!=null) {
                if (!files[position].isExpanded) {
                    files[position].isExpanded = true
                    holder.icon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    val list: Array<File> = files[position].file.listFiles()

                    for (i in list.indices) {
                        files2.add(FileName(name = list[i].name, file = list[i]))
                    }
                    setRecyclerView(holder.fileRecyclerView, files2)
                }
                else{
                    files[position].isExpanded = false
                    holder.icon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
                    files2.clear()
                    setRecyclerView(holder.fileRecyclerView, files2)
                }
            }
        }
        holder.icon.setOnClickListener{
            var files2 = ArrayList<FileName>()
            if (files[position].file.listFiles()!=null) {
                if (!files[position].isExpanded) {
                    files[position].isExpanded = true
                    holder.icon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    val list: Array<File> = files[position].file.listFiles()

                    for (i in list.indices) {
                        files2.add(FileName(name = list[i].name, file = list[i]))
                    }
                    setRecyclerView(holder.fileRecyclerView, files2)
                }
                else{
                    files[position].isExpanded = false
                    holder.icon.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
                    files2.clear()
                    setRecyclerView(holder.fileRecyclerView, files2)
                }
            }
        }
    }

    private fun setRecyclerView(recycler: RecyclerView, files2: ArrayList<FileName>) {
        //if (files2.size!=0) {
            var filesRecycler = recycler
            filesRecycler.layoutManager = LinearLayoutManager(mcontext)
            var FAdapter = FileAdapter(files2, mcontext)
            filesRecycler.adapter = FAdapter
       // }
    }


}