package cscot.basicnetherores.event;



import cscot.basicnetherores.BasicNetherOres;
import cscot.basicnetherores.objects.blocks.BlockOreBase;
import cscot.basicnetherores.util.handler.ConfigHandler;
import cscot.basicnetherores.util.helper.BlockListHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public final class BlockOreEvents {

    @SubscribeEvent(priority=EventPriority.HIGH, receiveCanceled=true)
    public static void onBlockHarvest(BlockEvent.BreakEvent event) {
    	boolean hasArmor = false;
    	boolean isPlayerInNether = false;
    	
    	EntityPlayer breaker  = event.getPlayer();
    	
    	for(ItemStack ai: breaker.inventory.armorInventory) {
    		if (!ai.isEmpty()) {
    			hasArmor = true;
    		}
    	}
    	
    	isPlayerInNether = breaker.world.getBiome(event.getPos()) == Biomes.HELL;
    	
        if(hasArmor && isPlayerInNether) {

            if (ConfigHandler.pigmanGuard) {

                IBlockState block = event.getState();

                if(BlockListHelper.PREDICATE_BLOCKS.apply(block))
                {
                    BlockOreBase.pigmenGuards(event.getPlayer(), event.getWorld(), event.getPos());
                }
            }
        }
    }
}
