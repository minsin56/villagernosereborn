package com.minsin56.VillagersNoseMod.VillagerStuff.Nose;

public class Nose implements INose
{
    private boolean IHasNose = true;

    @Override
    public boolean HasNose()
    {
        return IHasNose;
    }

    @Override
    public void SetHasNose(boolean Value)
    {
        IHasNose = Value;
    }
}
