package app.ecomm.data.api

/**
 * Annotation to specify if a Retrofit request needs to be authenticated.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequestPolicy(val authenticated: Boolean = false)
