package wraith.fwaystones.item;

import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.FabricWaystones;
import wraith.fwaystones.registry.ItemRegistry;

public class VoidTotem extends VoidItem {

    private PolymerModelData modelDataActive;

    public VoidTotem(Settings settings) {
        super(settings, Items.TOTEM_OF_UNDYING, "item/void_totem");
        this.canTeleport = false;
        this.translationName = "void_totem";
    }

    @Override
    protected void registerPolymerModel(Item item, String modelPath) {
        super.registerPolymerModel(item, modelPath);
        this.modelDataActive = PolymerResourcePackUtils.requestModel(item, new Identifier(FabricWaystones.MOD_ID, modelPath + "_active"));
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        if (ItemRegistry.hasLearned(itemStack)) {
            return this.modelDataActive.value();
        } else {
            return super.getPolymerCustomModelData(itemStack, player);
        }
    }
}
