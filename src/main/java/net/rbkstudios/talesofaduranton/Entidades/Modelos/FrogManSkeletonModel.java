package net.rbkstudios.talesofaduranton.Entidades.Modelos;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManSkeletonAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManSkeletonEntity;
import net.rbkstudios.talesofaduranton.TalesOfAduranton;


public class FrogManSkeletonModel<T extends FrogManSkeletonEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TalesOfAduranton.MODID, "frogmanmodel"), "main");
	private final ModelPart root;



	public FrogManSkeletonModel(ModelPart root) {
		this.root = root.getChild("root");

	}
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create().texOffs(18, 47).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -14.0F, 0.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -14.0F, 0.0F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(42, 0).addBox(0.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, 0.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(42, 26).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -8.0F, 0.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create().texOffs(0, 26).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -12.0F, -14.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}


	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		super.renderToBuffer(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}



	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(FrogManSkeletonAnimaciones.CAMINAR, v, v1, 4f, 2.5f);
		this.animate(((FrogManSkeletonEntity) t).idleAnimationState, FrogManSkeletonAnimaciones.IDLE, v2, 1f);
		this.animate(((FrogManSkeletonEntity) t).gruñirAnimationState, FrogManSkeletonAnimaciones.GRUÑIDO, v2, 1f);
		this.animate(((FrogManSkeletonEntity) t).spawnAnimationState, FrogManSkeletonAnimaciones.SPAWN, v2, 1f);

	}






}