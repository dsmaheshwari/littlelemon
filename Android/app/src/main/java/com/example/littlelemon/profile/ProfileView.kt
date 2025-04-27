package com.example.littlelemon.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.composables.InputBox
import com.example.common.composables.InputRadioButton
import com.example.common.enums.Gender
import com.example.common.preferences.SharedPreferenceManager
import com.example.littlelemon.R
import com.example.littlelemon.framework.composable.FormLayout
import com.example.littlelemon.ui.theme.Button_Tertiary_Background
import com.example.littlelemon.ui.theme.Button_Tertiary_Text_Color

@Composable
fun ProfileView(navController: NavController) {


    FormLayout(
        heading = stringResource(R.string.personalInformation)
    ) {
        ProfileViewForm()
    }
}

@Composable
private fun ProfileViewForm() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        var firstName = SharedPreferenceManager.getValue("firstName", String::class.java, "")
        var lastName = SharedPreferenceManager.getValue("lastName", String::class.java, "")
        var email = SharedPreferenceManager.getValue("email", String::class.java, "")
        var phone= SharedPreferenceManager.getValue("phone", String::class.java, "")
        var gender= SharedPreferenceManager.getValue("gender", String::class.java, "")

        val genderList = Gender.entries.map { it -> it.toString() }

            Column {
                InputBox(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                    },
                   enabled = false,
                    label = stringResource(R.string.First_Name)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                    },
                   enabled = false,
                    label = stringResource(R.string.Last_Name)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                   enabled = false,
                    label = stringResource(R.string.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputBox(
                    value = phone,
                    onValueChange = {
                        phone = it
                    },
                   enabled = false,
                    label = stringResource(R.string.Phone)
                )
                Spacer(modifier = Modifier.height(16.dp))

                InputRadioButton(
                    label = stringResource(R.string.gender),
                    listOptions = genderList,
                    selection = gender,
                    onSelectionChanged = { gender = it},
                    enabled = false
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Row {
                Button(
                    onClick = {

                    },
                    colors = ButtonColors(
                        containerColor = Button_Tertiary_Background,
                        contentColor = Button_Tertiary_Text_Color,
                        disabledContainerColor = Button_Tertiary_Background.copy(alpha = 0.5F),
                        disabledContentColor = Button_Tertiary_Text_Color.copy(alpha = 0.5F)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.logout)
                    )
                }
            }
    }
}