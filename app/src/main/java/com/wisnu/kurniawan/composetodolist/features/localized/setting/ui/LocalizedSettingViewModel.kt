package com.wisnu.kurniawan.composetodolist.features.localized.setting.ui

import androidx.lifecycle.viewModelScope
import com.wisnu.kurniawan.composetodolist.R
import com.wisnu.kurniawan.composetodolist.features.localized.base.ui.LocalizedEffect
import com.wisnu.kurniawan.composetodolist.features.localized.setting.data.ILocalizedSettingEnvironment
import com.wisnu.kurniawan.composetodolist.foundation.extension.update
import com.wisnu.kurniawan.composetodolist.foundation.viewmodel.StatefulViewModel
import com.wisnu.kurniawan.composetodolist.model.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalizedSettingViewModel @Inject constructor(localizedSettingEnvironment: ILocalizedSettingEnvironment) :
    StatefulViewModel<LocalizedSettingState, LocalizedEffect, LocalizedSettingAction, ILocalizedSettingEnvironment>(LocalizedSettingState(), localizedSettingEnvironment) {

    init {
        initLanguage()
    }

    override fun dispatch(action: LocalizedSettingAction) {
        when (action) {
            is LocalizedSettingAction.SelectLanguage -> {
                viewModelScope.launch {
                    environment.setLanguage(action.selected.language)
                }
            }
        }
    }

    private fun initLanguage() {
        viewModelScope.launch {
            environment.getLanguage()
                .flowOn(environment.dispatcher)
                .collect {
                    if (state.value.items.isNotEmpty()) {
                        setEffect(LocalizedEffect.ApplyLanguage(it))
                    }

                    setState { copy(items = initial().update(it)) }
                }
        }
    }

    private fun initial() = listOf(
        LanguageItem(
            title = R.string.setting_language_english,
            language = Language.ENGLISH,
            applied = false
        ),
        LanguageItem(
            title = R.string.setting_language_indonesia,
            language = Language.INDONESIA,
            applied = false
        ),
    )

}
