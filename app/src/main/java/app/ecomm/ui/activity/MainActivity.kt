package app.ecomm.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import app.ecomm.R
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.Ranking
import app.ecomm.databinding.ActivityMainBinding
import app.ecomm.ui.di.Injectable
import app.ecomm.ui.fragment.DetailFragment
import app.ecomm.ui.fragment.MainFragment
import app.ecomm.ui.fragment.RankFragment
import app.ecomm.util.transaction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), Injectable {

    // Trick to avoid auto selection of spinner
    var firstLaunch: Int = 2

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        setContentView(rootView)
        setBackStackListener()
        binding = DataBindingUtil.bind(rootView)
        binding?.showFilter = true
        launchContentFragment()
    }

    internal fun setUpCategorySpinner(listCategories: List<Categories>){
        val listCat: MutableList<String> = listCategories.map { it.name }.toMutableList()
        listCat.add(0, "All")
        val catAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listCat)
        catAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked)
        btnCategories.adapter = catAdapter


        btnCategories.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position > 0){
                    launchSubCategoryFragment(listCategories[position - 1].id)
                }
            }
        }
    }

    internal fun setUpRankingSpinner(listRanking: List<Ranking>){
        val listRank: MutableList<String> = listRanking.map { it.name }.toMutableList()
        listRank.add(0, "Rank Wise")
        val rankAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRank)
        rankAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked)
        btnRanking.adapter = rankAdapter

        btnRanking.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(firstLaunch-- > 0)
                {
                    return
                } else {
                    if(position > 0){
                        launchRankWiseFragment(listRanking[position-1].name)
                    } else {
                        launchRankWiseFragment("All")
                    }
                }
            }
        }
    }

    private fun setBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val detailFragment = supportFragmentManager
                    .findFragmentByTag(DetailFragment::class.java.simpleName)

            val subCategoryFragment = supportFragmentManager
                    .findFragmentByTag("SubCategory")


            if(detailFragment == null && subCategoryFragment == null) {
                binding?.showFilter = true
            }
        }
    }
    internal fun launchProductDetailFragment(productId: Int) {
        binding?.showFilter = false
        supportFragmentManager.transaction {
            addToBackStack("")
            add(R.id.fragmentContainer, DetailFragment.newInstance(productId),
                    DetailFragment::class.java.simpleName)
        }
    }

    internal fun launchSubCategoryFragment(catId: Int) {
        binding?.showFilter = false
        supportFragmentManager.transaction {
            addToBackStack("")
            add(R.id.fragmentContainer, MainFragment.newInstance(catId),
                    "SubCategory")
        }
    }

    internal fun launchRankWiseFragment(rankName: String) {
        binding?.showFilter = true
        supportFragmentManager.transaction {
            replace(R.id.fragmentContainer, RankFragment.newInstance(rankName))
        }
    }

    private fun launchContentFragment() {
        binding?.showFilter = true
        supportFragmentManager.transaction {
            replace(R.id.fragmentContainer, MainFragment.newInstance(-1),
                    MainFragment::class.java.simpleName)
        }
    }
}
