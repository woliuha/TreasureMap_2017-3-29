package com.yulu.zhaoxinpeng.mytreasuremap.treasure.hide;


import com.yulu.zhaoxinpeng.mytreasuremap.net.NetClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/7.
 * 埋藏宝藏的业务类
 */

public class HideTreasurePresenter {

    private HideTreasureView mHideTreasureView;

    public HideTreasurePresenter(HideTreasureView hideTreasureView) {
        this.mHideTreasureView = hideTreasureView;
    }

    // 埋藏宝藏
    public void hideTreasure(HideTreasure hideTreasure){

        // 显示一个进度
        mHideTreasureView.showProgress();

        Call<HideTreasureResult> resultCall = NetClient.getInstance().getTreasureApi().hideTreasure(hideTreasure);
        resultCall.enqueue(mResultCallback);
    }

    private Callback<HideTreasureResult> mResultCallback = new Callback<HideTreasureResult>() {

        // 请求成功
        @Override
        public void onResponse(Call<HideTreasureResult> call, Response<HideTreasureResult> response) {
            // 隐藏进度条
            mHideTreasureView.hideProgress();

            if (response.isSuccessful()){
                HideTreasureResult treasureResult = response.body();
                if (treasureResult==null){
                    // 提示信息
                    mHideTreasureView.showToast("未知的错误");
                    return;
                }
                // 真正的上传成功了
                if (treasureResult.getCode()==1){
                    // 跳回到Home页面
                    mHideTreasureView.navigateToHome();
                }
                // 提示信息
                mHideTreasureView.showToast(response.code()+"");
                mHideTreasureView.showToast(treasureResult.getMsg());
            }
        }

        // 请求失败
        @Override
        public void onFailure(Call<HideTreasureResult> call, Throwable t) {
            // 隐藏进度条
            mHideTreasureView.hideProgress();
            // 提示信息
            mHideTreasureView.showToast("请求失败："+t.getMessage());
        }
    };
}
