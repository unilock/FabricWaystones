package wraith.fwaystones.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import wraith.fwaystones.FabricWaystones;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.gui.UniversalWaystoneGui;
import wraith.fwaystones.util.TeleportSources;

public class PocketWormholeItem extends TexturedPolymerItem {

    private static final Text TITLE = Text.translatable("container." + FabricWaystones.MOD_ID + ".pocket_wormhole");

    public PocketWormholeItem(Settings settings) {
        super(settings, Items.ENDER_PEARL, "item/pocket_wormhole");
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            UniversalWaystoneGui.open(serverPlayerEntity, TITLE, TeleportSources.POCKET_WORMHOLE);
        }
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
