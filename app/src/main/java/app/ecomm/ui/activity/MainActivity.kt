package app.ecomm.ui.activity

import android.os.Bundle
import app.ecomm.R
import app.ecomm.core.di.Injectable
import app.ecomm.ui.fragment.DetailFragment
import app.ecomm.ui.fragment.MainFragment
import app.ecomm.util.transaction

class MainActivity : BaseActivity(), Injectable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchContentFragment()
    }

    internal fun launchProductDetailFragment(productId: Int) {
        supportFragmentManager.transaction {
            addToBackStack("")
            add(R.id.fragmentContainer, DetailFragment.newInstance(productId))
        }
    }

    internal fun launchSubCategoryFragment(catId: Int) {
        supportFragmentManager.transaction {
            addToBackStack("")
            add(R.id.fragmentContainer, MainFragment.newInstance(catId))
        }
    }

    private fun launchContentFragment() {
        supportFragmentManager.transaction {
            replace(R.id.fragmentContainer, MainFragment.newInstance(-1, "dummy"),
                    MainFragment::class.java.simpleName)
        }
    }
}
