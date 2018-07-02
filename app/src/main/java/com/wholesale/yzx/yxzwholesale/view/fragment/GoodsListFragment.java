package com.wholesale.yzx.yxzwholesale.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.wholesale.yzx.yxzwholesale.R;
import com.wholesale.yzx.yxzwholesale.base.BaseFragment;
import com.wholesale.yzx.yxzwholesale.bean.GoodsListBean;
import com.wholesale.yzx.yxzwholesale.bean.GoodsSecondTypeBean;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsListFragmentAdapter;
import com.wholesale.yzx.yxzwholesale.view.adapter.GoodsSecondTypeAdapter;
import com.wholesale.yzx.yxzwholesale.view.widght.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/6/28.
 */

public class GoodsListFragment extends BaseFragment {


    @InjectView(R.id.list)
    RecyclerView list;

    private View headerView;
    private RecyclerView list_goods_type;
    private  ConvenientBanner convenientBanner;

    private String goodsTypeId;//商品分类id
    public static List<String> goodsImageBanner;//广告轮播图数据
    private GoodsListFragmentAdapter adapter;//商品列表适配器
    private List<GoodsListBean> datas=new ArrayList<>();//商品数据
    private List<GoodsSecondTypeBean> secondTypeDatas=new ArrayList<>();//商品二级分类
    private GoodsSecondTypeAdapter goodsSecondTypeAdapter;
    private int type=0;//商品布局样式



    @Override
    protected int getContentId() {
        return R.layout.goods_list_fragment_layout;
    }

    @Override
    protected void init() {
        super.init();
        goodsTypeId = getArguments().getString("goodTypeId");
        if(goodsTypeId.equals("0")){
            type=1;
        }else {
            type=2;
        }

        headerView=View.inflate(getActivity(),R.layout.goodstype_header_view,null);
        convenientBanner=headerView.findViewById(R.id.convenient_banner);
        list_goods_type=headerView.findViewById(R.id.list_goods_type);

        goodsSecondTypeAdapter=new GoodsSecondTypeAdapter(getActivity(),R.layout.item_goods_second_type,secondTypeDatas);
        list_goods_type.setAdapter(goodsSecondTypeAdapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        list_goods_type.setLayoutManager(gridLayoutManager);


        adapter=new GoodsListFragmentAdapter(getActivity(),R.layout.item_goods_list,datas);
        list.setAdapter(adapter);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),type);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 0;
            }
        });
        list.setLayoutManager(layoutManager);
        adapter.addHeaderView(headerView);

        showConvenientBanner();
        getGoodslistData();
        getGoodsSecondTypeData();
    }

    /**
     * 广告轮播图
     */
    private void showConvenientBanner() {
        goodsImageBanner = new ArrayList<>();
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457446439&di=f9fe04c988d334900287de887d88c703&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F337%2F44549.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457488011&di=98aea79bfe7db959223aae74c15f1552&imgtype=0&src=http%3A%2F%2Fpic22.photophoto.cn%2F20120105%2F0017030057473498_b.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457515523&di=aa0cddeff5f699a2a875d44ec7cb0341&imgtype=0&src=http%3A%2F%2Fpic23.nipic.com%2F20120805%2F10665405_101511605116_2.jpg");
        goodsImageBanner.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530457566718&di=056f36ce1989f21506390cc198c21318&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130323%2F9544839_100946415000_2.jpg");


        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public NetworkImageHolderView createHolder(View itemView) {
                return new NetworkImageHolderView(getActivity(), itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_convenientbanner;
            }
        }, goodsImageBanner)
//                .setPageIndicator()
//           .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
    }

    private void getGoodsSecondTypeData(){
            GoodsSecondTypeBean bean=new GoodsSecondTypeBean();
            bean.setGoodsSecondTypeId(001);
            bean.setGoodsSecondTypeName("限时秒杀");
            bean.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
            secondTypeDatas.add(bean);
        GoodsSecondTypeBean bean1=new GoodsSecondTypeBean();
        bean1.setGoodsSecondTypeId(002);
        bean1.setGoodsSecondTypeName("品牌清仓");
        bean1.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean1);
        GoodsSecondTypeBean bean2=new GoodsSecondTypeBean();
        bean2.setGoodsSecondTypeId(003);
        bean2.setGoodsSecondTypeName("名品折扣");
        bean2.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean2);
        GoodsSecondTypeBean bean3=new GoodsSecondTypeBean();
        bean3.setGoodsSecondTypeId(004);
        bean3.setGoodsSecondTypeName("男人帮");
        bean3.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean3);
        GoodsSecondTypeBean bean4=new GoodsSecondTypeBean();
        bean4.setGoodsSecondTypeId(005);
        bean4.setGoodsSecondTypeName("食品超市");
        bean4.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean4);
        GoodsSecondTypeBean bean5=new GoodsSecondTypeBean();
        bean5.setGoodsSecondTypeId(006);
        bean5.setGoodsSecondTypeName("电视厨卫");
        bean5.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean5);
        GoodsSecondTypeBean bean6=new GoodsSecondTypeBean();
        bean6.setGoodsSecondTypeId(7);
        bean6.setGoodsSecondTypeName("休闲零食");
        bean6.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean6);
        GoodsSecondTypeBean bean7=new GoodsSecondTypeBean();
        bean7.setGoodsSecondTypeId(8);
        bean7.setGoodsSecondTypeName("纸品湿巾");
        bean7.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean7);
        GoodsSecondTypeBean bean8=new GoodsSecondTypeBean();
        bean8.setGoodsSecondTypeId(9);
        bean8.setGoodsSecondTypeName("五金工具");
        bean8.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean8);
        GoodsSecondTypeBean bean9=new GoodsSecondTypeBean();
        bean9.setGoodsSecondTypeId(010);
        bean9.setGoodsSecondTypeName("查看全部");
        bean9.setGoodsSecondImageUrl("https://img10.360buyimg.com/babel/s300x300_jfs/t20401/236/194635593/326774/2e0cd2ed/5b027ba3N1088d700.jpg");
        secondTypeDatas.add(bean9);
        goodsSecondTypeAdapter.notifyDataSetChanged();
    }
    /**
     * 商品列表数据
     */
    private void getGoodslistData(){

            GoodsListBean bean=new GoodsListBean();
            bean.setGoodsId("0");
            bean.setGoodsName("蒙牛真果粒牛奶");
            bean.setGoodsPrice(59.9);
            bean.setGoodsSpellNums(9);
            bean.setGoodsImageUrl("https://img11.360buyimg.com/mobilecms/s500x500_jfs/t19663/247/1001150916/163815/35bb42ae/5ab46f73N4049f6f8.jpg");

            datas.add(bean);
        GoodsListBean bean0=new GoodsListBean();
        bean0.setGoodsId("1");
        bean0.setGoodsName("三合一数据线");
        bean0.setGoodsPrice(9.9);
        bean0.setGoodsSpellNums(18);
        bean0.setGoodsImageUrl("https://img12.360buyimg.com/mobilecms/s500x500_jfs/t21253/257/782525093/200559/e10302eb/5b18d1fdNe1be8bc0.jpg");

        datas.add(bean0);
        GoodsListBean bean1=new GoodsListBean();
        bean1.setGoodsId("2");
        bean1.setGoodsName("中药减肥");
        bean1.setGoodsPrice(199.9);
        bean1.setGoodsSpellNums(200);
        bean1.setGoodsImageUrl("https://img10.360buyimg.com/mobilecms/s500x500_jfs/t21640/275/1372189259/162348/9575baca/5b27122eN3b046591.jpg");

        datas.add(bean1);
        GoodsListBean bean2=new GoodsListBean();
        bean2.setGoodsId("3");
        bean2.setGoodsName("罗蒙短袖商务上衣");
        bean2.setGoodsPrice(599.9);
        bean2.setGoodsSpellNums(7);
        bean2.setGoodsImageUrl("https://img13.360buyimg.com/mobilecms/s500x500_jfs/t21736/143/1652567222/202038/fad4d09d/5b30e4cfNdf8f6fcd.jpg");

        datas.add(bean2);
        GoodsListBean bean3=new GoodsListBean();
        bean3.setGoodsId("4");
        bean3.setGoodsName("金冠伊利奶粉");
        bean3.setGoodsPrice(399.9);
        bean3.setGoodsSpellNums(50);
        bean3.setGoodsImageUrl("https://img12.360buyimg.com/mobilecms/s500x500_jfs/t15043/88/2564947767/101257/3d33bf1e/5aa8f56dNb64ef094.jpg");

        datas.add(bean3);
        GoodsListBean bean4=new GoodsListBean();
        bean4.setGoodsId("5");
        bean4.setGoodsName("桂圆红枣枸杞");
        bean4.setGoodsPrice(26.9);
        bean4.setGoodsSpellNums(23);
        bean4.setGoodsImageUrl("https://img10.360buyimg.com/mobilecms/s500x500_jfs/t22480/188/542489527/165309/e09bf2e1/5b344923N717392df.jpg");

        datas.add(bean4);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
