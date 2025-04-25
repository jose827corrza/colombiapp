package com.josedev.colombiapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.josedev.colombiapp.components.NavigationComp
import com.josedev.colombiapp.components.PermissionDialog
import com.josedev.colombiapp.presentation.PermissionViewModel
import com.josedev.colombiapp.ui.theme.ColombiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val POST_NOTIFICATION_REQUEST_CODE = 100
    @RequiresApi(35)
    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseAnalytics = Firebase.analytics
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<PermissionViewModel>()
        val dialogQueue = viewModel.visiblePermissionDialogQueue


        enableEdgeToEdge()
//        requestCameraPermission()

        setContent {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_DENIED){
                Log.d("MainActivity", "Permission denied")
            }
        val postNotifPermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    viewModel.onPermissionResult(
                        permission = Manifest.permission.POST_NOTIFICATIONS,
                        isGranted = isGranted
                    )
                }
            }
        )
            ColombiAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Green
                ){
                    Button(onClick = {
                        postNotifPermissionResultLauncher.launch(
                            Manifest.permission.POST_NOTIFICATIONS
                        )
                    }) {
                        Text(text = " Request permissions")
                    }
                    dialogQueue.reversed().forEach { permission ->
                        PermissionDialog(
                            permissionsProvider = when (permission) {
                                Manifest.permission.POST_NOTIFICATIONS -> {
                                    com.josedev.colombiapp.permissions.PostNotificationsPermissionsProvider()
                                }

                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(permission),
                            onDismiss = viewModel::dismissDialog,
                            onOkClick = {
                                viewModel.dismissDialog()
//                                postNotifPermissionResultLauncher.launch(permission)
                            },
//                            onGoToAppSettingsClick = ::openASettings

                        )
                    }
                    NavigationComp(firebaseAnalytics)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        Log.d("MainActivity", "Permission Requested")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when (requestCode) {
            POST_NOTIFICATION_REQUEST_CODE -> {
                Log.d("MainActivity", "Permission granted")
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContent {
                        ColombiAppTheme {
                            Surface {
//                                NavigationComp(firebaseAnalytics)
                                Text(text = "soliicuta permiso")
                            }
                        }
                    }
                } else {
                    Log.d("MainActivity", "Permission else")
                    setContent {
                        ColombiAppTheme {
                            Surface {
                                NavigationComp(firebaseAnalytics)
                            }
                        }
                    }
                }
            }
        }
    }
    fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            POST_NOTIFICATION_REQUEST_CODE
        )
    }
}

fun Activity.openASettings(){
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
        .also(::startActivity)
}
