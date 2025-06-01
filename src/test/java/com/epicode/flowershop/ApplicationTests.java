package com.epicode.flowershop;

import com.epicode.flowershop.exceptions.UnrecognizedBouquetSizeException;
import com.epicode.flowershop.exceptions.UnrecognizedItemCreationRequestException;
import com.epicode.flowershop.models.Bouquet;
import com.epicode.flowershop.models.Plant;
import com.epicode.flowershop.models.SellableItem;
import com.epicode.flowershop.models.Tree;
import com.epicode.flowershop.utilities.CustomLogger;
import com.epicode.flowershop.utilities.PlantNursery;
import com.epicode.flowershop.utilities.UniversalLogger;
import org.junit.Test;


import static org.junit.Assert.*;

public class ApplicationTests {

    private final CustomLogger logger = UniversalLogger.getInstance().getGlobalLogger();

    @Test
    public void spawnSellableItem_validInput_returnsCorrectProduct() {
        SellableItem s;
        try {
            s = PlantNursery.spawn("plAnT", " cacTus");
            assertNotNull(s);
            assertEquals("Cactus", s.getName());
            assertEquals(Plant.class, s.getClass());

            s = PlantNursery.spawn("   TrEE ", "BonsAi ");
            assertNotNull(s);
            assertEquals("Bonsai Tree", s.getName());
            assertEquals(Tree.class, s.getClass());

            s = PlantNursery.spawn(" BouQuet  ", "ROSES ", " MeDium");
            assertNotNull(s);
            assertEquals("Bouquet of Roses - Medium Size", s.getName());
            assertEquals(21.5, s.getPrice(), 0);
            assertEquals(Bouquet.class, s.getClass());
            Bouquet b = (Bouquet) s;
            assertEquals("Bouquet of Roses - Medium Size | Composed of 5x Rose with 2x Leaf, 1x Mix of Leaves, 1x Ribbon", b.getElementList());

            b = (Bouquet) PlantNursery.spawn(" BouQuet  ", "Mixed flowers", "xL");
            assertNotNull(b);
            assertEquals("Bouquet of Mixed Flowers - Extra Large Size", b.getName());
            assertEquals(25.4, b.getPrice(), 0);
            assertEquals("Bouquet of Mixed Flowers - Extra Large Size | Composed of 10x Daisy, 3x Sunflower, 6x Snapdragon, 4x Statice, 5x Chrysanthemum, 1x Velvet Bow, 1x Velvet Ribbon", b.getElementList());

            b = (Bouquet) PlantNursery.spawn(" BouQuet  ", "Mixed flowers ", " Sm ");
            assertNotNull(b);
            assertEquals("Bouquet of Mixed Flowers - Small Size", b.getName());
            assertEquals(8.1, b.getPrice(), 0);
            assertEquals("Bouquet of Mixed Flowers - Small Size | Composed of 5x Daisy, 1x Sunflower with 2x Leaf, 1x Snapdragon, 2x Statice with 2x Leaf, 1x Chrysanthemum, 1x Mix of Leaves, 1x Ribbon", b.getElementList());
        } catch (UnrecognizedItemCreationRequestException | UnrecognizedBouquetSizeException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void spawnSellableItem_invalidInput_launchesException() {
        assertThrows(UnrecognizedItemCreationRequestException.class, () -> PlantNursery.spawn("pla nt", "cactus"));
        assertThrows(UnrecognizedItemCreationRequestException.class, () -> PlantNursery.spawn("plant", "cac tus"));
        assertThrows(IllegalArgumentException.class, () -> PlantNursery.spawn("", "cactus"));
        assertThrows(IllegalArgumentException.class, () -> PlantNursery.spawn("tree", "    "));
    }
}
