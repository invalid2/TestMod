package net.testauthor.testmod;

import dangerzone.BaseMod;
import dangerzone.entities.Entities;
import dangerzone.entities.ModelHumanoid;
import dangerzone.items.Item;
import dangerzone.items.ItemSpawnEgg;
import dangerzone.items.Items;

/**
 * This is the main class of the mod, the 'Main' at the end is
 * necessary so DangerZone adds this jar to the class path.
 * <p>
 * DangerZone is a game made by Richard Clark a.k.a TheyCallMeDanger
 * fro more info visit this page: http://www.dangerzonegame.net/
 * @author InvalidName
 */
public class TestModMain extends BaseMod {
	
	/*
	 * 										Basic mod constants
	 * MOD_NAME -> The name of the mod ex MagicalMe, Xahanns Food Mod, SpanwerBlocks, Orespawn, etc
	 * MOD_VERSION -> Version of the mod itself, separated so it can put at the end of the getModName() string
	 * DZ_VERSION -> The version this mod was made for ex 1.9, 2.0
	 */
	public static final String MOD_NAME = "TestMod";
	public static final String MOD_VERSION = "TEST";
	public static final String DZ_VERSION = "2.1";
	
	/*
	 * Items with custom texture size, meaning they can be bigger or smaller than 16x16 pixels
	 */
	public static Item test32 = new ItemCustomPixel("TestMod:Axe Thing","testmodres/diamond_block_axe.png", 32, 0, 0, 0, 0, 0, 0, 1, 1, 1); //32px
	public static Item test64 = new ItemCustomPixel("TestMod:Spear of War","testmodres/spear_test.png", 64, 10, -10, 0, (float) (Math.PI/2), 0, 0, 1, 1, 1); //64px
	public static Item test8 = new ItemCustomPixel("TestMod:Gameboy pick","testmodres/pickaxe_gameboy.png", 8, 0, 0, 0, 0, 0, 0, 2, 2, 2);//8px
	
	/*
	 * Items with obj models
	 * A generic giant eye
	 * And an epic sword!
	 */
	public static Item itemobj = new ItemOBJModel("TestMod:Obj Item","testmodres/eye.png");
	public static Item epicsword = new EpicSword("TestMod:Epic Sword","testmodres/epicsword.png", 1200, 20);
	
	//Spawn egg for the dummy entity, used as a punching bag
	public static Item egg = new ItemSpawnEgg("TestMod:DummySpawnEgg","testmodres/eggdummy.png", "TestMod:DummyEntity");
	
	//Testing this thing still
	public static Item eggbanshee = new ItemSpawnEgg("TestMod:BansheeSpawnEgg","testmodres/eggdummy.png", "TestMod:Banshee");
	
	public TestModMain() {
		//initialize your mod if need be...
	}
	
	//Composite of the three string constants from above
	public String getModName()
	{
		return MOD_NAME+" v"+MOD_VERSION+" for DZ"+DZ_VERSION;
	}
	
	/**
	 * This is the version of DZ that I have made this mod with
	 */
	public String versionBuiltWith()
	{
		return DZ_VERSION;
	}
	
	public void registerThings() {
		//Register the items from above
		Items.registerItem(test32);
		Items.registerItem(test64);	
		Items.registerItem(test8);
		Items.registerItem(egg);
		Items.registerItem(itemobj);
		Items.registerItem(epicsword);
		Items.registerItem(eggbanshee);
		//Register the dummy entity
		Entities.registerEntity(DummyEntity.class, "TestMod:DummyEntity", new ModelHumanoid());
		Entities.registerEntity(Banshee.class, "TestMod:Banshee", new ModelBanshee());
	}
	
	public void postLoadProcessing(){
		
	}
}
