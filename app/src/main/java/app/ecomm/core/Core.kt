package app.ecomm.core

import app.ecomm.util.NoArgSingletonHolder
import javax.inject.Singleton

@Singleton
class Core private constructor() {
    companion object : NoArgSingletonHolder<Core>(::Core)
}