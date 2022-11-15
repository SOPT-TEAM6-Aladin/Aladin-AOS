package com.sopt.aladinaos.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
) : LocalDataSource
