package com.cmdb26.cmdb26sqolmod.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.cmdb26.cmdb26sqolmod.events.WolfArmorEventHandler;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class WolfArmorLayer extends LayerRenderer<WolfEntity, WolfModel<WolfEntity>> {

    private static final ResourceLocation ARMOR_TEXTURE =
            new ResourceLocation("cmdb26sqolmod", "textures/models/armor/wolf_armor_layer_1.png");

    public WolfArmorLayer(IEntityRenderer<WolfEntity, WolfModel<WolfEntity>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight,
                       WolfEntity wolf, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack armor = WolfArmorEventHandler.WOLF_ARMOR_MAP.get(wolf.getUniqueID());
        if (armor != null && !armor.isEmpty()) {
            RenderType type = RenderType.getEntityCutoutNoCull(ARMOR_TEXTURE);
            this.getEntityModel().render(matrixStack, buffer.getBuffer(type), packedLight,
                    OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
