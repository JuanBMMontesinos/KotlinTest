package com.example.juan.kotlintest.mcache

interface FilePresenterBuilderInterface<T> {

  var file: T?
  var files: List<T>
  var cls: Class<T>
  var mode: CacheMode
  var index: CharSequence
}