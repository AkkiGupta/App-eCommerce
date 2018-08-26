package app.ecomm.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ecomm.R
import app.ecomm.core.viewmodel.ContentViewModel
import app.ecomm.data.api.model.Resource
import app.ecomm.data.api.model.Status
import app.ecomm.data.model.content.Product
import app.ecomm.data.model.content.Ranking
import app.ecomm.databinding.FragmentRankBinding
import app.ecomm.ui.activity.MainActivity
import app.ecomm.ui.adapter.RankWiseAdapter
import app.ecomm.ui.di.Injectable
import app.ecomm.util.AutoClearedValue
import app.ecomm.util.inflateWithDataBinding
import kotlinx.android.synthetic.main.fragment_rank.*
import javax.inject.Inject


class RankFragment : BaseFragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var contentViewModel: ContentViewModel
    private lateinit var binding: AutoClearedValue<FragmentRankBinding>
    private lateinit var rankController: RankWiseAdapter

    private var rank: String = "All"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding =
                container?.inflateWithDataBinding(R.layout.fragment_rank)
                        as FragmentRankBinding
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rank = arguments?.getString("RANK") ?: "All"
        initUi()
        initViewModels()
        fetchContent(rank)
    }

    private fun initUi() {
        rankController = RankWiseAdapter(object : RankWiseAdapter.ItemClickListener {

            override fun onContentItemClicked(prodcut: Product) {
                (activity as MainActivity)
                        .launchProductDetailFragment(prodcut.id)
            }
        })
        rvRankListing.adapter = rankController.adapter
    }

    private fun initViewModels() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

    }

    private fun fetchContent(mRank: String = "All") {
        if(mRank == "All"){
            contentViewModel.getAllProductsRankWise().observe(this, Observer {
                onAllPRoductRankWiseResponse(it)
            })
        }else {
            contentViewModel.getProductsByRank(mRank).observe(this, Observer {
                onContentResponse(it)
            })
        }
    }

    private fun onContentResponse(resource: Resource<Ranking>?) {
        binding.get()?.resource = resource

        when(resource?.status) {
            Status.SUCCESS,
            Status.LOADING -> {
                resource?.data?.let {
                    rankController.setData(arrayListOf(it))
                }
            }
            Status.ERROR -> {}
        }
    }

    private fun onAllPRoductRankWiseResponse(resource: Resource<List<Ranking>>?) {
        binding.get()?.resource = resource

        when(resource?.status) {
            Status.SUCCESS,
            Status.LOADING -> {
                resource?.data?.let {
                    rankController.setData(it)
                }
            }
            Status.ERROR -> {}
        }
    }

    companion object {
        fun newInstance(rank: String="All"): RankFragment {
            val args = Bundle()
            val fragment = RankFragment()
            args.putString("RANK", rank)
            fragment.arguments = args
            return fragment
        }
    }
}