package com.example.newapp.login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newapp.Navigation.allScreen
import com.example.newapp.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun phoneNumberInputScreen(modifier: Modifier = Modifier , navController : NavHostController) {
    var phoneNumber by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    Scaffold {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(Color.White)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.splash_screen_logo)
                        , modifier = Modifier.size(150.dp)
                            .align(Alignment.CenterHorizontally), contentDescription = "")
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val newPhoneNumber = "+91"+phoneNumber
                            val options = PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(newPhoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(context as Activity)
                                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                                    }
                                    override fun onVerificationFailed(e: FirebaseException) {
                                        Toast.makeText(context, "Verification failed: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                                    override fun onCodeSent(verificationIdSent: String, token: PhoneAuthProvider.ForceResendingToken) {
                                        verificationId = verificationIdSent
                                        Toast.makeText(context, "OTP sent", Toast.LENGTH_SHORT).show()
                                    }
                                })
                                .build()
                            PhoneAuthProvider.verifyPhoneNumber(options)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Send OTP")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = otp,
                        onValueChange = { otp = it },
                        label = { Text("OTP") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val credential = PhoneAuthProvider.getCredential(verificationId, otp)
                            auth.signInWithCredential(credential)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "Verification successful", Toast.LENGTH_SHORT).show()
                                        navController.navigate(allScreen.HomePage){
                                            popUpTo(allScreen.PhoneNumberInputScreen){
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        Toast.makeText(context, "Verification failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verify OTP")
                    }
                }
            }

        }
    }
}
@Preview(showBackground = true , showSystemUi =  true )
@Composable
fun previewOtpScreen(modifier: Modifier = Modifier) {
    phoneNumberInputScreen(navController = NavHostController(LocalContext.current))
}