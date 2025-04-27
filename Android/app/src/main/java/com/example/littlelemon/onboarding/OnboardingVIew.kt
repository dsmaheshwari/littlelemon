package com.example.littlelemon.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.composables.InputBox
import com.example.common.composables.InputRadioButton
import com.example.common.enums.Gender
import com.example.littlelemon.R
import com.example.littlelemon.framework.composable.FormLayout
import com.example.littlelemon.ui.theme.*

@Composable
fun OnBoardingView() {
    FormLayout(
        heading = stringResource(R.string.lets_get_to_Know_you),
        subHeading = stringResource(R.string.personal_information)
    ) {
        OnBoardingViewForm()
    }
}

@Composable
private fun OnBoardingViewForm() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var firstName by rememberSaveable { mutableStateOf("") }
        var lastName by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var gender by rememberSaveable { mutableStateOf("") }
        var age by rememberSaveable { mutableStateOf("") }

        val genderList = Gender.entries.map { it -> it.toString() }

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
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.register)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun OnBoardingPreview() {
    OnBoardingView()
}