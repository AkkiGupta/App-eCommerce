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
import app.ecomm.data.model.content.Product
import app.ecomm.data.model.content.Variant
import app.ecomm.databinding.FragmentProductDetailBinding
import app.ecomm.ui.adapter.DetailProductAdapter
import app.ecomm.ui.di.Injectable
import app.ecomm.util.AutoClearedValue
import app.ecomm.util.inflateWithDataBinding
import kotlinx.android.synthetic.main.fragment_product_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var contentViewModel: ContentViewModel
    private lateinit var binding: AutoClearedValue<FragmentProductDetailBinding>
    private lateinit var detailController: DetailProductAdapter

    var productId: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding =
                container?.inflateWithDataBinding(R.layout.fragment_product_detail)
                        as FragmentProductDetailBinding
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId = arguments?.getInt("productId")
        initUi()
        initViewModels()
        fetchProductDetail(productId!!)
    }

    private fun initUi() {
        detailController = DetailProductAdapter(object : DetailProductAdapter.ItemClickListener {
            override fun onContentItemClicked(variant: Variant) {
                setProductVariant(variant)
            }
        })
        rvVariants.adapter = detailController.adapter
    }

    private fun setProductVariant(variant: Variant) {
        binding.get()?.variant = variant
    }

    private fun initViewModels() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]
    }

    private fun fetchProductDetail(productId: Int) {
        contentViewModel.getProductById(productId).observe(this, Observer {
            onContentResponse(it)
        })
    }

    private fun onContentResponse(resource: Resource<Product>?) {
        // Data binding will switch states based on resource.status and provide data to views
        binding.get()?.resource = resource
        Log.d("Fragment", "Response ${resource?.status} ${resource?.data}")

        when (resource?.status) {
            Status.SUCCESS -> {
                resource?.data?.variants?.get(0).let {
                    tvProductTitle.text = resource?.data?.name
                    binding.get()?.variant = resource?.data?.variants?.get(0)
                    binding.get()?.tax = resource?.data?.tax
                }

                detailController.setData(resource.data, "Dummy Arg")
            }
            Status.ERROR -> {
            }
            else -> {
            }
        }
    }

    companion object {
        fun newInstance(arg: Int): DetailFragment {
            val args = Bundle()
            val fragment = DetailFragment()
            args.putInt("productId", arg)
            fragment.arguments = args
            return fragment
        }
    }
}