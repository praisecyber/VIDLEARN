package com.vidlearn.data

import android.content.Context
import android.content.SharedPreferences

class VidlearnRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("vidlearn_prefs", Context.MODE_PRIVATE)

    var userName: String?
        get() = prefs.getString("user_name", null)
        set(value) {
            prefs.edit().putString("user_name", value).apply()
        }

    var userEmail: String?
        get() = prefs.getString("user_email", null)
        set(value) {
            prefs.edit().putString("user_email", value).apply()
        }

    fun getEnrolledCourseIds(): Set<Int> {
        val stringSet = prefs.getStringSet("enrolled_ids", emptySet()) ?: emptySet()
        return stringSet.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun enrollCourse(courseId: Int) {
        val set = getEnrolledCourseIds().map { it.toString() }.toMutableSet()
        set.add(courseId.toString())
        prefs.edit().putStringSet("enrolled_ids", set).apply()
    }

    fun getCompletedLessons(): Set<String> {
        return prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()
    }

    fun markLessonDone(key: String) {
        val set = getCompletedLessons().toMutableSet()
        set.add(key)
        prefs.edit().putStringSet("completed_lessons", set).apply()
    }

    fun getLessonCode(key: String, default: String): String {
        return prefs.getString("code_$key", default) ?: default
    }

    fun saveLessonCode(key: String, code: String) {
        prefs.edit().putString("code_$key", code).apply()
    }

    fun getLessonNote(key: String): String {
        return prefs.getString("note_$key", "") ?: ""
    }

    fun saveLessonNote(key: String, note: String) {
        prefs.edit().putString("note_$key", note).apply()
    }

    fun getFavoriteCourseIds(): Set<Int> {
        val stringSet = prefs.getStringSet("favorite_ids", emptySet()) ?: emptySet()
        return stringSet.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun toggleFavorite(courseId: Int): Boolean {
        val set = getFavoriteCourseIds().map { it.toString() }.toMutableSet()
        val isFav: Boolean
        if (set.contains(courseId.toString())) {
            set.remove(courseId.toString())
            isFav = false
        } else {
            set.add(courseId.toString())
            isFav = true
        }
        prefs.edit().putStringSet("favorite_ids", set).apply()
        return isFav
    }

    fun logout() {
        prefs.edit()
            .remove("user_name")
            .remove("user_email")
            .apply()
    }
}
