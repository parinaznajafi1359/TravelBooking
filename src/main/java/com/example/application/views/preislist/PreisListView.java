package com.example.application.views.preislist;

import com.example.application.data.TravelOrder;
import com.example.application.data.TravelOrderException;
import com.example.application.service.TravelOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Preis List")
@Route("preis")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class PreisListView extends VerticalLayout {

    private final Button buttonRemoveAll = new Button("Remove all orders");
    private final Button buttonAdd10 = new Button("Add 10 orders");
    private final Button buttonAddWrong = new Button("Add WRONG order");

    private final Grid<TravelOrder> grid = new Grid<>(TravelOrder.class, false);
    private final TravelOrderService travelOrderService;

    public PreisListView(@Autowired TravelOrderService travelOrderService) {
        this.travelOrderService = travelOrderService;

        setSpacing(true);
        setSizeFull();

        buttonRemoveAll.addClickListener(event -> removeAllOrders());
        buttonAdd10.addClickListener(event -> add10Orders());
        buttonAddWrong.addClickListener(event -> addWrongOrder());

        HorizontalLayout buttons = new HorizontalLayout(
                buttonRemoveAll,
                buttonAdd10,
                buttonAddWrong
        );
        buttons.setSpacing(true);

        setupGridColumns();

        grid.setSizeFull();

        if (travelOrderService.findAll().isEmpty()) {
            travelOrderService.fillTestData(10);
        }

        add(buttons, grid);
        reload();
    }

    private void setupGridColumns() {
        grid.addColumn(order -> order.getOrderId())
                .setHeader("Order ID")
                .setSortable(true);

        grid.addColumn(order -> order.getOrderDate())
                .setHeader("Order Date")
                .setSortable(true);

        grid.addColumn(order -> order.getDestination())
                .setHeader("Destination")
                .setSortable(true);

        grid.addColumn(order -> order.getTravelClass())
                .setHeader("Class")
                .setSortable(true);

        grid.addColumn(order -> order.getPrice())
                .setHeader("Price")
                .setSortable(true);

        grid.addColumn(order -> order.getPersons())
                .setHeader("Persons")
                .setSortable(true);

        grid.addComponentColumn(order -> {
                    Checkbox checkbox = new Checkbox(Boolean.TRUE.equals(order.getInsurance()));
                    checkbox.setReadOnly(true);
                    return checkbox;
                })
                .setHeader("Insurance")
                .setSortable(true)
                .setComparator(order -> Boolean.TRUE.equals(order.getInsurance()));
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

    private void addWrongOrder() {
        try {
            travelOrderService.addWrongOrder();
            reload();
        } catch (TravelOrderException e) {
            Notification.show(e.getMessage(), 4000, Notification.Position.MIDDLE);
        }
    }

    private void reload() {
        grid.setItems(travelOrderService.findAll());
        buttonRemoveAll.setEnabled(!travelOrderService.findAll().isEmpty());
    }
}