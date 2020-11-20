package com.example.fetchrewardscodingexercise.presentation.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.fetchrewardscodingexercise.R


fun makeDialog(context: Context): AlertDialog.Builder =
   AlertDialog.Builder(context, R.style.Theme_AppCompat_Dialog_Alert)