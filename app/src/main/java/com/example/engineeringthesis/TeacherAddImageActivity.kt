package com.example.engineeringthesis

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engineeringthesis.model.Category
import com.example.engineeringthesis.model.Word
import com.example.engineeringthesis.utils.GlobalValues
import com.example.engineeringthesis.viewmodel.CategoryTeacherViewModel
import com.example.engineeringthesis.viewmodel.CategoryViewModel
import com.example.engineeringthesis.viewmodel.ImageViewModel
import com.example.engineeringthesis.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_teacher_add_image.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*
import java.util.*


class TeacherAddImageActivity : AppCompatActivity() {
    private val PICK_IMAGE = 1
    lateinit var wordViewModel: WordViewModel
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var categoryTeacherViewModel: CategoryTeacherViewModel
    lateinit var imageViewModel:ImageViewModel
    var mutableArrayCategories: ArrayList<String>? = ArrayList()
    var mutableArrayWords: ArrayList<String>? = ArrayList()
    lateinit var dialog: Dialog
    lateinit var editTextSpinner: EditText
    lateinit var listViewSpinner: ListView
    var selectedCategorySpinner:String = ""
    var selectedWordSpinner:String = ""
    lateinit var selectedCategory: Category
    lateinit var selectedWord: Word
    lateinit var selectedImage: File
    var imageUri: Uri? = null
    var bitmap:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_add_image)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryTeacherViewModel = ViewModelProvider(this).get(CategoryTeacherViewModel::class.java)
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        // Category get
        categoryTeacherViewModel.getAllCategoriesByTeacher(GlobalValues.currentTeacher!!.teacherId).observe(this,
                { categories ->
                    categories.forEach {
                        //Toast.makeText(this@TeacherAddWordActivity, it.toString(), Toast.LENGTH_SHORT).show()
                        mutableArrayCategories!!.add(it.categoryId.categoryName)
                    }
                })

        teacher_spinner_category_list.setOnClickListener {
            dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_category_searchable_spinner)
            dialog.window!!.setLayout(650, 800)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val adap = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableArrayCategories!!)
            listViewSpinner = dialog.findViewById(R.id.dialog_listview_spinner)
            editTextSpinner = dialog.findViewById(R.id.dialog_edittext_spinner)
            listViewSpinner.adapter = adap
            editTextSpinner.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adap.filter.filter(s)
                }
            })
            listViewSpinner.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                    teacher_spinner_category_list.text = adap.getItem(position)
                    selectedCategorySpinner = teacher_spinner_category_list.text.toString()
                    Toast.makeText(this@TeacherAddImageActivity, "Kategoria:  $selectedCategorySpinner", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        }
            teacher_spinner_word_list.setOnClickListener {
                mutableArrayWords!!.clear()
                wordViewModel.getWordsFromCategory(selectedCategorySpinner, 0, Integer.MAX_VALUE).iterator()
                        .forEach {mutableArrayWords!!.add(it.wordName)}
                dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_word_searchable_spinner)
                dialog.window!!.setLayout(650, 800)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val adap = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableArrayWords!!)
                listViewSpinner = dialog.findViewById(R.id.dialog_word_listview_spinner)
                editTextSpinner = dialog.findViewById(R.id.dialog_word_edittext_spinner)
                listViewSpinner.adapter = adap
                editTextSpinner.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        adap.filter.filter(s)
                    }
                })
                listViewSpinner.onItemClickListener = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(adapter: AdapterView<*>?, v: View?, position: Int, id: Long) {
                        teacher_spinner_word_list.text = adap.getItem(position)
                        selectedWordSpinner = teacher_spinner_word_list.text.toString()
                        Toast.makeText(this@TeacherAddImageActivity, "Słowo:  $selectedWordSpinner", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
            }
        teacher_image_select_button.setOnClickListener { //create the intent for ImageGallery
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

        teacher_add_image_button.setOnClickListener {
            //selectedCategory = categoryViewModel.getCategory(selectedCategorySpinner)
            //selectedWord = wordViewModel.getWordByName(selectedWordSpinner)
            val file: File = File("/storage/emulated/0/DCIM/baseball.jpg")
            val requestFile: RequestBody = file.asRequestBody(contentResolver.getType(imageUri!!)!!.toMediaTypeOrNull())
            // MultipartBody.Part is used to send also the actual file name
            //val body: MultipartBody.Part = createFormData("picture", file.name, requestFile)
            imageViewModel.saveImage(requestFile)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data!!.data!!

            //Toast.makeText(this,"To jest imageUri: "+imageUri.toString(),Toast.LENGTH_SHORT).show()
            //Toast.makeText(this,"T o jest imageURiPath: "+imageUri!!.path,Toast.LENGTH_SHORT).show()

            if (Build.VERSION.SDK_INT >= 29) {
                val source: ImageDecoder.Source = ImageDecoder.createSource(applicationContext.contentResolver, imageUri!!)
                try {
                    bitmap = ImageDecoder.decodeBitmap(source)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, imageUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
           // Toast.makeText(this, "to jest bitmap: " + bitmap.toString(), Toast.LENGTH_SHORT).show()
            teacher_selected_image.setImageBitmap(bitmap)
        }
    }
}