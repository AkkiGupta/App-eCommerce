package app.ecomm.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ecomm.R
import app.ecomm.core.viewmodel.ContentViewModel
import app.ecomm.data.api.model.Resource
import app.ecomm.data.api.model.Status
import app.ecomm.data.model.content.*
import app.ecomm.databinding.FragmentMainBinding
import app.ecomm.ui.activity.MainActivity
import app.ecomm.ui.adapter.HomeAdapter
import app.ecomm.ui.di.Injectable
import app.ecomm.util.AutoClearedValue
import app.ecomm.util.inflateWithDataBinding
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var contentViewModel: ContentViewModel
    private lateinit var binding: AutoClearedValue<FragmentMainBinding>
    private lateinit var homeController: HomeAdapter

    private var catId: Int = -1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding =
                container?.inflateWithDataBinding(R.layout.fragment_main)
                        as FragmentMainBinding
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catId = arguments?.getInt("Sub_Cat_id") ?: -1
        initUi()
        initViewModels()
        fetchContent(catId)
    }

    private fun initUi() {
        homeController = HomeAdapter(object : HomeAdapter.ItemClickListener {
            override fun onSubCategoryItemClicked(childCategory: ChildCategories) {
                (activity as MainActivity)
                        .launchSubCategoryFragment(childCategory.id)
            }

            override fun onContentItemClicked(prodcut: Product) {
                (activity as MainActivity)
                        .launchProductDetailFragment(prodcut.id)
            }
        })
        rvHomeListing.adapter = homeController.adapter
    }

    private fun initViewModels() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

    }

    private fun fetchContent(id: Int = -1) {
        if (id != -1) {
            contentViewModel.getProductsByCatId(id).observe(this, Observer {
                onCatIdContentResponse(it)
            })
        } else {
            contentViewModel.getContent().observe(this, Observer {
                onContentResponse(it)
            })
        }
    }

    private fun onCatIdContentResponse(resource: Resource<ECommContent>?) {
        binding.get()?.resource = resource
        Log.d("Fragment", "Response ${resource?.status} ${resource?.data}")

        when (resource?.status) {
            Status.SUCCESS,
            Status.LOADING -> {
                homeController.setData(resource.data?.categories, "Dummy Arg")
            }
            Status.ERROR -> {
            }
        }
    }

    private fun onContentResponse(resource: Resource<ECommContent>?) {
        // Data binding will switch states based on resource.status and provide data to views
        binding.get()?.resource = resource
        Log.d("Fragment", "Response ${resource?.status} ${resource?.data}")

        when (resource?.status) {
            Status.SUCCESS,
            Status.LOADING -> {
                resource.data?.let {
                    homeController.setData(it.categories, "Dummy Arg")
                    it.rankings?.let { rankings ->
                        (activity as MainActivity).setUpRankingSpinner(rankings)
                    }
                    it.categories?.let { cat ->
                        (activity as MainActivity).setUpCategorySpinner(cat)
                    }
                }
            }
            Status.ERROR -> {
            }
        }
    }

    companion object {
        fun newInstance(catId: Int): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            args.putInt("Sub_Cat_id", catId)
            fragment.arguments = args
            return fragment
        }
    }
}