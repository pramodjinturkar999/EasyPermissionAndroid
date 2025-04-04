package com.pramod.easypermissionandroid

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


object EasyPermissionManager {

    // Generic function to request a single runtime permission
    fun requestPermission(
        activity: AppCompatActivity,
        permission: String,
        callback: (Boolean) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            callback(true) // Permission already granted
        } else {
            activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                callback(isGranted)
            }.launch(permission)
        }
    }

    // Generic function to request multiple runtime permissions at once
    fun requestMultiplePermissions(
        activity: AppCompatActivity,
        permissions: Array<String>,
        callback: (Map<String, Boolean>) -> Unit
    ) {
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            callback(results)
        }.launch(permissions)
    }

    // Individual permission request methods (Only Dangerous Permissions)
    fun requestCamera(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.CAMERA, callback)
    }

    fun requestStorage(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // Request READ and WRITE permissions for Android 10 and below
            requestMultiplePermissions(activity, arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )) { result ->
                val allGranted = result.values.all { it }
                callback(allGranted)
            }
        } else {
            // For Android 11+, MANAGE_EXTERNAL_STORAGE is not a normal runtime permission
            val isGranted = Environment.isExternalStorageManager()
            if (isGranted) {
                callback(true)
            } else {
                // Direct user to settings to enable MANAGE_EXTERNAL_STORAGE
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:${activity.packageName}")
                activity.startActivity(intent)
                callback(false) // Permission is not granted yet
            }
        }
    }



    fun requestMicrophone(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.RECORD_AUDIO, callback)
    }

    fun requestLocation(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, callback)
    }

    fun requestBluetooth(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermission(activity, Manifest.permission.BLUETOOTH_CONNECT, callback)
        }
    }

    fun requestCallPhone(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.CALL_PHONE, callback)
    }

    fun requestReadCalendar(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.READ_CALENDAR, callback)
    }

    fun requestReadContacts(activity: AppCompatActivity, callback: (Boolean) -> Unit) {
        requestPermission(activity, Manifest.permission.READ_CONTACTS, callback)
    }
}

