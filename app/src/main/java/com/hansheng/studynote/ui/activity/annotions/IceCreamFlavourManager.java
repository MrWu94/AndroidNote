package com.hansheng.studynote.ui.activity.annotions;

import android.support.annotation.IntDef;

/**
 * Created by hansheng on 17-5-8.
 */

public class IceCreamFlavourManager {
    private int flavour;

    public static final int VANILLA = 0;
    public static final int CHOCOLATE = 1;
    public static final int STRAWBERRY = 2;

    @IntDef({VANILLA, CHOCOLATE, STRAWBERRY})
    public @interface Flavour {
    }

    @Flavour
    public int getFlavour() {
        return flavour;
    }

    public void setFlavour(@Flavour int flavour) {
        this.flavour = flavour;
    }

}
