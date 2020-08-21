package com.example.files

import java.io.File

data class FileName(
    var name: String ,
    var isExpanded: Boolean = false,
    var file: File
) {
}