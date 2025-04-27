package com.example.littlelemon.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.common.composables.InputBox
import com.example.common.composables.InputRadioButton
import com.example.common.enums.Gender
import com.example.common.preferences.SharedPreferenceManager
import com.example.littlelemon.R
import com.example.littlelemon.framework.composable.FormLayout
import com.example.littlelemon.ui.theme.*
import com.example.navigations.HomeScreen
import kotlinx.coroutines.delay

private lateinit var header:String
private lateinit var subHeader:String

@Composable
fun OnBoardingView(navController: NavHostController) {
    header = stringResource(R.string.lets_get_to_Know_you)
    subHeader = stringResource(R.string.personal_information)

    FormLayout(
        heading = header,
        subHeading = subHeader
    ) {
        OnBoardingViewForm(navController)
    }
}

@Composable
private fun OnBoardingViewForm(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        var firstName by rememberSaveable { mutableStateOf("") }
        var lastName by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var gender by rememberSaveable { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        var isSuccess by remember { mutableStateOf(false) }

        header = stringResource(R.string.lets_get_to_Know_you)
        subHeader = stringResource(R.string.personal_information)

        val genderList = Gender.entries.map { it -> it.toString() }

        if(!isSuccess) {
            if(!message.isNullOrBlank()) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.labelLarge,
                    color = Alert
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Column {
                InputBox(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    label = stringResource(R.string.First_Name)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    label = stringResource(R.string.Last_Name)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    label = stringResource(R.string.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = phone,
                    onValueChange = {
                        phone = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    label = stringResource(R.string.Phone)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputRadioButton(
                    label = stringResource(R.string.gender),
                    listOptions = genderList,
                    selection = gender,
                    onSelectionChanged = { gender = it}
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Row {
                Button(
                    onClick = {
                        if(firstName.isNullOrBlank() || lastName.isNullOrBlank() || email.isNullOrBlank() || phone.isNullOrBlank()) {
                            message = "Please enter all data!"
                        } else {
                            SharedPreferenceManager.set("firstName", firstName)
                            SharedPreferenceManager.set("lastName", lastName)
                            SharedPreferenceManager.set("email", email)
                            SharedPreferenceManager.set("phone", phone)
                            SharedPreferenceManager.set("isRegistered", true)
                            isSuccess = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.register)
                    )
                }
            }
        } else {
            var timer by remember { mutableIntStateOf(3) }
            message = "Registration successful! You will be redirected in $timer sec"

            LaunchedEffect(Unit) {
                for(i in (5 downTo 1)) {
                    timer -= 1
                    delay(1000)
                }
            }

            if(timer != 0) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.displayLarge,
                    color = Success
                )
            } else {
                navController.navigate(HomeScreen.route)
            }
        }
    }
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun OnBoardingPreview() {
//    OnBoardingView(navController)
//}