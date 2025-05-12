package com.jct.renthabesha.core.utils

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun uriToFile(uri: Uri): File? {
        return try {
            val fileExtension = context.contentResolver.getType(uri)?.split("/")?.last() ?: "jpg"
            val tempFile = File.createTempFile(
                "profile_${System.currentTimeMillis()}",
                ".$fileExtension",
                context.cacheDir
            )

            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                tempFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            tempFile
        } catch (e: Exception) {
            null
        }
    }

    fun uriToFileWithMime(uri: Uri): Pair<File, String>? {
        return try {
            val mimeType = context.contentResolver.getType(uri) ?: "image/jpeg"
            val file = uriToFile(uri) ?: return null
            Pair(file, mimeType)
        } catch (e: Exception) {
            null
        }
    }

    fun cleanupTempFiles() {
        context.cacheDir.listFiles()?.filter { it.name.startsWith("profile_") }?.forEach {
            it.delete()
        }
    }
}
