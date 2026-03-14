package com.example.demo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView 示例程序
 *
 * 展示如何在 Android 中使用 RecyclerView 控件显示网格布局列表
 * RecyclerView 是 ListView 的增强版，提供了更好的性能和灵活性
 */
class MainActivity : AppCompatActivity() {

    // 定义要显示的数据列表
    private val dataList = listOf(
        "苹果", "香蕉", "橙子", "葡萄", "西瓜",
        "草莓", "芒果", "菠萝", "猕猴桃", "石榴",
        "樱桃", "桃子", "梨", "柚子", "火龙果"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建根布局 - 使用 ConstraintLayout
        val rootLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        // 创建 RecyclerView 控件
        val recyclerView = RecyclerView(this).apply {
            id = View.generateViewId()
        }

        // 设置布局管理器为网格布局（2列）
        layoutManager = GridLayoutManager(this, 2)

        // 创建并设置适配器
        adapter = MyAdapter(dataList) { item ->
            // 处理点击事件
            Toast.makeText(
                this@MainActivity,
                "点击了: $item",
                Toast.LENGTH_SHORT
            ).show()
        }

        // 将 RecyclerView 添加到根布局
        rootLayout.addView(recyclerView)

        // 设置布局参数
        recyclerView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        ).apply {
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
            rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            topMargin = 16
            bottomMargin = 16
            leftMargin = 16
            rightMargin = 16
        }

        // 设置内容视图
        setContentView(rootLayout)
    }
}

/**
 * 自定义 RecyclerView 适配器
 */
class MyAdapter(
    private val data: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // ViewHolder 类，用于缓存视图
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }

    // 创建视图Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 创建列表项视图
        val view = android.view.View.inflate(
            parent.context,
            android.R.layout.simple_list_item_1,
            null
        )
        return ViewHolder(view)
    }

    // 绑定数据到视图
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item

        // 设置点击监听器
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    // 返回数据项数量
    override fun getItemCount(): Int = data.size
}
