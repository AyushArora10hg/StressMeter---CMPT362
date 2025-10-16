package ca.sfu.cmpt362.ayusharora.stressmeter

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// Util class taken from XD's lecture demos. Then, code expanded with the help of chatGPT
// to ask permissions on all android devices.

object Util {

    private const val PERMISSION_REQUEST_CODE = 1001

    private fun isPermissionGranted(activity: Activity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            permission
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    fun checkPermissions(activity: Activity?) {
        if (Build.VERSION.SDK_INT < 23 || activity == null) return

        val toRequest = mutableListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!isPermissionGranted(activity, Manifest.permission.POST_NOTIFICATIONS)) {
                toRequest += Manifest.permission.POST_NOTIFICATIONS
            }
            if (!isPermissionGranted(activity, Manifest.permission.READ_MEDIA_AUDIO)) {
                toRequest += Manifest.permission.READ_MEDIA_AUDIO
            }
        } else {

            if (!isPermissionGranted(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                toRequest += Manifest.permission.READ_EXTERNAL_STORAGE
            }
        }

        if (toRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                toRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }
}
