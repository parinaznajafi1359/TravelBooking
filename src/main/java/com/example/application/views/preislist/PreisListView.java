package com.example.application.views.preislist;

import com.example.application.data.TravelOrder;
import com.example.application.service.TravelOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.example.application.data.TravelOrderException;
import com.vaadin.flow.component.notification.Notification;

@PageTitle("Preis List")
@Route("preis")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PreisListView extends VerticalLayout {

    private final Button buttonAddWrong = new Button("Add WRONG order");

    private final Button buttonRemoveAll = new Button("Remove all orders");
    private final Button buttonAdd10 = new Button("Add 10 orders");

    private final Grid<TravelOrder> grid = new Grid<>(TravelOrder.class, true);
    private final TravelOrderService travelOrderService;

    public PreisListView(@Autowired TravelOrderService travelOrderService) {
        this.travelOrderService = travelOrderService;

        setSpacing(true);
        setSizeFull();

        buttonAddWrong.addClickListener(event -> addWrongOrder());

        buttonRemoveAll.addClickListener(event -> removeAllOrders());
        buttonAdd10.addClickListener(event -> add10Orders());

        HorizontalLayout buttons = new HorizontalLayout(buttonRemoveAll, buttonAdd10, buttonAddWrong);
        buttons.setSpacing(true);

        grid.setSizeFull();

        if (travelOrderService.findAll().isEmpty()) {
            travelOrderService.fillTestData(10);
        }

        add(buttons, grid);

        reload();
    }

    private void addWrongOrder() {
        try {
            travelOrderService.addWrongOrder();
            reload();
        } catch (TravelOrderException e) {
            Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
        }
    }

    private void removeAllOrders() {
        travelOrderService.removeAll();
        buttonRemoveAll.setEnabled(false);
        reload();
    }

    private void add10Orders() {
        travelOrderService.fillTestData(10);
        buttonRemoveAll.setEnabled(true);
        reload();
    }

    private void reload() {
        grid.setItems(travelOrderService.findAll());
        buttonRemoveAll.setEnabled(!travelOrderService.findAll().isEmpty());
    }
}