package starbucksTeam;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestOrderBuilder {
    @Test
    public void shouldBuildCLSmallMilkACoffe(){
        Starbucks coffee = new StarbuckBuilder().withFlavour(Flavour.CL).withMilk(Milk.Almond).withSize(Size.Short).build();
        assertEquals(Flavour.CL, coffee.getFlavour());
        assertEquals(Milk.Almond, coffee.getMilk());
        assertEquals(Size.Short,coffee.getSize());
        assertEquals("Caffe latteAlmondShort",coffee.getFinalOrder());

    }
    @Test
    public void shouldBuildSmallMilk2Tea(){
        Starbucks coffee = new StarbuckBuilder().withFlavour(Flavour.Tea).withMilk(Milk.Milk2).withSize(Size.Short).build();
        assertEquals(Flavour.Tea, coffee.getFlavour());
        assertEquals(Milk.Milk2, coffee.getMilk());
        assertEquals(Size.Short,coffee.getSize());
        assertEquals("Tea2%MilkShort",coffee.getFinalOrder());

    }
}
