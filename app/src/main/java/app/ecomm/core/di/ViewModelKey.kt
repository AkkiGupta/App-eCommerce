package com.airtel.vision.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/** Key to annotate [ViewModel]s */

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
