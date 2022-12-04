package com.example.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsUiModel(
    val id: String,
    val title: String?,
    val thumbnail: String?,
    val body: String?
) : UiModel, Parcelable