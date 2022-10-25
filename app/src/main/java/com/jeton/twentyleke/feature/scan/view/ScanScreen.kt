package com.jeton.twentyleke.feature.scan.view

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.jeton.twentyleke.core.ui.theme.TwentyLekeTheme
import com.jeton.twentyleke.feature.scan.viewmodel.ScanResult
import com.jeton.twentyleke.feature.scan.viewmodel.ScanViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(
    navigateToDetail: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val viewModel = getViewModel<ScanViewModel>()
    val scanResult = viewModel.scanResult.collectAsState()

    LaunchedEffect(cameraPermissionState) {
        // wip
        viewModel.onScannedValue("https://efiskalizimi-app.tatime.gov.al/invoice-check/#/verify?iic=F623DA5EB6D83ED32238A2332513C276&tin=M21314013L&crtd=2022-07-19T11:21:58+02:00&ord=939&bu=jq972gs580&cr=ok253eq083&sw=vn690dp449&prc=2100.00")

        if (!cameraPermissionState.hasPermission) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(scanResult.value) {
        if (scanResult.value is ScanResult.Success) {
            navigateToDetail()
        }
    }

    TwentyLekeTheme {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                ScanCamera(onScannedValue = { value ->
                    viewModel.onScannedValue(value)
                })

                when (scanResult.value) {
                    is ScanResult.Failure -> {
                        FailedScanDialog(onDismissRequest = { viewModel.reset() },
                            onConfirmButtonClick = { viewModel.reset() })
                    }
                    is ScanResult.Loading -> {
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                    is ScanResult.Initial -> {
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                                .padding(56.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            ScanningIndicator()
                        }
                    }
                    is ScanResult.Success -> {}
                }

                when (scanResult.value) {
                    is ScanResult.Failure -> {
                        val value = (scanResult.value as ScanResult.Failure).error
                        Text(text = value, color = Color.Magenta)
                    }
                    is ScanResult.Initial -> {
                        Text(text = "initial state", color = Color.Magenta)
                    }
                    is ScanResult.Loading -> {
                        Text(text = "loading state", color = Color.Magenta)
                    }
                    is ScanResult.Success -> {
                        val value = (scanResult.value as ScanResult.Success).data.toString()
                        Text(text = value, color = Color.Magenta)
                    }
                }
            }
        }
    }
}