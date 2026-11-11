package com.krg.construction.service;

import com.krg.construction.model.CompanyInfo;
import com.krg.construction.model.DeliveryFleetItem;
import com.krg.construction.model.ManufacturingStage;
import com.krg.construction.model.Product;
import com.krg.construction.model.WhyChooseUsItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructionDataService {

    public CompanyInfo getCompanyInfo() {
        return new CompanyInfo(
            "KRG CONSTRUCTIONS",
            "KRG CONSTRUCTIONS",
            2010,
            "Building Stronger Foundations Since 2010",
            "Madagondapalli, Tamil Nadu",
            "JP4V+335, Madagondapalli, Tamil Nadu, India.",
            "JP4V+335, Madagondapalli",
            "9787267811",
            "+91 97872 67811",
            "918122895259",
            "+91 81228 95259",
            "Hello KRG CONSTRUCTIONS, I would like to enquire about building materials and delivery availability. Please let me know the details.",
            "Open 24 Hours",
            35,
            "12\u00B036'18.5\"N 77\u00B044'33.8\"E",
            "https://www.google.com/maps/dir/?api=1&destination=12.605139,77.742722",
            "Hello KRG CONSTRUCTIONS, I would like to enquire about delivery availability to my location. Please let me know the details.",
            "Family-Owned Business"
        );
    }

    public List<Product> getManufacturedProducts() {
        return List.of(
            new Product(
                "4-inch-solid-cement-bricks",
                "4-inch Solid Cement Bricks",
                "Manufactured Cement Bricks",
                "MANUFACTURED",
                "High-density solid cement bricks manufactured under controlled hydraulic pressure and table vibration. 100% solid dense body with no holes, cavities, or perforations.",
                "Standard 4-inch width (Solid Body)",
                List.of(
                    "100% solid body without holes, cavities, or perforations",
                    "Hydraulic pressure and vibration for maximum compressive density",
                    "Uniform shape and true corners reduce mortar joint thickness",
                    "Thoroughly water-cured for reliable load endurance"
                ),
                "Internal partition walls, parapets & compound boundary fencing",
                "/images/solid-cement-bricks-4inch.jpg",
                "KRG Manufactured",
                "Hello KRG Constructions, I would like to enquire about 4-inch Solid Cement Bricks. Please share availability and delivery details to my site."
            ),
            new Product(
                "6-inch-solid-cement-bricks",
                "6-inch Solid Cement Bricks",
                "Manufactured Cement Bricks",
                "MANUFACTURED",
                "Heavy-duty solid cement bricks engineered for load-bearing external walls, structural pillars, and multi-story masonry construction. Completely solid with no cavities.",
                "Standard 6-inch width (Solid Body)",
                List.of(
                    "Solid dense structural body engineered for primary load-bearing walls",
                    "Completely solid: strictly no holes, no cavities, no perforations",
                    "Superior thermal mass and acoustic dampening properties",
                    "Dimensional uniformity ensures clean joint courses and easier plastering"
                ),
                "Load-bearing external walls, foundation plinths & structural masonry",
                "/images/solid-cement-bricks-6inch.jpg",
                "KRG Manufactured",
                "Hello KRG Constructions, I would like to enquire about 6-inch Solid Cement Bricks. Please share availability and delivery details to my site."
            )
        );
    }

    public List<Product> getSuppliedProducts() {
        return List.of(
            new Product(
                "p-sand",
                "P-Sand",
                "Plastering Sand",
                "SUPPLIED",
                "Finely sieved, triple-washed manufactured sand engineered specifically for interior and exterior wall plastering. 100% free from silt and clay impurities.",
                "Fine Granulometry (Plastering Grade)",
                List.of(
                    "Triple-washed with zero silt content to prevent surface cracks",
                    "Ultra-fine, smooth texture for effortless trowel application",
                    "Reduces paint putty consumption and gives smooth wall finish",
                    "Available in prompt tractor and lorry delivery loads"
                ),
                "Interior wall plastering, exterior facade plastering & smooth finishes",
                "/images/p-sand.jpg",
                "Supplied Material",
                "Hello KRG Constructions, I would like to enquire about P-Sand (Plastering Sand). Please share load availability and delivery details."
            ),
            new Product(
                "m-sand",
                "M-Sand",
                "Concrete Sand",
                "SUPPLIED",
                "High-grade cubical-grain manufactured granite sand engineered as a superior, eco-conscious replacement for river sand in all structural concrete casting.",
                "Graded Granulometry (Concreting Grade)",
                List.of(
                    "Cubical grain shape enhances mechanical interlock and compressive strength",
                    "Zero organic matter, clay lumps, or agricultural salt impurities",
                    "Optimal grading ensures dense, void-free structural concrete",
                    "Ideal for heavy RCC structural casting and masonry work"
                ),
                "RCC roof slabs, columns, beams, lintels & brick masonry",
                "/images/m-sand.jpg",
                "Supplied Material",
                "Hello KRG Constructions, I would like to enquire about M-Sand (Manufactured Sand). Please share load availability and delivery details."
            ),
            new Product(
                "dust",
                "Dust",
                "Stone Dust / Quarry Dust",
                "SUPPLIED",
                "Clean, finely graded blue metal granite quarry dust. Essential for interlocking paver bedding, flooring leveling, pipe trench backfilling, and sub-bases.",
                "Granite Quarry Dust (Sub-base Grade)",
                List.of(
                    "High compaction stability for firm ground and paver block bedding",
                    "Clean blue metal granite dust free from garden soil or organic residue",
                    "Cost-effective sub-base leveling and trench backfill material",
                    "Direct transport to site in tractor and lorry tippers"
                ),
                "Paver block bedding, floor leveling, pipe trench backfilling & sub-bases",
                "/images/stone-dust.jpg",
                "Supplied Material",
                "Hello KRG Constructions, I would like to enquire about Dust (Stone / Quarry Dust). Please share availability and delivery details."
            ),
            new Product(
                "cement-brands",
                "Cement – Sagar, Priya, Ramco & ACC",
                "Certified Brand Cement",
                "SUPPLIED",
                "Fresh, depot-sourced cement from India's leading verified manufacturers: Sagar Cement, Priya Cement, Ramco Cement, and ACC Cement.",
                "Standard 50 kg Moisture-Protected Bags",
                List.of(
                    "Fresh batch inventory supplied directly from verified company depots",
                    "Available in leading trusted brands: Sagar, Priya, Ramco, and ACC",
                    "High early strength and durable performance for RCC and brickwork",
                    "Available in retail bag counts or full vehicle loads delivered to site"
                ),
                "Reinforced concrete structures, brick bonding, plastering & flooring",
                "/images/cement-brands.jpg",
                "Top Brands",
                "Hello KRG Constructions, I would like to enquire about Cement (Sagar, Priya, Ramco, ACC). Please share bag availability and delivery pricing."
            ),
            new Product(
                "20mm-jelly",
                "20 mm Jelly",
                "Coarse Aggregate",
                "SUPPLIED",
                "Machine-crushed blue metal granite coarse aggregate (20 mm). The primary structural aggregate required for all reinforced concrete (RCC) works.",
                "20 mm Nominal Size (RCC Grade)",
                List.of(
                    "Machine-crushed blue metal granite with sharp, angular cubical shape",
                    "Washed clean and free from stone dust, shale, and clay coating",
                    "Maximizes mechanical interlock within concrete matrix",
                    "Dependable bulk supply direct from crusher to your jobsite"
                ),
                "Roof slabs, columns, beams, lintels & reinforced concrete (RCC)",
                "/images/jelly-20mm.jpg",
                "Blue Metal",
                "Hello KRG Constructions, I would like to enquire about 20 mm Jelly (Coarse Aggregate). Please share load availability and delivery details."
            ),
            new Product(
                "40mm-jelly",
                "40 mm Jelly",
                "Coarse Aggregate",
                "SUPPLIED",
                "Heavy-duty 40 mm machine-crushed blue metal granite aggregate. Engineered for plain cement concrete (PCC), foundation bedding, and road sub-bases.",
                "40 mm Nominal Size (PCC Grade)",
                List.of(
                    "Heavy angular granite aggregate for high load-bearing foundation bedding",
                    "Ideal for plain cement concrete (PCC) and mass concrete foundation work",
                    "Provides superior drainage and ground stabilization under foundations",
                    "Available in tractor and lorry tippers for convenient site tipping"
                ),
                "Foundation trenches, PCC bedding, road sub-bases & mass concreting",
                "/images/jelly-40mm.jpg",
                "Blue Metal",
                "Hello KRG Constructions, I would like to enquire about 40 mm Jelly (Coarse Aggregate). Please share load availability and delivery details."
            )
        );
    }

    public List<Product> getAllProducts() {
        var all = new java.util.ArrayList<Product>();
        all.addAll(getManufacturedProducts());
        all.addAll(getSuppliedProducts());
        return java.util.Collections.unmodifiableList(all);
    }

    public List<Product> getFeaturedProducts() {
        return getAllProducts();
    }

    public List<WhyChooseUsItem> getWhyChooseUsList() {
        return List.of(
            new WhyChooseUsItem(
                "shield-check",
                "Quality-Focused Products",
                "Every cement brick is manufactured with selected aggregates, precise water-cement ratio, and systematically water-cured to achieve maximum compressive load capacity and clean edges.",
                "Batch-Tested Strength"
            ),
            new WhyChooseUsItem(
                "factory",
                "Semi-Automatic Manufacturing",
                "Our facility employs vibrating hydraulic brick-making machinery that eliminates internal voids and ensures dimensional accuracy, preventing crooked walls and uneven joints.",
                "Precision Compaction"
            ),
            new WhyChooseUsItem(
                "box-heart",
                "Small Orders Accepted",
                "Whether you need 500 cement bricks to complete a compound wall or full vehicle loads for a major residential project, we treat every order with equal dedication and transparency.",
                "All Order Sizes Welcome"
            ),
            new WhyChooseUsItem(
                "truck",
                "Prompt Local Delivery",
                "With our own dedicated tractors and lorries, we ensure fast direct-to-site delivery within 30–35 km of Madagondapalli, navigating village roads and tricky access points safely.",
                "30–35 km Radius"
            )
        );
    }

    public List<DeliveryFleetItem> getDeliveryFleet() {
        return List.of(
            new DeliveryFleetItem(
                "Tractor Delivery",
                "Partial & Small-to-Medium Loads",
                "Narrow rural lanes, layout boundaries & restricted site entrances",
                "Our dedicated company tractors provide flexible access for smaller deliveries, plot perimeters, and tight residential sites where larger trucks cannot easily enter. Small orders are gladly accepted.",
                List.of(
                    "Small orders accepted with full dedication",
                    "Easily navigates narrow roads, unpaved approach ways, and plot entrances",
                    "Direct site tipping at your designated unloading spot",
                    "Delivery charges included in the product price"
                ),
                "/images/delivery-tractor.jpg"
            ),
            new DeliveryFleetItem(
                "Lorry / Truck Delivery",
                "Bulk & Commercial Vehicle Loads",
                "Primary foundation casting, heavy masonry walling & bulk material drops",
                "Our heavy-duty tipper lorries deliver larger-volume shipments of solid cement bricks, M-Sand, P-Sand, cement, and blue metal jelly directly from the factory to your site across our 30–35 km coverage area.",
                List.of(
                    "High-capacity commercial transport for full construction phases",
                    "Direct delivery within approximately 30–35 km from our Madagondapalli factory",
                    "Hydraulic tipper unloading minimizes manual handling time",
                    "Delivery charges included in the product price"
                ),
                "/images/delivery-lorry.jpg"
            )
        );
    }

    public List<ManufacturingStage> getManufacturingStages() {
        return List.of(
            new ManufacturingStage(
                1,
                "Stage 01: Machine Operation",
                "Semi-Automatic Machine Operation",
                "Our manufacturing begins with semi-automatic hydraulic machinery operated under continuous operator oversight. The operator manages hydraulic control valves and table vibration to ensure uniform distribution and dense compaction across every cycle.",
                "/images/mfg-worker-operating-machine.jpg",
                "Worker operating semi-automatic hydraulic cement brick pressing machine in covered yard",
                "Semi-Automatic Hydraulic Precision",
                List.of(
                    "Controlled hydraulic pressing eliminates internal voids",
                    "Table vibration promotes homogenous material compaction",
                    "Hands-on operator oversight guarantees batch consistency"
                )
            ),
            new ManufacturingStage(
                2,
                "Stage 02: Mould Compaction",
                "Cement Mixture Pressed Into Solid Bricks",
                "A balanced damp mixture of Portland cement, clean sand, and fine aggregates is directed into heavy precision steel moulds. High hydraulic pressure compresses the mixture into completely solid rectangular bricks with flat faces and zero cavities.",
                "/images/mfg-mixture-pressed-mould.jpg",
                "Heavy steel mould and hydraulic ram compressing damp cement mixture into completely solid rectangular bricks",
                "100% Solid Body • Zero Holes",
                List.of(
                    "Heavy-gauge solid mould tooling with no core cavity pins",
                    "High compressive ram pressure for dense, void-free bricks",
                    "Clean, true right-angle edges for crisp masonry coursing"
                )
            ),
            new ManufacturingStage(
                3,
                "Stage 03: Demoulding",
                "Freshly Manufactured Solid Cement Bricks",
                "Immediately after hydraulic compression, the solid cement bricks are smoothly demoulded onto wooden production pallets. Each brick stands firm with crisp edges, dark moist cement texture, and a completely solid structure ready for initial setting.",
                "/images/mfg-fresh-manufactured-bricks.jpg",
                "Freshly manufactured green solid cement bricks standing in uniform rows on wooden production pallets",
                "Crisp Edges & Uniform Shape",
                List.of(
                    "Smooth demoulding without distortion or corner crumbling",
                    "Rigid solid geometry across standard 4-inch and 6-inch sizes",
                    "Immediate visual quality inspection before moving to the curing area"
                )
            ),
            new ManufacturingStage(
                4,
                "Stage 04: Curing & Stacking",
                "Finished Solid Bricks Arranged in Stacks",
                "Following demoulding, the solid bricks undergo systematic water curing to achieve their full compressive load strength and durability. Cured bricks are neatly arranged in tall, organized yard stacks, ready for prompt site loading and dispatch.",
                "/images/mfg-finished-brick-stacks.jpg",
                "Finished cured solid cement bricks neatly arranged in tall stacks in outdoor factory yard",
                "Water-Cured Strength & Dispatch",
                List.of(
                    "Dedicated water curing to develop complete structural durability",
                    "Tall, parallel stacks in our Madagondapalli yard for easy loading",
                    "Direct transport to sites across 30–35 km via company tractors & lorries"
                )
            )
        );
    }
}
