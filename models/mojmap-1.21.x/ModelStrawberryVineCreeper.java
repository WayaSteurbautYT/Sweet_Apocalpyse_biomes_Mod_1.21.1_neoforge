// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelStrawberryVineCreeper<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "strawberryvinecreeper"), "main");
	private final ModelPart Creeper;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart lEGS;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;
	private final ModelPart Bush;
	private final ModelPart Bush1;
	private final ModelPart Bush2;
	private final ModelPart Bush3;
	private final ModelPart Bush4;

	public ModelStrawberryVineCreeper(ModelPart root) {
		this.Creeper = root.getChild("Creeper");
		this.head = this.Creeper.getChild("head");
		this.body = this.Creeper.getChild("body");
		this.lEGS = this.Creeper.getChild("lEGS");
		this.leg1 = this.lEGS.getChild("leg1");
		this.leg2 = this.lEGS.getChild("leg2");
		this.leg3 = this.lEGS.getChild("leg3");
		this.leg4 = this.lEGS.getChild("leg4");
		this.Bush = root.getChild("Bush");
		this.Bush1 = this.Bush.getChild("Bush1");
		this.Bush2 = this.Bush.getChild("Bush2");
		this.Bush3 = this.Bush.getChild("Bush3");
		this.Bush4 = this.Bush.getChild("Bush4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Creeper = partdefinition.addOrReplaceChild("Creeper", CubeListBuilder.create(),
				PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition head = Creeper.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(4.4F, 0.4F, 4.4F, -8.8F, -8.8F, -8.8F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = Creeper.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(4.4F, 12.4F, 2.4F, -8.8F, -12.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(16, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lEGS = Creeper.addOrReplaceChild("lEGS", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 4.0F));

		PartDefinition leg1 = lEGS.addOrReplaceChild("leg1",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg2 = lEGS.addOrReplaceChild("leg2",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition leg3 = lEGS.addOrReplaceChild("leg3",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition leg4 = lEGS.addOrReplaceChild("leg4",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(2.4F, 6.4F, 2.4F, -4.8F, -6.8F, -4.8F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, -8.0F));

		PartDefinition Bush = partdefinition.addOrReplaceChild("Bush", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Bush1 = Bush.addOrReplaceChild("Bush1", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F,
				-10.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Bush2 = Bush.addOrReplaceChild("Bush2", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F,
				-10.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 0.0F));

		PartDefinition Bush3 = Bush.addOrReplaceChild("Bush3", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F,
				-10.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Bush4 = Bush.addOrReplaceChild("Bush4", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F,
				-10.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		Creeper.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Bush.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
	}
}