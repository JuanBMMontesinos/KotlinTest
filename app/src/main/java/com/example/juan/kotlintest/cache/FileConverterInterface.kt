package com.example.juan.kotlintest.mcache

interface FileConverterInterface<T> {
  val builder: FilePresenterBuilderInterface<T>
  var encodedFile: String
  val encodedFiles: List<String>
}