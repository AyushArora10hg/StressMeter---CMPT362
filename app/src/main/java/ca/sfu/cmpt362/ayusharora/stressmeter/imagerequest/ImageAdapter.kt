package ca.sfu.cmpt362.ayusharora.stressmeter.imagerequest

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

// Learnt the logic of creating own adapter class from tutorial provided in the assignment description.
// It takes an array of images and assigns it to a layout (eg. GridView)
// It overrides the four basic functions of the BaseAdapter class
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

    // This method creates or reuses an ImageView for each position in the GridView.
    // It sets the correct image from the images array and sizes it so that four images fit in one row.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val imageView: ImageView = convertView as? ImageView ?: ImageView(context)
        imageView.setImageResource(images[position])
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        val size = parent!!.width/4
        imageView.layoutParams = ViewGroup.LayoutParams(size, size)
        return imageView
    }

    // This helper method updates the value of the private images array yo newImages (provided by the
    // user class) to update the images displayed in the layout (eg. GridView)
    fun updateImages(newImages: IntArray) {
        images = newImages
        notifyDataSetChanged()
    }
}