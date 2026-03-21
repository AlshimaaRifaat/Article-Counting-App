package com.example.article_counting_app.presentation.count

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
fun CountArticleScreen(
    articleId: String,
    onNavigateBack: () -> Unit
) {
    val vm: CountArticleViewModel = viewModel(
        factory = CountArticleViewModelFactory(articleId)
    )
    val state by vm.uiState.collectAsState()

    LaunchedEffect(state.shouldNavigateBack) {
        if (state.shouldNavigateBack) {
            onNavigateBack()
            vm.onNavigatedBack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = state.countInput,
            onValueChange = vm::onCountChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.count_input_label)) },
            singleLine = true,
            isError = state.countError != null
        )
        state.countError?.let { countError ->
            Text(text = countError, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = vm::onSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save))
        }
    }
}
