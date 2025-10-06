package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private var images: IntArray) : BaseAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any? {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val imageView: ImageView = convertView as? ImageView ?: ImageView(context)
        imageView.setImageResource(images[position])
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        val size = parent!!.width/4
        imageView.layoutParams = ViewGroup.LayoutParams(size, size)
        return imageView
    }

    fun updateImages(newImages: IntArray) {
        images = newImages
        notifyDataSetChanged()
    }
}