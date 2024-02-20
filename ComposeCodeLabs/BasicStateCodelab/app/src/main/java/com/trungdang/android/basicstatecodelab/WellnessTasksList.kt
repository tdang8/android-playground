package com.trungdang.android.basicstatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(modifier = modifier, state = listState) {
        items(
            items = list,
            key = { task ->
                task.id
            }) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked.value,
                onCheckedChange =  { checked ->
                    onCheckedTask(task, checked)
                },
                onClose = {
                    onCloseTask(task)
                }
            )
        }
    }

}