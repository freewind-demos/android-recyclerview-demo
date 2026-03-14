# android-recyclerview-demo

## 简介

本 demo 展示了如何在 Android 应用中使用 RecyclerView 控件来显示网格布局列表。RecyclerView 是 Android 中 ListView 的增强版本，提供了更好的性能和更灵活的布局方式。

## 基本原理

RecyclerView 采用了视图回收和复用机制：
- **ViewHolder 模式**：缓存视图对象，避免频繁创建
- **LayoutManager**：管理子视图的布局方式（线性、网格、瀑布流）
- **ItemDecoration**：添加分隔线、边距等装饰
- **ItemAnimator**：管理列表项的动画效果

RecyclerView 比 ListView 性能更好，因为它只创建可见区域的视图，并回收超出屏幕的视图进行复用。

## 启动和使用

### 环境要求
- Android Studio Arctic Fox 或更高版本
- JDK 11 或更高版本
- Android SDK 34

### 安装和运行
1. 使用 Android Studio 打开本项目
2. 连接 Android 设备或启动模拟器
3. 点击 Run 按钮运行应用

## 教程

### 什么是 RecyclerView？

RecyclerView 是 Android Jetpack 组件之一，用于显示大量数据集合。它比 ListView 更灵活、性能更好，支持多种布局方式。

### 核心组件

1. **RecyclerView**：列表视图容器
2. **LayoutManager**：布局管理器，控制子视图的排列方式
3. **Adapter**：数据适配器，负责提供列表项数据
4. **ViewHolder**：视图持有者，缓存视图提高性能

### 使用步骤

1. 添加依赖：
```kotlin
implementation 'androidx.recyclerview:recyclerview:1.3.2'
```

2. 在布局中添加 RecyclerView：
```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

3. 创建布局管理器：
```kotlin
layoutManager = GridLayoutManager(this, 2)  // 2列网格布局
```

4. 创建适配器：
```kotlin
adapter = MyAdapter(dataList) { item ->
    // 处理点击事件
}
```

### 布局管理器类型

- **LinearLayoutManager**：线性布局（垂直或水平）
- **GridLayoutManager**：网格布局
- **StaggeredGridLayoutManager**：瀑布流布局

### ViewHolder 模式

```kotlin
class MyAdapter(
    private val data: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.View.inflate(
            parent.context,
            android.R.layout.simple_list_item_1,
            null
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int = data.size
}
```

### 注意事项

1. **必须实现 ViewHolder**：RecyclerView.ViewHolder 是必须的
2. **调用 notifyDataSetChanged()**：数据更新后需要通知适配器
3. **设置 LayoutManager**：否则不会显示任何内容
4. **性能优化**：使用 DiffUtil 进行增量更新，避免整体刷新
