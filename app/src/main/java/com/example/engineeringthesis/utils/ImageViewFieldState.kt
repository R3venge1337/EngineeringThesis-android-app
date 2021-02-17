package com.example.engineeringthesis.utils

import android.widget.ImageView
import com.example.engineeringthesis.model.Word

data class ImageViewFieldState(val idButton: ImageView, var isWordImage: Boolean = false, var Word: Word,var isFlipped: Boolean = false, var isMatched: Boolean = false)
