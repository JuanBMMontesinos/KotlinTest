package com.example.juan.kotlintest.mcache

import java.io.File

class FileRW(override val wrapper: FileWrapperInterface) : FileRWInterface {

  override fun read(): String {
    val file = findFile()
    if (file.length() <= 0L)
      return ""
    return file.readText()
  }

  override fun write(wrappedFile: String) {
    synchronized(lock) {
      val file = findFile()
      file.writeText(wrappedFile)
    }
  }

  override fun delete(): Boolean {
    synchronized(lock) {
      val builder = wrapper.converter.builder
      val index = builder.index
      val classFolder = findClassFolder()
      return if (index.isEmpty()) {
        if (builder.cls == Cache::class.java) {
          return classFolder.parentFile?.deleteRecursively() == true
        } else {
          classFolder.deleteRecursively()
        }
      } else {
        classFolder.listFiles().first { it.name == index }.deleteRecursively()
      }
    }
  }

  override fun all(): List<String> {
    val classFolder = findClassFolder()
    return classFolder.listFiles().map { it.readText() }
  }

  private fun findFile(): File {
    val classFolder = findClassFolder()
    val classFile = File(classFolder, findFileName(classFolder))
    classFile.createNewFile()

    return classFile
  }

  private fun findClassFolder(): File {
    val builder = wrapper.converter.builder
    val folder = when (builder.mode) {
      CacheMode.CACHE -> Cache.context.cacheDir
      CacheMode.FILE -> Cache.context.filesDir
    }
    val name = builder.cls.simpleName

    val homeFolder = File(folder, "mCache")
    homeFolder.mkdirs()
    val classFolder = File(homeFolder, name.base64())
    classFolder.mkdirs()
    return classFolder
  }

  private fun findFileName(classFolder: File): String {
    removeUnwantedFiles(classFolder)

    val index = wrapper.converter.builder.index
    if (index.isNotEmpty())
      return index.toString()

    return "default".base64()
  }

  private fun removeUnwantedFiles(classFolder: File) {
    classFolder.listFiles().forEach {
      if (/*!it.name.isNumber() || */!it.isFile) {
        it.deleteRecursively()
      }
    }
  }

  companion object {
    private val lock: Any = Any()
  }
}