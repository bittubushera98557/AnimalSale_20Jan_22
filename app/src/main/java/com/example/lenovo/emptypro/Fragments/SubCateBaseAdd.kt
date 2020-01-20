package com.example.lenovo.emptypro.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.lenovo.emptypro.Activities.MainActivity
import com.example.lenovo.emptypro.ApiCallClasses.RetrofitClasses.GetDataService
import com.example.lenovo.emptypro.ApiCallClasses.RetrofitClasses.RetrofitClientInstance

import com.example.lenovo.emptypro.Listeners.OnFragmentInteractionListener
import com.example.lenovo.emptypro.ModelClasses.AllApiResponse
import com.example.lenovo.emptypro.R
import com.example.lenovo.emptypro.Utilities.Utilities
import com.example.lenovo.emptypro.Utils.GlobalData
import com.example.lenovo.emptypro.Utils.SharedPrefUtil
import com.facebook.internal.Utility
import com.google.gson.Gson
 import com.iww.classifiedolx.recyclerview.setUp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_sub_cate_base_add.*
import kotlinx.android.synthetic.main.item_advertisement.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SubCateBaseAdd : Fragment() {
    // TODO: Rename and change types of parameters
    private var mainCatId: String? = null
    private var subCatId: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var ctx: Context? = null
    var utilities=Utilities()
    private var advertiseItemData: MutableList<AllApiResponse.FilterBasePetsRes.FilterBasePetsModel>? = null

    var TAG="SubCateBaseAdd "
    internal var service: GetDataService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainCatId = it.getString(ARG_PARAM1)
            subCatId = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_cate_base_add, container, false)
    }

     fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        advertiseItemData = mutableListOf()

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService::class.java)
        rv_adverLst.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = VERTICAL
        rv_adverLst.layoutManager = mLayoutManager

        rv_adverLst.setUp(advertiseItemData!!, R.layout.item_advertisement, { it1 ->

            val circularProgressDrawable = CircularProgressDrawable(ctx as Activity)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            /*var strImgUrl = ""
            if (!it1.imageUpload1.equals("")) {
                strImgUrl = it1.imageUpload1
            } else if (!it1.imageUpload2.equals("")) {
                strImgUrl = it1.imageUpload2
            } else if (!it1.imageUpload3.equals("")) {
                strImgUrl = it1.imageUpload3

            } else if (!it1.imageUpload4.equals("")) {
                strImgUrl = it1.imageUpload4
            }*/
           /* Picasso.with(context).load(Uri.parse(GlobalData.BASE_URL+ strImgUrl))
                    .placeholder(circularProgressDrawable).error(R.drawable.app_logo).into(this.iv_advertiseImg)
*/

            tv_advertisePrice.text ="Rs. "+ it1.petPrice
            tv_advertiseDate.text = "12-03-19"



            tv_advertiseMoreInfo.text=it1.petName
            tv_advertiseDesc.text=it1.category+" | "+it1.subCategory

            iv_favImg.setOnClickListener{
              /*  if(it1.favorite.equals("0"))
                {

                    if(utilities.isConnected(ctx!!))
                        callChangeFavApi("add_fav_ads", it1)
                    else
                        utilities.snackBar(rv_adverLst,"Please check internet ")

                }
                else{
                    if(utilities.isConnected(ctx!!))
                        callChangeFavApi("delete_fav_ads",it1)
                    else
                        utilities.snackBar(rv_adverLst,"Please check internet ")

                }*/
            }

           /* if(it1.favorite.equals("1"))
            {
                iv_favImg.setImageResource(R.drawable.ic_favorite_filled)
                //iv_favImg.setImageResource(R.drawable.ic_favorite_filled)
            }else{
                iv_favImg.setImageResource(R.drawable.ic_favorite_empty)

            }*/


            this.ll_advertItemLinear.setOnClickListener {
                utilities.enterNextReplaceFragment(
                        R.id.fl_subCateBaseAdd,
                        AdvertisementDetailsFrag.newInstance(  ""+it1.petID,""),
                        (ctx as MainActivity).supportFragmentManager
                )
            }
        }, { view1: View, i: Int -> })

        if(utilities.isConnected(ctx!!))
            fetchAdvertiseList()
        else
            utilities.snackBar(rv_adverLst,"Please check internet ")


        rv_adverLst.layoutManager = LinearLayoutManager(context)

        swipe_refresh.setOnRefreshListener {
            if(utilities.isConnected(ctx!!))
                fetchAdvertiseList()
            else
                utilities.snackBar(rv_adverLst,"Please check internet ")

        }
    }

    private fun callChangeFavApi(strAction: String,dataModel: AllApiResponse.FilterBasePetsRes.FilterBasePetsModel?) {
        Log.e(TAG,"callChangeFavApi   adsId="+dataModel!!.petID+"   action="+strAction)

        val call = service!!.addInFav(""+strAction ,""+SharedPrefUtil.getUserId(ctx),""+ dataModel!!.petID).enqueue(object : Callback<AllApiResponse.CommonRes> {
            override fun onResponse(
                    call: Call<AllApiResponse.CommonRes>,
                    response: Response<AllApiResponse.CommonRes>
            ) {
                Log.e("callAddInFavApi res", "" + Gson().toJson(response.body()))
                if (response.isSuccessful && (response.body()!!.status.equals("200"))) {
                    if(strAction.equals("add_fav_ads"))
//                        dataModel!!.favorite="1"
                    if(strAction.equals("delete_fav_ads"))
  //                      dataModel!!.favorite="0"

                    rv_adverLst.adapter!!.notifyDataSetChanged()
                } else {
                    //swipe_refresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<AllApiResponse.CommonRes>, t: Throwable) {
                t.printStackTrace()
                tvNoData.visibility = View.VISIBLE
                rv_adverLst.visibility = View.GONE
                //swipe_refresh.isRefreshing = false
            }
        })

    }


    private fun fetchAdvertiseList() {
        swipe_refresh.isRefreshing = true
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService::class.java)
        Log.e(TAG+"fetchAdvertiseList res", "filter-data/?userID="+ SharedPrefUtil.getUserId(ctx)+"&cityName=&catId="+mainCatId+"&subCat="+subCatId)

        service!!.getFilterBaseApi(""+SharedPrefUtil.getUserId(ctx),"",""+ mainCatId, ""+subCatId ).enqueue(object : Callback<AllApiResponse.FilterBasePetsRes> {
            override fun onResponse(
                    call: Call<AllApiResponse.FilterBasePetsRes>,
                    response: Response<AllApiResponse.FilterBasePetsRes>
            ) {
                Log.e("fetchAdvertiseList res", "" + Gson().toJson(response.body()))
                if (response.isSuccessful && (response.body()!!.status.equals("200"))) {
                    advertiseItemData!!.clear()
                    advertiseItemData!!.addAll(response.body()!!.data)
                    rv_adverLst.adapter!!.notifyDataSetChanged()
                    swipe_refresh.isRefreshing = false
                } else {
                    tvNoData.visibility = View.VISIBLE
                    rv_adverLst.visibility = View.GONE
                    swipe_refresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<AllApiResponse.FilterBasePetsRes>, t: Throwable) {
                t.printStackTrace()
                tvNoData.visibility = View.VISIBLE
                rv_adverLst.visibility = View.GONE
                swipe_refresh.isRefreshing = false
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx=context
         if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SubCateBaseAdd().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
