package com.example.application.views.preislist;

import com.example.application.data.TravelOrder;
import com.example.application.service.TravelOrderService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Preis List")
@Route(value = "preis-list", layout = MainLayout.class)
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PreisListView extends VerticalLayout {

    private final Grid<TravelOrder> grid = new Grid<>(TravelOrder.class, true);
    private final TravelOrderService travelOrderService;

    public PreisListView(@Autowired TravelOrderService travelOrderService) {
        this.travelOrderService = travelOrderService;

        setSpacing(true);
        setSizeFull();

        grid.setSizeFull();

        if (travelOrderService.findAll().isEmpty()) {
            travelOrderService.fillTestData(20);
        }

        add(grid);
        reload();
    }

    private void reload() {
        grid.setItems(travelOrderService.findAll());
    }
}