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
import app.ecomm.core.di.Injectable
import app.ecomm.core.viewmodel.ContentViewModel
import app.ecomm.data.api.model.Resource
import app.ecomm.data.api.model.Status
import app.ecomm.data.model.content.ECommContent
import app.ecomm.data.model.content.Product
import app.ecomm.databinding.FragmentMainBinding
import app.ecomm.ui.activity.MainActivity
import app.ecomm.ui.adapter.HomeAdapter
import app.ecomm.util.AutoClearedValue
import app.ecomm.util.inflateWithDataBinding
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(),Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var contentViewModel: ContentViewModel
    private lateinit var binding: AutoClearedValue<FragmentMainBinding>
    private lateinit var homeController: HomeAdapter

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
        initUi()
        initViewModels()
        fetchContent()
    }

    private fun initUi() {
        homeController = HomeAdapter(object : HomeAdapter.ItemClickListener {
            override fun onContentItemClicked(prodcut: Product) {
                (activity as MainActivity)
                        .launchProductDetailFragment(prodcut.id)
            }
        })
        rvHomeListing.adapter = homeController.adapter

//        binding.get()?.retryCallback = object : RetryCallback {
//            override fun retry() {
//                sampleContentViewModel.retryContentList()
//            }
//        }
    }

    private fun initViewModels() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

//        contentViewModel = ViewModelProviders.of(this)
//                .get(ContentViewModel::class.java)

    }

    private fun fetchContent() {
        contentViewModel.getContent().observe(this, Observer {
            onContentResponse(it)
        })
    }

    private fun onContentResponse(resource: Resource<ECommContent>?) {
        // Data binding will switch states based on resource.status and provide data to views
        binding.get()?.resource = resource
        Log.d("Fragment", "Response ${resource?.status} ${resource?.data}")

        when(resource?.status) {
            Status.SUCCESS,
            Status.LOADING -> {
                homeController.setData(resource.data?.categories, "Dummy Arg")
            }
            Status.ERROR -> {}
        }
    }

    companion object {
        fun newInstance(arg: String): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            args.putString("arg", arg)
            fragment.arguments = args
            return fragment
        }
    }
}