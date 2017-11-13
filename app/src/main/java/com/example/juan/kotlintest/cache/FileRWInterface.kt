package com.example.juan.kotlintest.mcache

interface FileRWInterface {
  val wrapper: FileWrapperInterface
  fun read(): String
  fun write(wrappedFile: String)
  fun delete(): Boolean
  fun all(): List<String>
}