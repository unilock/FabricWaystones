package wraith.fwaystones.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import wraith.fwaystones.FabricWaystones;

public class TexturedPolymerItem extends SimplePolymerItem {

    protected PolymerModelData modelData;

    public TexturedPolymerItem(Settings settings, Item polymerItem, String modelPath) {
        super(settings, polymerItem);
        this.registerPolymerModel(polymerItem, modelPath);
    }

    protected void registerPolymerModel(Item item, String modelPath) {
        this.modelData = PolymerResourcePackUtils.requestModel(item, new Identifier(FabricWaystones.MOD_ID, modelPath));
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelData.value();
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, TooltipContext context, @Nullable ServerPlayerEntity player) {
        var stack = super.getPolymerItemStack(itemStack, context, player);

        if (player != null && !PolymerResourcePackUtils.hasMainPack(player)) {
            stack.addEnchantment(Enchantments.LURE, 2);
        }

        return stack;
    }
}
