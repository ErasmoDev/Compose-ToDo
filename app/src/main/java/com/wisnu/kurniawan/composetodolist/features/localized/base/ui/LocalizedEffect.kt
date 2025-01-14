package com.wisnu.kurniawan.composetodolist.features.localized.base.ui

import com.wisnu.kurniawan.composetodolist.model.Language

sealed class LocalizedEffect {
    data class ApplyLanguage(val language: Language) : LocalizedEffect()
}
