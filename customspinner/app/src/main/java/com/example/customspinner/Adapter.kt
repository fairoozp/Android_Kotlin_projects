package com.example.customspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Adapter(context: Context, var dataSource: List<Model>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (p1 == null) {
            view = inflater.inflate(R.layout.adpater_layout, p2, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = p1
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[p0].name

        return view
    }
    private class ItemHolder(view: View?) {
        val label: TextView = view!!.findViewById(R.id.label)
    }


//    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//
//        val view: View
//        val vh: ItemHolder
//        if (convertView == null) {
//            view = inflater.inflate(R.layout.adpater_layout, parent, false)
//            vh = ItemHolder(view)
//            view?.tag = vh
//        } else {
//            view = convertView
//            vh = view.tag as ItemHolder
//        }
//        vh.label?.text = dataSource.get(position).name
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any? {
//        return dataSource[position];
//    }
//
//    override fun getCount(): Int {
//        return dataSource.size;
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong();
//    }
//
//    private class ItemHolder(row: View?) {
//        val label: TextView?
//
//        init {
//            label = row?.findViewById(R.id.text) as TextView
//        }
//    }
}