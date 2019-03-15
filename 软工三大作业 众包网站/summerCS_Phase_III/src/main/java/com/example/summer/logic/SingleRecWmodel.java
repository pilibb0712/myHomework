package com.example.summer.logic;

public interface SingleRecWmodel extends Wmodel{
    double[] getXContributionVec();
    void setXContributionVec(double[] x);

    double[] getYContributionVec();
    void setYContributionVec(double[] x);

    double[] getWContributionVec();
    void setWContributionVec(double[] x);

    double[] getHContributionVec();
    void setHContributionVec(double[] x);
}
