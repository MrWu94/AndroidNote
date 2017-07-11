package com.hansheng.Algorithm;

/**
 * Created by mrwu on 2017/6/30.
 * https://mp.weixin.qq.com/s?__biz=MzAxOTc0NzExNg==&mid=2665513734&idx=1&sn=3a412a6cbc8a5c35508186347240dae8&chksm=80d67b45b7a1f253fe78c499c5b9a1126bb4bb95ed60c4798d638fe08c4b3fe94a2b95f0f8c1&mpshare=1&scene=1&srcid=0627iLEpSfmEtvjZVAbmZLeC&key=243547dcedfbad6c8eaebf1e86962956db2d711d2d7e8b1d50d5f9ad52d558fe10d23635d0058b6f4a86d5dfba2112e12d5f24bbcc4b77a25d26b65e873555b34f280cc658d5feb39838460bf2f7e5bd&ascene=0&uin=MjMxMjAwNzUxOA%3D%3D&devicetype=iMac+MacBookPro13%2C1+OSX+OSX+10.12.5+build(16F73)&version=12020810&nettype=WIFI&fontScale=100&pass_ticket=H6TemKy4HGMIBk7jHwu%2BtB9EaTVLbP35de0s2NzOEEjHLFFDDYJYAPxTmeXP25Ac
 */

public class DynamicPrograming {
    public static void main(String... args) {

        getClimbWays(10);
    }

    public static int getClimbWays(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
}
