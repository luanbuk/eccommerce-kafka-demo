package br.com.ecommerce.arch

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE

@Target(CLASS, TYPE)
@Retention(RUNTIME)
@MustBeDocumented
annotation class NoArgs