package com.example.article_counting_app.presentation.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.article_counting_app.R

@Composable
fun CreateArticleScreen(
    onNavigateBack: () -> Unit,
    viewModel: CreateArticleViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.shouldNavigateBack) {
        if (state.shouldNavigateBack) {
            onNavigateBack()
            viewModel.onNavigatedBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.create_article_title),
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.article_name_label)) },
            singleLine = true,
            isError = state.nameError != null
        )
        state.nameError?.let { nameError ->
            Text(text = nameError, color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = state.number,
            onValueChange = viewModel::onNumberChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.article_number_label)) },
            singleLine = true,
            isError = state.numberError != null
        )
        state.numberError?.let { numberError ->
            Text(text = numberError, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = viewModel::onSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save))
        }
    }
}
