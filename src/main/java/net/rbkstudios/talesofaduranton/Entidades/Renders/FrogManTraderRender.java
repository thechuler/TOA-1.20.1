package net.rbkstudios.talesofaduranton.Entidades.Renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManShamanEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManTraderEntity;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManShamanModel;
import net.rbkstudios.talesofaduranton.Entidades.Modelos.FrogManTraderModel;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManTraderRender<type extends FrogManTraderEntity> extends MobRenderer<type, FrogManTraderModel<type>> {



    private static final ResourceLocation TEXTURAS = new ResourceLocation(TalesOfAduranton.MODID, "textures/entity/frogman_trader.png");




    public FrogManTraderRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FrogManTraderModel<>(pContext.bakeLayer(FrogManTraderModel.LAYER_LOCATION)), 0.5f);

    }


    @Override
    public void render(FrogManTraderEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render((type) pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(type type) {


            return TEXTURAS;






    }
}
