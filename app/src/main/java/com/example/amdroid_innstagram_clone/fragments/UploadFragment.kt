package com.example.amdroid_innstagram_clone.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amdroid_innstagram_clone.R
import com.example.amdroid_innstagram_clone.adapter.HomeAdapter
import com.example.amdroid_innstagram_clone.model.Post
import com.example.amdroid_innstagram_clone.utils.Logger
import com.example.amdroid_innstagram_clone.utils.Utils
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter


class UploadFragment : BaseFragment() {

    val TAG = UploadFragment::class.java.simpleName
    private var listener:UploadListener? = null

    lateinit var fl_photo: FrameLayout
    lateinit var iv_photo: ImageView
    lateinit var et_caption: EditText

    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload, container, false)
        initViews(view)
        return view
    }
    /*
    onAttach is for communication of Fragments
     */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is UploadListener){
            context
        }else{
            throw RuntimeException("$context must implement UploadListener")
        }
    }
    /*
  onAttach is for communication of Fragments
   */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    private fun initViews(view: View) {
        val fl_view = view.findViewById<FrameLayout>(R.id.fl_view)
        setViewHeight(fl_view)

        et_caption = view.findViewById(R.id.et_caption)
        fl_photo = view.findViewById(R.id.fl_photo)
        iv_photo = view.findViewById(R.id.iv_photo)

        val iv_close = view.findViewById<ImageView>(R.id.iv_close)
        val iv_pick = view.findViewById<ImageView>(R.id.iv_pick)
        val iv_upload = view.findViewById<ImageView>(R.id.iv_upload)

        iv_pick.setOnClickListener {
            pickFishBunPhoto()
        }

        iv_close.setOnClickListener {
            hidePickedPhoto()
        }

        iv_upload.setOnClickListener {
            uploadNewPost()
        }
    }




    /*
    /set view height as screen width
     */
    private fun setViewHeight(view: View) {
        val params: ViewGroup.LayoutParams = view.layoutParams
        params.height = Utils.screenSize(requireActivity().application).width
        view.layoutParams = params
    }

    /*
    pick photo using fishBun
     */

    private fun pickFishBunPhoto(){
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }

    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                allPhotos =
                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH)?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
                showPickedPhoto()
            }
        }

    private fun showPickedPhoto() {
        fl_photo.visibility = View.VISIBLE
        iv_photo.setImageURI(pickedPhoto)
    }

    private fun uploadNewPost() {
        listener!!.scrollToHome()
        val caption = et_caption.text.toString().trim()
        if (caption.isNotEmpty()&&pickedPhoto != null){
            Logger.d(TAG,caption)
            Logger.d(TAG,pickedPhoto!!.path.toString())
            resetAll()
        }
    }

    private fun resetAll() {
        et_caption.text.clear()
        pickedPhoto = null
        fl_photo.visibility = View.GONE
    }

    private fun hidePickedPhoto() {
        pickedPhoto = null
        fl_photo.visibility = View.GONE
    }

    /*
    /this Interface is created for communication with HomeFragmant
     */

    interface UploadListener{
        fun scrollToHome()
    }
}