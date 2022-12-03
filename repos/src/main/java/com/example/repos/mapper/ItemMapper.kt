package com.example.repos.mapper

import com.example.repos.models.ItemModel

interface ItemMapper<R, I : ItemModel> {
    fun mapFromItem(model: I): R
    fun mapToItem(model: R): I
}