package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

class GildedRoseApprovalTest {

    @Test
    void itemsLoseQualityOverTime() {
        Approvals.verify(ItemPrinter.printQualityOverNDays(30));
    }
}
