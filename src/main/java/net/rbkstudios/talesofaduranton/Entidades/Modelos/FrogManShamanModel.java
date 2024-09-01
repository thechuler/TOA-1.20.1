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
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Animaciones.FrogManShamanAnimaciones;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManEntity;
import net.rbkstudios.talesofaduranton.Entidades.Entity.FrogManShamanEntity;

public class FrogManShamanModel<T extends FrogManShamanEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frogmanshamanmodel"), "main");
	private final ModelPart root;


	public FrogManShamanModel(ModelPart root) {
		this.root = root.getChild("root");

	}



	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 28.0F, 0.0F));

		PartDefinition BrazoIzq = root.addOrReplaceChild("BrazoIzq", CubeListBuilder.create(), PartPose.offset(-7.0F, -14.0F, 0.0F));

		PartDefinition cube_r1 = BrazoIzq.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 53).addBox(-2.0F, -2.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, -1.4114F, 0.6049F, 0.0911F));

		PartDefinition Baston = BrazoIzq.addOrReplaceChild("Baston", CubeListBuilder.create().texOffs(0, 52).addBox(-4.0F, -15.0F, -7.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(66, 0).addBox(-5.0F, -10.0F, -8.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(42, 26).addBox(-6.0F, -19.0F, -9.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(8, 52).addBox(-5.0F, -18.0F, -16.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.0F, 1.0F));

		PartDefinition BrazoDer = root.addOrReplaceChild("BrazoDer", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -3.0F, 3.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -14.0F, 0.0F));

		PartDefinition cube_r2 = BrazoDer.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(-1.0F, -2.0F, -8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r3 = BrazoDer.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(50, 0).addBox(-2.0F, -5.0F, -4.0F, 4.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition PiernaDer = root.addOrReplaceChild("PiernaDer", CubeListBuilder.create().texOffs(8, 63).addBox(0.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -8.0F, 0.0F));

		PartDefinition PiernaIzq = root.addOrReplaceChild("PiernaIzq", CubeListBuilder.create().texOffs(42, 63).addBox(-4.0F, 0.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -8.0F, 0.0F));

		PartDefinition Cabeza = root.addOrReplaceChild("Cabeza", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition mandibulaInf = Cabeza.addOrReplaceChild("mandibulaInf", CubeListBuilder.create().texOffs(42, 38).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 7.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(61, 26).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 59).addBox(-7.0F, 0.0F, 7.0F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mandibulasup = Cabeza.addOrReplaceChild("mandibulasup", CubeListBuilder.create().texOffs(0, 26).addBox(-7.0F, -12.0F, -14.0F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 7.0F));

		PartDefinition Casco = mandibulasup.addOrReplaceChild("Casco", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -10.0F, 16.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -7.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Casco.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 63).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -6.0F, 6.0F, -0.5236F, 0.0F, 0.4363F));

		PartDefinition cube_r5 = Casco.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(68, 15).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -6.0F, 6.0F, -0.5236F, 0.0F, -0.4363F));

		return LayerDefinition.create(meshdefinition, 128, 128);
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
		this.animateWalk(FrogManShamanAnimaciones.CAMINAR, v, v1, 4f, 2.5f);
		this.animate(((FrogManShamanEntity) t).idleAnimationState, FrogManShamanAnimaciones.IDLE, v2, 1f);
		this.animate(((FrogManShamanEntity) t).gruñirAnimationState, FrogManShamanAnimaciones.GRUÑIDO, v2, 1f);
		//this.animate(((FrogManEntity) t).atacarAnimationState, FrogManAnimaciones.ATACAR, v2, 1f);
	}
}